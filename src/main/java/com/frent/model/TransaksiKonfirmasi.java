/**
 * @author odeng
 *
 * Copyright © 2017 All Rights Reserved
 *
 */
package com.frent.model;

import java.io.Serializable;

public class TransaksiKonfirmasi implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1237477090652403310L;
	private int confirmation_id;
	private String confirmation_date;
	private String confirmation_image;
	private int confirmation_bank;
	private String confirmation_transfer;
	private TransaksiPinjam pinjam;
	private User user;

	/**
	 * @return the confirmation_id
	 */
	public int getConfirmation_id() {
		return confirmation_id;
	}

	/**
	 * @param confirmation_id
	 *            the confirmation_id to set
	 */
	public void setConfirmation_id(int confirmation_id) {
		this.confirmation_id = confirmation_id;
	}

	/**
	 * @return the confirmation_date
	 */
	public String getConfirmation_date() {
		return confirmation_date;
	}

	/**
	 * @param confirmation_date
	 *            the confirmation_date to set
	 */
	public void setConfirmation_date(String confirmation_date) {
		this.confirmation_date = confirmation_date;
	}

	/**
	 * @return the confirmation_image
	 */
	public String getConfirmation_image() {
		return confirmation_image;
	}

	/**
	 * @param confirmation_image
	 *            the confirmation_image to set
	 */
	public void setConfirmation_image(String confirmation_image) {
		this.confirmation_image = confirmation_image;
	}

	/**
	 * @return the confirmation_transfer
	 */
	public String getConfirmation_transfer() {
		return confirmation_transfer;
	}

	/**
	 * @param confirmation_transfer the confirmation_transfer to set
	 */
	public void setConfirmation_transfer(String confirmation_transfer) {
		this.confirmation_transfer = confirmation_transfer;
	}

	/**
	 * @return the confirmation_bank
	 */
	public int getConfirmation_bank() {
		return confirmation_bank;
	}

	/**
	 * @param confirmation_bank the confirmation_bank to set
	 */
	public void setConfirmation_bank(int confirmation_bank) {
		this.confirmation_bank = confirmation_bank;
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

}
