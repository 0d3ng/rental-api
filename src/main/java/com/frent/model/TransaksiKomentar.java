/**
 * @author odeng
 *
 * Copyright © 2017 All Rights Reserved
 *
 */
package com.frent.model;

import java.io.Serializable;

public class TransaksiKomentar implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4347331528984367242L;
	private int testimoni_id;
	private String testimoni_create;
	private String testimoni_content;
	private int testimoni_rate;
	private User user;
	private TransaksiPinjam pinjam;

	/**
	 * @return the testimoni_id
	 */
	public int getTestimoni_id() {
		return testimoni_id;
	}

	/**
	 * @param testimoni_id
	 *            the testimoni_id to set
	 */
	public void setTestimoni_id(int testimoni_id) {
		this.testimoni_id = testimoni_id;
	}

	/**
	 * @return the testimoni_create
	 */
	public String getTestimoni_create() {
		return testimoni_create;
	}

	/**
	 * @param testimoni_create
	 *            the testimoni_create to set
	 */
	public void setTestimoni_create(String testimoni_create) {
		this.testimoni_create = testimoni_create;
	}

	/**
	 * @return the testimoni_content
	 */
	public String getTestimoni_content() {
		return testimoni_content;
	}

	/**
	 * @param testimoni_content
	 *            the testimoni_content to set
	 */
	public void setTestimoni_content(String testimoni_content) {
		this.testimoni_content = testimoni_content;
	}

	/**
	 * @return the testimoni_rate
	 */
	public int getTestimoni_rate() {
		return testimoni_rate;
	}

	/**
	 * @param testimoni_rate
	 *            the testimoni_rate to set
	 */
	public void setTestimoni_rate(int testimoni_rate) {
		this.testimoni_rate = testimoni_rate;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
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
		result = prime * result + ((testimoni_content == null) ? 0 : testimoni_content.hashCode());
		result = prime * result + ((testimoni_create == null) ? 0 : testimoni_create.hashCode());
		result = prime * result + testimoni_id;
		result = prime * result + testimoni_rate;
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
		TransaksiKomentar other = (TransaksiKomentar) obj;
		if (testimoni_content == null) {
			if (other.testimoni_content != null)
				return false;
		} else if (!testimoni_content.equals(other.testimoni_content))
			return false;
		if (testimoni_create == null) {
			if (other.testimoni_create != null)
				return false;
		} else if (!testimoni_create.equals(other.testimoni_create))
			return false;
		if (testimoni_id != other.testimoni_id)
			return false;
		if (testimoni_rate != other.testimoni_rate)
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
		return "TransaksiTestimoni [testimoni_id=" + testimoni_id + ", testimoni_create=" + testimoni_create
				+ ", testimoni_content=" + testimoni_content + ", testimoni_rate=" + testimoni_rate + "]";
	}

}
