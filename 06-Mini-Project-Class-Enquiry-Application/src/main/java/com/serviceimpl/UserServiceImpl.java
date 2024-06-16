package com.serviceimpl;

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
	public String login(LoginForm dto) {
		return null;
	}

	@Override
	public boolean signup(SignUp dto) {
		
		//Copy data from Dto obj to Entity obj
		UserEntity entity=new UserEntity();
		entity.setUserEmail(dto.getEmail());
		entity.setUserMobile(Long.valueOf(dto.getPhno()));
		entity.setUserName(dto.getName());
		entity.setUserEnable(0);
		UserEntity userentity = userrepo.save(entity);
		
		
		//Generate Random Pwd
		String tempPwd = PwdUtils.generateRandomPwd();
		UserActivationDtls actdtls=new UserActivationDtls();
		actdtls.setUserEmail(dto.getUserEmail());
		actdtls.setUserEnable(0);
		actdtls.setUserPwd(tempPwd);
		actdtls.setUserId(userentity.getUserId());
		actdtlsrepo.save(actdtls);
		
		//Send Pwd over email
		String subject="VKY IT class temporary password";
		String body="<p>Hi "+entity.getUserName()+",/nPlease find below temporary password for email : "+entity.getUserEmail()+"./n Password : "+tempPwd+",</p>";
		return emailutils.sendPwd(subject, body, entity.getUserEmail(), null);
	}

	@Override
	public boolean unlockAccount(UnlockForm form) {
		return false;
	}

	@Override
	public String forgotPwd(String email) {
		return null;
	}

}
