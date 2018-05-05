/**
 * @author odeng
 *
 * Copyright © 2017 All Rights Reserved
 *
 */
package com.frent.model;

import java.io.Serializable;

public class Paket implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5493088147647022415L;

	private int paket_id;
	private String paket_name;
	private int paket_jumlahjam;
	/**
	 * @return the paket_id
	 */
	public int getPaket_id() {
		return paket_id;
	}
	/**
	 * @param paket_id the paket_id to set
	 */
	public void setPaket_id(int paket_id) {
		this.paket_id = paket_id;
	}
	/**
	 * @return the paket_name
	 */
	public String getPaket_name() {
		return paket_name;
	}
	/**
	 * @param paket_name the paket_name to set
	 */
	public void setPaket_name(String paket_name) {
		this.paket_name = paket_name;
	}
	/**
	 * @return the paket_jumlahjam
	 */
	public int getPaket_jumlahjam() {
		return paket_jumlahjam;
	}
	/**
	 * @param paket_jumlahjam the paket_jumlahjam to set
	 */
	public void setPaket_jumlahjam(int paket_jumlahjam) {
		this.paket_jumlahjam = paket_jumlahjam;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + paket_id;
		result = prime * result + paket_jumlahjam;
		result = prime * result + ((paket_name == null) ? 0 : paket_name.hashCode());
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
		Paket other = (Paket) obj;
		if (paket_id != other.paket_id)
			return false;
		if (paket_jumlahjam != other.paket_jumlahjam)
			return false;
		if (paket_name == null) {
			if (other.paket_name != null)
				return false;
		} else if (!paket_name.equals(other.paket_name))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Paket [paket_id=" + paket_id + ", paket_name=" + paket_name + ", paket_jumlahjam=" + paket_jumlahjam
				+ "]";
	}
	
	
}
