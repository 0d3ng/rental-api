/**
 * @author odeng
 *
 * Copyright © 2017 All Rights Reserved
 *
 */
package com.frent.helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.frent.model.TransaksiBatal;

public class TransaksiBatalRowMapper implements RowMapper<TransaksiBatal> {

	@Override
	public TransaksiBatal mapRow(ResultSet arg0, int arg1) throws SQLException {
		TransaksiBatal cancel = new TransaksiBatal();
		cancel.setCancel_id(arg0.getString("cancel_id"));
		cancel.setCancel_reason(arg0.getString("cancel_reason"));
		cancel.setCancel_telp(arg0.getString("cancel_telp);"));
		cancel.setCancel_user(arg0.getString("cancel_user"));;
		cancel.setCancel_date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(arg0.getDate("cancel_date").getTime())));
		return cancel;
	}

}
