/**
 * @author odeng
 *
 * Copyright © 2017 All Rights Reserved
 *
 */
package com.frent.model;

import java.io.Serializable;

public class TransaksiBatal implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5420006790808352237L;
	private String cancel_id;
	private String cancel_date;
	private String cancel_user;
	private String cancel_telp;
	private String cancel_reason;
	private TransaksiPinjam pinjam;

	/**
	 * @return the cancel_id
	 */
	public String getCancel_id() {
		return cancel_id;
	}

	/**
	 * @param cancel_id
	 *            the cancel_id to set
	 */
	public void setCancel_id(String cancel_id) {
		this.cancel_id = cancel_id;
	}

	/**
	 * @return the cancel_date
	 */
	public String getCancel_date() {
		return cancel_date;
	}

	/**
	 * @param cancel_date
	 *            the cancel_date to set
	 */
	public void setCancel_date(String cancel_date) {
		this.cancel_date = cancel_date;
	}

	/**
	 * @return the cancel_user
	 */
	public String getCancel_user() {
		return cancel_user;
	}

	/**
	 * @param cancel_user
	 *            the cancel_user to set
	 */
	public void setCancel_user(String cancel_user) {
		this.cancel_user = cancel_user;
	}

	/**
	 * @return the cancel_telp
	 */
	public String getCancel_telp() {
		return cancel_telp;
	}

	/**
	 * @param cancel_telp
	 *            the cancel_telp to set
	 */
	public void setCancel_telp(String cancel_telp) {
		this.cancel_telp = cancel_telp;
	}

	/**
	 * @return the cancel_reason
	 */
	public String getCancel_reason() {
		return cancel_reason;
	}

	/**
	 * @param cancel_reason
	 *            the cancel_reason to set
	 */
	public void setCancel_reason(String cancel_reason) {
		this.cancel_reason = cancel_reason;
	}

	/**
	 * @return the pinjam
	 */
	public TransaksiPinjam getPinjam() {
		return pinjam;
	}

	/**
	 * @param pinjam the pinjam to set
	 */
	public void setPinjam(TransaksiPinjam pinjam) {
		this.pinjam = pinjam;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cancel_date == null) ? 0 : cancel_date.hashCode());
		result = prime * result + ((cancel_id == null) ? 0 : cancel_id.hashCode());
		result = prime * result + ((cancel_reason == null) ? 0 : cancel_reason.hashCode());
		result = prime * result + ((cancel_telp == null) ? 0 : cancel_telp.hashCode());
		result = prime * result + ((cancel_user == null) ? 0 : cancel_user.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
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
		TransaksiBatal other = (TransaksiBatal) obj;
		if (cancel_date == null) {
			if (other.cancel_date != null)
				return false;
		} else if (!cancel_date.equals(other.cancel_date))
			return false;
		if (cancel_id == null) {
			if (other.cancel_id != null)
				return false;
		} else if (!cancel_id.equals(other.cancel_id))
			return false;
		if (cancel_reason == null) {
			if (other.cancel_reason != null)
				return false;
		} else if (!cancel_reason.equals(other.cancel_reason))
			return false;
		if (cancel_telp == null) {
			if (other.cancel_telp != null)
				return false;
		} else if (!cancel_telp.equals(other.cancel_telp))
			return false;
		if (cancel_user == null) {
			if (other.cancel_user != null)
				return false;
		} else if (!cancel_user.equals(other.cancel_user))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TransaksiCancel [cancel_id=" + cancel_id + ", cancel_date=" + cancel_date + ", cancel_user="
				+ cancel_user + ", cancel_telp=" + cancel_telp + ", cancel_reason=" + cancel_reason + "]";
	}

}
