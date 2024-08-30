package com.service;

import java.util.List;

import com.binding.App;

public interface ArService {

	public String createApplication(App app);
	public List<App> getAllApps(Integer userId);
	
}
