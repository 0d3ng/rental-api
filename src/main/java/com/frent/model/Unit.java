/**
 * @author odeng
 *
 * Copyright © 2017 All Rights Reserved
 *
 */
package com.frent.model;

import java.io.Serializable;	

public class Unit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1090743958957641450L;
	
	private int unit_id;
	private String unit_name;
	private String unit_produsen;
	private String unit_class;
	private String unit_framework;
	private String unit_detail;
	private String unit_thumbnail;
	private int unit_baggage;
	private int unit_passenger;	
	private Kategori kategori;
	/**
	 * @return the unit_id
	 */
	public int getUnit_id() {
		return unit_id;
	}
	/**
	 * @param unit_id the unit_id to set
	 */
	public void setUnit_id(int unit_id) {
		this.unit_id = unit_id;
	}
	/**
	 * @return the unit_name
	 */
	public String getUnit_name() {
		return unit_name;
	}
	/**
	 * @param unit_name the unit_name to set
	 */
	public void setUnit_name(String unit_name) {
		this.unit_name = unit_name;
	}
	/**
	 * @return the unit_produsen
	 */
	public String getUnit_produsen() {
		return unit_produsen;
	}
	/**
	 * @param unit_produsen the unit_produsen to set
	 */
	public void setUnit_produsen(String unit_produsen) {
		this.unit_produsen = unit_produsen;
	}
	/**
	 * @return the unit_class
	 */
	public String getUnit_class() {
		return unit_class;
	}
	/**
	 * @param unit_class the unit_class to set
	 */
	public void setUnit_class(String unit_class) {
		this.unit_class = unit_class;
	}
	/**
	 * @return the unit_framework
	 */
	public String getUnit_framework() {
		return unit_framework;
	}
	/**
	 * @param unit_framework the unit_framework to set
	 */
	public void setUnit_framework(String unit_framework) {
		this.unit_framework = unit_framework;
	}
	/**
	 * @return the unit_detail
	 */
	public String getUnit_detail() {
		return unit_detail;
	}
	/**
	 * @param unit_detail the unit_detail to set
	 */
	public void setUnit_detail(String unit_detail) {
		this.unit_detail = unit_detail;
	}
	/**
	 * @return the unit_thumbnail
	 */
	public String getUnit_thumbnail() {
		return unit_thumbnail;
	}
	/**
	 * @param unit_thumbnail the unit_thumbnail to set
	 */
	public void setUnit_thumbnail(String unit_thumbnail) {
		this.unit_thumbnail = unit_thumbnail;
	}
	/**
	 * @return the unit_baggage
	 */
	public int getUnit_baggage() {
		return unit_baggage;
	}
	/**
	 * @param unit_baggage the unit_baggage to set
	 */
	public void setUnit_baggage(int unit_baggage) {
		this.unit_baggage = unit_baggage;
	}
	/**
	 * @return the unit_passenger
	 */
	public int getUnit_passenger() {
		return unit_passenger;
	}
	/**
	 * @param unit_passenger the unit_passenger to set
	 */
	public void setUnit_passenger(int unit_passenger) {
		this.unit_passenger = unit_passenger;
	}
	/**
	 * @return the kategori
	 */
	public Kategori getKategori() {
		return kategori;
	}
	/**
	 * @param kategori the kategori to set
	 */
	public void setKategori(Kategori kategori) {
		this.kategori = kategori;
	}
	
	

}
