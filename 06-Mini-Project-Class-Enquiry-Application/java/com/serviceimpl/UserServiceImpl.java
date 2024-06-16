package com.serviceimpl;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dto.LoginForm;
import com.dto.SignUp;
import com.dto.UnlockForm;
import com.entity.UserActivationDtls;
import com.entity.UserEntity;
import com.repository.UserActDtlsRepository;
import com.repository.UserRepository;
import com.service.UserService;
import com.util.EmailUtils;
import com.util.PwdUtils;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userrepo;
	
	@Autowired
	private UserActDtlsRepository actdtlsrepo;
	
	@Autowired
	private EmailUtils emailutils;
	
	@Override
	public String login(LoginForm form) {
		String result=null;
		UserEntity user = userrepo.findByUserEmail(form.getEmail());
		if(null!=user)
		{
			if(user.getUserEnable()==1)
			{
				if(user.getUserPwd().equals(form.getPwd()))
				{
					result="success";
				}else
				{
					result="Invalid password.";
				}
			}else
			{
				result="Account is locked,Please unlock account.";
			}
		}else
		{
			result="No user found with given email.";
		}
		return result;
	}

	@Override
	@Transactional
	public String signup(SignUp dto,HttpServletRequest req) {
		String result="error";
		if(null==userrepo.findByUserEmail(dto.getEmail()))
		{
			//Generate Random Pwd
			String tempPwd = PwdUtils.generateRandomPwd();
			
			//Copy data from Dto obj to Entity obj
			UserEntity entity=new UserEntity();
			entity.setUserEmail(dto.getEmail());
			entity.setUserMobile(Long.valueOf(dto.getPhno()));
			entity.setUserName(dto.getName());
			entity.setUserEnable(0);
			entity.setUserPwd(tempPwd);
			UserEntity userentity = userrepo.save(entity);
			
			
			UserActivationDtls actdtls=new UserActivationDtls();
			actdtls.setUserEmail(dto.getEmail());
			actdtls.setUserEnable(0);
			actdtls.setUserPwd(tempPwd);
			actdtls.setUserId(userentity.getUserId());
			actdtlsrepo.save(actdtls);
			
			//Send Pwd over email
			String subject="Unlock your account | (VKY IT class) temporary password";
			StringBuffer body=new StringBuffer();
			body.append("<h2>Hi "+entity.getUserName()+",Please find below temporary password for email : "+entity.getUserEmail()+"</h2>");
			body.append("<br><h2>Temporary Password : "+tempPwd+"</h2>");
			body.append("<br><h2><a href='http://localhost:8081/vkyit/unlock?email="+entity.getUserEmail()+"'>Click here to unlock account</a><h2>");
			result=emailutils.sendPwd(subject, body.toString(), entity.getUserEmail(), null)?"success":"error";
		}else
		{
			result="Email is already register";
		}
		return result;
	}

	@Override
	public String unlockAccount(UnlockForm form) {
		String result="";
		UserEntity entity = userrepo.findByUserEmail(form.getEmail());
		if(null!=entity)
		{
			if(entity.getUserEnable()!=1)
			{
				if(entity.getUserPwd().equals(form.getTempPwd()))
				{
					entity.setUserEnable(1);
					entity.setUserPwd(form.getConfirmPwd());
					entity.setCrtnBy(entity.getUserId());
					entity.setModBy(entity.getUserId());
					userrepo.save(entity);
					result="Account unlock successfully.";
				}
				else
				{
					result="Invalid password.";
				}
			}else
			{
				result="Account is already active.";
			}
		}
		else
		{
			result="No record found with given email.";
		}
		return result;
	}
	
	@Override
	public UserEntity getUserByEmail(String email) {
		return userrepo.findByUserEmail(email);
	}

	@Override
	public String forgotPwd(String email) {
		UserEntity user = userrepo.findByUserEmail(email);
		String result="forgotpwd";
		if(null!=user)
		{
			if(user.getUserEnable()==1)
			{
				//Generate Random Pwd
				String password = PwdUtils.generateRandomPwd();
				
				user.setModBy(user.getUserId());
				user.setUserPwd(password);
				userrepo.save(user);
				String subject="Forgot Password";
				StringBuffer body=new StringBuffer();
				body.append("<h2>Hi "+user.getUserName()+",Please find below temporary password for email : "+user.getUserEmail()+"</h2>");
				body.append("<br><h2>Temporary Password : "+password+"</h2>");
				body.append("<br><br><br><br><h2>Thank You/h2><br><h2>Vaibhav Yadav</h2>");
				if(emailutils.sendPwd(subject,body.toString(),user.getUserEmail(), null))
				{
					result="success";
				}else
				{
					result="fail";
				}
				
			}else
			{
				result="InactiveUser";
			}
		}else
		{
			result="InvalidUser";
		}
		return result;
	}

}
