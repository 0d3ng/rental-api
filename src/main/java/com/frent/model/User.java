package com.frent.model;

import java.io.Serializable;
/**
 * @author odeng
 * 
 */

public class User implements Serializable{
	
	private static final long serialVersionUID = -8994661688697999312L;
	private long user_id;
	private String user_email;
	private String user_password;
	private String user_idcard;
	private String user_frontname;
	private String user_lastname;
	private String user_photo;
	private String user_photo_idcard;
	private String user_jk;
	private String user_tanggallahir;
	private String user_phone;
	private String user_address;
	private boolean user_active;
	private int user_groupid;
	private boolean user_verification;
	private String user_apps;
	private String user_token;
	private String user_type;
	
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getUser_idcard() {
		return user_idcard;
	}
	public void setUser_idcard(String user_idcard) {
		this.user_idcard = user_idcard;
	}
	public String getUser_frontname() {
		return user_frontname;
	}
	public void setUser_frontname(String user_frontname) {
		this.user_frontname = user_frontname;
	}
	public String getUser_lastname() {
		return user_lastname;
	}
	public void setUser_lastname(String user_lastname) {
		this.user_lastname = user_lastname;
	}
	public String getUser_photo() {
		return user_photo;
	}
	public void setUser_photo(String user_photo) {
		this.user_photo = user_photo;
	}
	/**
	 * @return the user_photo_idcard
	 */
	public String getUser_photo_idcard() {
		return user_photo_idcard;
	}
	/**
	 * @param user_photo_idcard the user_photo_idcard to set
	 */
	public void setUser_photo_idcard(String user_photo_idcard) {
		this.user_photo_idcard = user_photo_idcard;
	}
	public String getUser_jk() {
		return user_jk;
	}
	public void setUser_jk(String user_jk) {
		this.user_jk = user_jk;
	}
	public String getUser_tanggallahir() {
		return user_tanggallahir;
	}
	public void setUser_tanggallahir(String user_tanggallahir) {
		this.user_tanggallahir = user_tanggallahir;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public String getUser_address() {
		return user_address;
	}
	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}
	public boolean isUser_active() {
		return user_active;
	}
	public void setUser_active(boolean user_active) {
		this.user_active = user_active;
	}
	public int getUser_groupid() {
		return user_groupid;
	}
	public void setUser_groupid(int user_groupid) {
		this.user_groupid = user_groupid;
	}
	public String getUser_apps() {
		return user_apps;
	}
	public void setUser_apps(String user_apps) {
		this.user_apps = user_apps;
	}
	public boolean isUser_verification() {
		return user_verification;
	}
	public void setUser_verification(boolean user_verification) {
		this.user_verification = user_verification;
	}
	/**
	 * @return the user_token
	 */
	public String getUser_token() {
		return user_token;
	}
	/**
	 * @param user_token the user_token to set
	 */
	public void setUser_token(String user_token) {
		this.user_token = user_token;
	}
	
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (user_active ? 1231 : 1237);
		result = prime * result + ((user_address == null) ? 0 : user_address.hashCode());
		result = prime * result + ((user_apps == null) ? 0 : user_apps.hashCode());
		result = prime * result + ((user_email == null) ? 0 : user_email.hashCode());
		result = prime * result + ((user_frontname == null) ? 0 : user_frontname.hashCode());
		result = prime * result + user_groupid;
		result = prime * result + (int) (user_id ^ (user_id >>> 32));
		result = prime * result + ((user_idcard == null) ? 0 : user_idcard.hashCode());
		result = prime * result + ((user_jk == null) ? 0 : user_jk.hashCode());
		result = prime * result + ((user_lastname == null) ? 0 : user_lastname.hashCode());
		result = prime * result + ((user_password == null) ? 0 : user_password.hashCode());
		result = prime * result + ((user_phone == null) ? 0 : user_phone.hashCode());
		result = prime * result + ((user_photo == null) ? 0 : user_photo.hashCode());
		result = prime * result + ((user_tanggallahir == null) ? 0 : user_tanggallahir.hashCode());
		result = prime * result + ((user_token == null) ? 0 : user_token.hashCode());
		result = prime * result + (user_verification ? 1231 : 1237);
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (user_active != other.user_active)
			return false;
		if (user_address == null) {
			if (other.user_address != null)
				return false;
		} else if (!user_address.equals(other.user_address))
			return false;
		if (user_apps == null) {
			if (other.user_apps != null)
				return false;
		} else if (!user_apps.equals(other.user_apps))
			return false;
		if (user_email == null) {
			if (other.user_email != null)
				return false;
		} else if (!user_email.equals(other.user_email))
			return false;
		if (user_frontname == null) {
			if (other.user_frontname != null)
				return false;
		} else if (!user_frontname.equals(other.user_frontname))
			return false;
		if (user_groupid != other.user_groupid)
			return false;
		if (user_id != other.user_id)
			return false;
		if (user_idcard == null) {
			if (other.user_idcard != null)
				return false;
		} else if (!user_idcard.equals(other.user_idcard))
			return false;
		if (user_jk == null) {
			if (other.user_jk != null)
				return false;
		} else if (!user_jk.equals(other.user_jk))
			return false;
		if (user_lastname == null) {
			if (other.user_lastname != null)
				return false;
		} else if (!user_lastname.equals(other.user_lastname))
			return false;
		if (user_password == null) {
			if (other.user_password != null)
				return false;
		} else if (!user_password.equals(other.user_password))
			return false;
		if (user_phone == null) {
			if (other.user_phone != null)
				return false;
		} else if (!user_phone.equals(other.user_phone))
			return false;
		if (user_photo == null) {
			if (other.user_photo != null)
				return false;
		} else if (!user_photo.equals(other.user_photo))
			return false;
		if (user_tanggallahir == null) {
			if (other.user_tanggallahir != null)
				return false;
		} else if (!user_tanggallahir.equals(other.user_tanggallahir))
			return false;
		if (user_token == null) {
			if (other.user_token != null)
				return false;
		} else if (!user_token.equals(other.user_token))
			return false;
		if (user_verification != other.user_verification)
			return false;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", user_email=" + user_email + ", user_password=" + user_password
				+ ", user_idcard=" + user_idcard + ", user_frontname=" + user_frontname + ", user_lastname="
				+ user_lastname + ", user_photo=" + user_photo + ", user_photo_idcard=" + user_photo_idcard
				+ ", user_jk=" + user_jk + ", user_tanggallahir=" + user_tanggallahir + ", user_phone=" + user_phone
				+ ", user_address=" + user_address + ", user_active=" + user_active + ", user_groupid=" + user_groupid
				+ ", user_verification=" + user_verification + ", user_apps=" + user_apps + ", user_token=" + user_token
				+ ", user_type=" + user_type + "]";
	}
	/**
	 * @return the user_type
	 */
	public String getUser_type() {
		return user_type;
	}
	/**
	 * @param user_type the user_type to set
	 */
	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}

}
