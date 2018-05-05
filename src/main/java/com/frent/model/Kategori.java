/**
 * @author odeng
 *
 * Copyright © 2017 All Rights Reserved
 *
 */
package com.frent.model;

import java.io.Serializable;

public class Kategori implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5486788728144310122L;
	private int categories_id;
	private String categories_name;

	/**
	 * @return the categories_id
	 */
	public int getCategories_id() {
		return categories_id;
	}
	/**
	 * @param categories_id the categories_id to set
	 */
	public void setCategories_id(int categories_id) {
		this.categories_id = categories_id;
	}
	/**
	 * @return the categories_name
	 */
	public String getCategories_name() {
		return categories_name;
	}
	/**
	 * @param categories_name the categories_name to set
	 */
	public void setCategories_name(String categories_name) {
		this.categories_name = categories_name;
	}

}
