package com.serviceimpl;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.binding.UnlockAccountForm;
import com.binding.UserAccountForm;
import com.constant.AppConstants;
import com.entity.UserEntity;
import com.repository.UserRepository;
import com.service.AccountService;
import com.utils.EmailUtils;
import com.utils.PwdUtils;
@Service
public class AccountServiceImpl implements AccountService {

	private Logger logger=LoggerFactory.getLogger(AccountServiceImpl.class);
	
	@Autowired
	private PwdUtils pwdutils;
	
	@Autowired
	private UserRepository userrepo;
	
	@Autowired
	private EmailUtils emailutils;
	
	@Override
	public Boolean createUserAccount(UserAccountForm form) {
		UserEntity entity=new UserEntity();
		BeanUtils.copyProperties(form, entity);
		
		//set temp pwd
		entity.setPwd(pwdutils.generateRandomPwd());
		
		//set account status
		entity.setAccStatus(AppConstants.LOCKED_ACCOUNT_STATUS);
		entity.setActiveSw(AppConstants.ACTIVATE_ACCOUNT);
		try
		{
			userrepo.save(entity);
		}catch(Exception e)
		{
			logger.error("Error occured while creating account for user : "+form.getEmail(),e);
		}
		
		//send email to user to unlock account
		String toAddress=form.getEmail();
		String subject="User Registration";
		String body=readEmailBody("REG_EMAIL_BODY.txt", entity);
		return emailutils.sendEmail(subject, body, toAddress,null);
	}

	@Override
	public List<UserAccountForm> fetchUserAccounts() {
		List<UserEntity> entityList = userrepo.findAll();
		List<UserAccountForm> formlist=new ArrayList<>();
		entityList.forEach(entity->{
			UserAccountForm form=new UserAccountForm();
			BeanUtils.copyProperties(entity, form);
			formlist.add(form);
		});
		return formlist;
	}

	@Override
	public UserAccountForm getAccountById(Integer id) {
		UserAccountForm form=new UserAccountForm();
		Optional<UserEntity> optentity = userrepo.findById(id);
		if(optentity.isPresent())
		{
			BeanUtils.copyProperties(optentity.get(), form);
		}
		return form;
	}

	@Override
	public Boolean updateUserAccount(UserAccountForm form) {
		return null;
	}

	@Override
	public Boolean actDeactAccount(Integer id) {
		Optional<UserEntity> optentity = userrepo.findById(id);
		Boolean result=false;
		if(optentity.isPresent())
		{
			UserEntity entity = optentity.get();
			if(AppConstants.ACTIVATE_ACCOUNT.equals(entity.getActiveSw()))
			{
				entity.setAccStatus(AppConstants.INACTIVATE_ACCOUNT);
			}else
			{
				entity.setActiveSw(AppConstants.ACTIVATE_ACCOUNT);
			}
			userrepo.save(entity);
			result=true;
		}
		return result;
	}

	@Override
	public String unlockUserAccount(UnlockAccountForm form) {
		String result=null;
		try
		{
			UserEntity entity = userrepo.findByEmail(form.getEmail());
			entity.setPwd(form.getNewPwd());
			entity.setAccStatus(AppConstants.UNLOCKED_ACCOUNT_STATUS);
			userrepo.save(entity);
			result=AppConstants.ACCOUNT_CREATED_SUCCESSFULLY;
		}catch(Exception e)
		{
			e.printStackTrace();
			result=AppConstants.ACCOUNT_CREATION_FAILED;
		}
		return result;
	}
	
	private String readEmailBody(String fileName,UserEntity user)
	{
		StringBuilder sb=new StringBuilder();
		try(Stream<String> lines=Files.lines(Paths.get(fileName)))
		{
			lines.forEach(line->{
				line=line.replace("${FNAME}",user.getFullName());
				line=line.replace("${PWD}",user.getPwd());
				line=line.replace("${EMAIL}",user.getEmail());
				sb.append(line);
			});
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return sb.toString();
	}
	
}
