package com.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;

import com.binding.App;
import com.constants.AppConstants;
import com.entities.AppEntity;
import com.entities.UserEntity;
import com.exceptions.SsnWebException;
import com.repositories.AppRepository;
import com.repositories.UserRepository;
import com.service.ArService;

public class ArServiceImpl implements ArService {

	@Autowired
	private AppRepository apprepo;
	
	@Autowired
	private UserRepository userrepo;
	
	private Logger logger=LoggerFactory.getLogger(ArServiceImpl.class);
	
	private static final String SSA_WEB_API_URL="https://ssa.web.app/{ssn}";
	
	@Override
	public String createApplication(App app) {
		try
		{
			WebClient webClient = WebClient.create();
			String stateName=webClient.get().uri(SSA_WEB_API_URL,app.getSsn())
							.retrieve()
							.bodyToMono(String.class)
							.block();
			if(AppConstants.RI_STATE_NAME.equals(stateName))
			{
				AppEntity entity=new AppEntity();
				BeanUtils.copyProperties(app, entity);
				entity.setUser(userrepo.findById(app.getUserId()).get());
				entity=apprepo.save(entity);
				return "Application created with case number : "+entity.getCaseNumber();
			}
			
		}catch(Exception e)
		{
			logger.error("Error occured while fetching SSA Web api : ",e);
			throw new SsnWebException(e.getLocalizedMessage());
		}
		return AppConstants.INVALID_SSN;
	}

	@Override
	public List<App> getAllApps(Integer userId) {
		UserEntity user = userrepo.findById(userId).get();
		Integer roleId = user.getRoleId();
		List<AppEntity> appEntities=null;
		if(AppConstants.ADMIN_ROLE_ID==roleId)
		{
			appEntities = apprepo.findAll();
		}else
		{
			appEntities = apprepo.findByUser(user);
		}
		List<App> apps=new ArrayList<>();
		appEntities.forEach(ent->{
			App app=new App();
			BeanUtils.copyProperties(ent, app);
			apps.add(app);
		});
		return apps;
	}

}
