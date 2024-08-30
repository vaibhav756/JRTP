package com.serviceimpl;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.binding.DashboardCards;
import com.binding.LoginForm;
import com.binding.UserAccountForm;
import com.constant.AppConstants;
import com.entity.EligEntity;
import com.entity.UserEntity;
import com.repository.EligibilityRepository;
import com.repository.PlanRepository;
import com.repository.UserRepository;
import com.service.UserService;
import com.utils.EmailUtils;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userrepo;
	
	@Autowired
	private EmailUtils emailutils;
	
	@Autowired
	private PlanRepository planrepo;
	
	@Autowired
	private EligibilityRepository eligrepo;

	@Override
	public String login(LoginForm form) {
		UserEntity entity = userrepo.findByEmailAndPwd(form.getEmail(),form.getPwd());
		if(null==entity)
		{
			return AppConstants.INVALID_CREDENTIAL;
		}

		if(AppConstants.UNLOCKED_ACCOUNT_STATUS.equals(entity.getAccStatus()))
		{
			return AppConstants.UNLOCKED_ACCOUNT_STATUS+"|"+entity.getRoleId();
		}else
		{
			return AppConstants.ACCOUNT_LOCKED_MSG;
		}
	}

	@Override
	public Boolean recoverPassword(String email) {
		UserEntity entity = userrepo.findByEmail(email);
		if(null==entity)
		{
			return false;
		}else
		{
			String subject="Recover Pwd";
			String body=readEmailBody("FORGOT_PWD_EMAIL_BODY.txt", entity);
			return emailutils.sendEmail(subject, body, email,null);
		}
	}

	@Override
	public DashboardCards getDashboardInfo() {
		
		List<EligEntity> eligiList = eligrepo.findAll();
		DashboardCards card=new DashboardCards();
		card.setPlansCount(planrepo.count());
		card.setApprovedCount(eligiList.stream().filter(ed->"AP".equals(ed.getPlanStatus())).count());
		card.setDeniedCount(eligiList.stream().filter(ed->"DN".equals(ed.getPlanStatus())).count());
		card.setBeniftAmtGiven(eligiList.stream().mapToDouble(ed->ed.getBenefitAmt()).sum());
		return card;
	}

	@Override
	public UserAccountForm getUserByEmail(String email) {
		UserEntity entity = userrepo.findByEmail(email);
		UserAccountForm form=new UserAccountForm();
		BeanUtils.copyProperties(entity, form);
		return form;
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
