package com.serviceimpl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.constant.AppConstants;
import com.dto.BlogDto;
import com.dto.LoginForm;
import com.dto.SignUp;
import com.dto.UnlockForm;
import com.entity.UserActivationDtls;
import com.entity.UserEntity;
import com.repo.UserActDtlsRepository;
import com.repo.UserRepository;
import com.service.BlogService;
import com.service.UserService;
import com.util.EmailUtils;
import com.util.PwdUtils;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userrepo;
	
	@Autowired
	private UserActDtlsRepository actdtlsrepo;
	
	@Autowired
	private BlogService blogservice;
	
	@Autowired
	private EmailUtils emailutils;
	
	@Override
	public String login(LoginForm form) {
		String result=null;
		UserEntity user = userrepo.findByUserEmail(form.getEmail());
		if(null!=user)
		{
			if(user.getUserEnable()==AppConstants.USER_ENABLE)
			{
				if(user.getUserPwd().equals(form.getPwd()))
				{
					result=AppConstants.SUCCESS_MSG;
				}else
				{
					result=AppConstants.INVALID_PWD;
				}
			}else
			{
				result=AppConstants.ACC_LOCKED;
			}
		}else
		{
			result=AppConstants.INVALID_USER;
		}
		return result;
	}

	@Override
	@Transactional
	public String signup(SignUp dto,HttpServletRequest req) {
		String result=AppConstants.ERROR_MSG;
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
			result=emailutils.sendPwd(subject, body.toString(), entity.getUserEmail(), null)?AppConstants.SUCCESS_MSG:AppConstants.ERROR_MSG;
		}else
		{
			result=AppConstants.EMAIL_ALREADY_REG;
		}
		return result;
	}

	@Override
	public String unlockAccount(UnlockForm form) {
		String result="";
		UserEntity entity = userrepo.findByUserEmail(form.getEmail());
		if(null!=entity)
		{
			if(entity.getUserEnable()!=AppConstants.USER_ENABLE)
			{
				if(entity.getUserPwd().equals(form.getTempPwd()))
				{
					entity.setUserEnable(AppConstants.USER_ENABLE);
					entity.setUserPwd(form.getConfirmPwd());
					entity.setCrtnBy(entity.getUserId());
					entity.setModBy(entity.getUserId());
					userrepo.save(entity);
					result=AppConstants.UNLOCK_ACCOUNT;
				}
				else
				{
					result=AppConstants.INVALID_PWD;
				}
			}else
			{
				result=AppConstants.ACC_ALREADY_ACTIVE;
			}
		}
		else
		{
			result=AppConstants.INVALID_USER;
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
			if(user.getUserEnable()==AppConstants.USER_ENABLE)
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
					result=AppConstants.SUCCESS_MSG;
				}else
				{
					result=AppConstants.ERROR_MSG;
				}
				
			}else
			{
				result=AppConstants.INACTIVE_USER;
			}
		}else
		{
			result=AppConstants.INVALID_USER_ACC;
		}
		return result;
	}
	
	public List<BlogDto> getAllBlogs()
	{
		return blogservice.getAllBlogs();
	}
	
	@Override
	public UserEntity getUserById(Integer userid)
	{
		return userrepo.findById(userid).get();
	}
	
}
