/**
 * @author odeng
 *
 * Copyright © 2017 All Rights Reserved
 *
 */
package com.frent.dao;

import java.util.HashMap;
import java.util.List;

import com.frent.model.Setting;

public interface SettingDao {

	public HashMap<String, String> settings();

	public List<Setting> getSettings();

}
