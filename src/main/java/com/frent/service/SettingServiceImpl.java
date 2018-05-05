/**
 * @author odeng
 *
 * Copyright © 2017 All Rights Reserved
 *
 */
package com.frent.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frent.dao.SettingDao;
import com.frent.model.Setting;

@Service("settingService")
public class SettingServiceImpl implements SettingService{

	@Autowired
	SettingDao settingDao;
	
	public HashMap<String, String> settings() {
		return settingDao.settings();
	}

	@Override
	public List<Setting> getSettings() {
		return settingDao.getSettings();
	}

}
