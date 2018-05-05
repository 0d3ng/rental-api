/**
 * @author odeng
 *
 * Copyright © 2017 All Rights Reserved
 *
 */
package com.frent.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import com.frent.helper.Message;
import com.frent.helper.MessageStatus;
import com.frent.helper.VendorHelper;
import com.frent.model.Bank;
import com.frent.model.Kategori;
import com.frent.model.Lokasi;
import com.frent.model.Paket;
import com.frent.model.Setting;
import com.frent.model.Unit;
import com.frent.model.Vendor;
import com.frent.service.BankService;
import com.frent.service.KategoriService;
import com.frent.service.LokasiService;
import com.frent.service.PaketService;
import com.frent.service.SettingService;
import com.frent.service.UnitService;
import com.frent.service.VendorService;
import com.frent.util.AppConfig;
import com.frent.util.KeyGenerator;

@Controller
@RequestMapping("service/ref")
public class RefController {

	private final Logger LOGGER = Logger.getLogger(getClass());

	@Autowired
	KategoriService kategoriService;
	@Autowired
	PaketService paketService;
	@Autowired
	UnitService unitService;
	@Autowired
	VendorService vendorService;
	@Autowired
	LokasiService lokasiSerice;
	@Autowired
	BankService bankService;
	@Autowired
	AppConfig appConfig;
	@Autowired
	SettingService settingService;

	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	public @ResponseBody MessageStatus<Kategori> doFindAllKategori(@RequestHeader String token,
			UriComponentsBuilder uriComponentsBuilder) {

		MessageStatus<Kategori> status = new MessageStatus<>();
		status.setCode(HttpStatus.OK.value());
		status.setLink(uriComponentsBuilder.path("/service/ref/categories").build().toUriString());
		try {

			if (KeyGenerator.isValid(token)) {
				List<Kategori> kategories = kategoriService.kategoris();
				if (!kategories.isEmpty()) {
					status.setData(kategories);
					status.setMessage("Success.");
				} else {
					status.setCode(HttpStatus.NOT_FOUND.value());
					status.setMessage("Data is empty.");
					status.setDeveloperMessage("Please entry data.");
				}
			} else {
				status.setCode(HttpStatus.UNAUTHORIZED.value());
				status.setMessage("Auth token fail.");
				status.setDeveloperMessage("Check your token.");
			}
		} catch (Exception e) {
			if (e.getMessage().contains("Expiration Time")) {
				status.setCode(HttpStatus.UNAUTHORIZED.value());
				status.setMessage("Token have expired!");
			} else {
				status.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
				status.setMessage("Fail.");
				status.setDeveloperMessage(e.getMessage());
				e.printStackTrace();
			}
		}
		return status;
	}

	@RequestMapping(value = "/pakets", method = RequestMethod.GET)
	public @ResponseBody MessageStatus<Paket> doFindAllPaket(@RequestHeader String token,
			UriComponentsBuilder uriComponentsBuilder) {

		MessageStatus<Paket> status = new MessageStatus<>();
		status.setCode(HttpStatus.OK.value());
		status.setLink(uriComponentsBuilder.path("/service/ref/pakets").build().toUriString());
		try {

			if (KeyGenerator.isValid(token)) {
				List<Paket> pakets = paketService.pakets();
				if (!pakets.isEmpty()) {
					status.setData(pakets);
					status.setMessage("Success.");
				} else {
					status.setCode(HttpStatus.NOT_FOUND.value());
					status.setMessage("Data is empty.");
					status.setDeveloperMessage("Please entry data.");
				}
			} else {
				status.setCode(HttpStatus.UNAUTHORIZED.value());
				status.setMessage("Auth token fail.");
				status.setDeveloperMessage("Check yout token.");
			}
		} catch (Exception e) {
			if (e.getMessage().contains("Expiration Time")) {
				status.setCode(HttpStatus.UNAUTHORIZED.value());
				status.setMessage("Token have expired!");
			} else {
				status.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
				status.setMessage("Fail.");
				status.setDeveloperMessage(e.getMessage());
				e.printStackTrace();
			}
		}
		return status;
	}

	@RequestMapping(value = "/units", method = RequestMethod.GET)
	public @ResponseBody MessageStatus<Unit> doFindAllUnit(@RequestHeader String token,
			UriComponentsBuilder uriComponentsBuilder) {

		MessageStatus<Unit> status = new MessageStatus<>();
		status.setCode(HttpStatus.OK.value());
		status.setLink(uriComponentsBuilder.path("/service/ref/units").build().toUriString());
		try {

			if (KeyGenerator.isValid(token)) {
				List<Unit> units = unitService.units();
				if (!units.isEmpty()) {
					List<Unit> temps = new ArrayList<>();
					for (Unit unit : units) {
						unit.setUnit_thumbnail(appConfig.getImgUnit() + unit.getUnit_thumbnail());
						temps.add(unit);
					}
					status.setData(temps);
					status.setMessage("Success.");
				} else {
					status.setCode(HttpStatus.NOT_FOUND.value());
					status.setMessage("Data is empty.");
					status.setDeveloperMessage("Please entry data.");
				}
			} else {
				status.setCode(HttpStatus.UNAUTHORIZED.value());
				status.setMessage("Auth token fail.");
				status.setDeveloperMessage("Check yout token.");
			}
		} catch (Exception e) {
			if (e.getMessage().contains("Expiration Time")) {
				status.setCode(HttpStatus.UNAUTHORIZED.value());
				status.setMessage("Token have expired!");
			} else {
				status.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
				status.setMessage("Fail.");
				status.setDeveloperMessage(e.getMessage());
				e.printStackTrace();
			}
		}
		return status;
	}

	@RequestMapping(value = "/vendors", method = RequestMethod.GET)
	public @ResponseBody MessageStatus<Vendor> doFindAllVendor(@RequestHeader String token,
			UriComponentsBuilder uriComponentsBuilder) {

		MessageStatus<Vendor> status = new MessageStatus<>();
		status.setCode(HttpStatus.OK.value());
		status.setLink(uriComponentsBuilder.path("/service/ref/vendors").build().toUriString());
		try {

			if (KeyGenerator.isValid(token)) {
				List<Vendor> vendors = vendorService.getVendors();
				if (!vendors.isEmpty()) {
					List<Vendor> temp = new ArrayList<>();
					for (Vendor vendor : vendors) {
						vendor.setVendor_logo(appConfig.getImgVendor() + vendor.getVendor_logo());
						temp.add(vendor);
					}
					status.setData(temp);
					status.setMessage("Success.");
				} else {
					status.setCode(HttpStatus.NOT_FOUND.value());
					status.setMessage("Data is empty.");
					status.setDeveloperMessage("Please entry data.");
				}
			} else {
				status.setCode(HttpStatus.UNAUTHORIZED.value());
				status.setMessage("Auth token fail.");
				status.setDeveloperMessage("Check yout token.");
			}
		} catch (Exception e) {
			if (e.getMessage().contains("Expiration Time")) {
				status.setCode(HttpStatus.UNAUTHORIZED.value());
				status.setMessage("Token have expired!");
			} else {
				status.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
				status.setMessage("Fail.");
				status.setDeveloperMessage(e.getMessage());
				e.printStackTrace();
			}
		}
		return status;
	}

	@RequestMapping(value = "/vendor", method = RequestMethod.POST)
	public @ResponseBody Message<VendorHelper> doFindByVendorId(@RequestHeader String token,
			@RequestParam(value = "email") String email, UriComponentsBuilder uriComponentsBuilder) {

		Message<VendorHelper> status = new Message<>();
		status.setCode(HttpStatus.OK.value());
		status.setLink(uriComponentsBuilder.path("/service/ref/vendor").build().toUriString());
		try {

			if (KeyGenerator.isValid(token)) {
				VendorHelper vendorByEmail = vendorService.getVendorByEmail(email);
				if (vendorByEmail != null) {
					vendorByEmail.setFoto(appConfig.getImgVendor() + vendorByEmail.getFoto());
					status.setData(vendorByEmail);
					status.setMessage("Success.");
				} else {
					status.setCode(HttpStatus.NOT_FOUND.value());
					status.setMessage("Data is empty.");
					status.setDeveloperMessage("Please entry data.");
				}
			} else {
				status.setCode(HttpStatus.UNAUTHORIZED.value());
				status.setMessage("Auth token fail.");
				status.setDeveloperMessage("Check yout token.");
			}
		} catch (Exception e) {
			if (e.getMessage().contains("Expiration Time")) {
				status.setCode(HttpStatus.UNAUTHORIZED.value());
				status.setMessage("Token have expired!");
			} else {
				status.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
				status.setMessage("Fail.");
				status.setDeveloperMessage(e.getMessage());
				e.printStackTrace();
			}
		}
		return status;
	}

	@RequestMapping(value = "/locations", method = RequestMethod.GET)
	public @ResponseBody MessageStatus<Lokasi> doFindAllLocations(@RequestHeader String token,
			UriComponentsBuilder uriComponentsBuilder) {

		MessageStatus<Lokasi> status = new MessageStatus<>();
		status.setCode(HttpStatus.OK.value());
		status.setLink(uriComponentsBuilder.path("/service/ref/locations").build().toUriString());
		try {

			if (KeyGenerator.isValid(token)) {
				List<Lokasi> locations = lokasiSerice.lokasis();
				if (!locations.isEmpty()) {
					List<Lokasi> temp = new ArrayList<>();
					for (Lokasi location : locations) {
						location.setLocation_photo(appConfig.getImgLocation() + location.getLocation_photo());
						temp.add(location);
					}
					status.setData(temp);
					status.setMessage("Success.");
				} else {
					status.setCode(HttpStatus.NOT_FOUND.value());
					status.setMessage("Data is empty.");
					status.setDeveloperMessage("Please entry data.");
				}
			} else {
				status.setCode(HttpStatus.UNAUTHORIZED.value());
				status.setMessage("Auth token fail.");
				status.setDeveloperMessage("Check yout token.");
			}
		} catch (Exception e) {
			if (e.getMessage().contains("Expiration Time")) {
				status.setCode(HttpStatus.UNAUTHORIZED.value());
				status.setMessage("Token have expired!");
			} else {
				status.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
				status.setMessage("Fail.");
				status.setDeveloperMessage(e.getMessage());
				e.printStackTrace();
			}
		}
		return status;
	}

	@RequestMapping(value = "/banks", method = RequestMethod.GET)
	public @ResponseBody MessageStatus<Bank> doGetAllBanks(@RequestHeader String token,
			UriComponentsBuilder uriComponentsBuilder) {

		MessageStatus<Bank> status = new MessageStatus<>();
		status.setCode(HttpStatus.OK.value());
		status.setLink(uriComponentsBuilder.path("/service/ref/banks").build().toUriString());
		try {

			if (KeyGenerator.isValid(token)) {
				List<Bank> banks = bankService.getAllBank();
				if (!banks.isEmpty()) {
					List<Bank> temp = new ArrayList<>();
					for (Bank bank : banks) {
						bank.setLogo(appConfig.getImgBank() + bank.getLogo());
						temp.add(bank);
					}
					status.setData(temp);
					status.setMessage("Success.");
				} else {
					status.setCode(HttpStatus.NOT_FOUND.value());
					status.setMessage("Data is empty.");
					status.setDeveloperMessage("Please entry data.");
				}
			} else {
				status.setCode(HttpStatus.UNAUTHORIZED.value());
				status.setMessage("Auth token fail.");
				status.setDeveloperMessage("Check your token.");
			}
		} catch (Exception e) {
			if (e.getMessage().contains("Expiration Time")) {
				status.setCode(HttpStatus.UNAUTHORIZED.value());
				status.setMessage("Token have expired!");
			} else {
				status.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
				status.setMessage("Fail.");
				status.setDeveloperMessage(e.getMessage());
				e.printStackTrace();
			}
		}
		return status;
	}

	@RequestMapping(value = "/settings", method = RequestMethod.GET)
	public @ResponseBody MessageStatus<Setting> doGetSettings(UriComponentsBuilder uriComponentsBuilder) {

		MessageStatus<Setting> status = new MessageStatus<>();
		status.setCode(HttpStatus.OK.value());
		status.setLink(uriComponentsBuilder.path("/service/ref/settings").build().toUriString());
		try {
			List<Setting> settings = settingService.getSettings();
			if (!settings.isEmpty()) {
				status.setData(settings);
				status.setMessage("Success.");
			} else {
				status.setCode(HttpStatus.NOT_FOUND.value());
				status.setMessage("Data is empty.");
				status.setDeveloperMessage("Please entry data.");
			}
		} catch (Exception e) {
			if (e.getMessage().contains("Expiration Time")) {
				status.setCode(HttpStatus.UNAUTHORIZED.value());
				status.setMessage("Token have expired!");
			} else {
				status.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
				status.setMessage("Fail.");
				status.setDeveloperMessage(e.getMessage());
				e.printStackTrace();
			}
		}
		return status;
	}

}
