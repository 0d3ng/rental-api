/**
 * @author odeng
 *
 * Copyright © 2018 All Rights Reserved
 *
 */
package com.frent.controller.guest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import com.frent.helper.CariFilter;
import com.frent.helper.Message;
import com.frent.helper.MessageStatus;
import com.frent.helper.Pesanan;
import com.frent.helper.TransaksiPinjamHelper;
import com.frent.helper.VendorHelper;
import com.frent.model.Bank;
import com.frent.model.Kategori;
import com.frent.model.Lokasi;
import com.frent.model.Paket;
import com.frent.model.TransaksiPinjam;
import com.frent.model.Unit;
import com.frent.model.User;
import com.frent.model.Vendor;
import com.frent.model.VendorLokasi;
import com.frent.model.VendorUnit;
import com.frent.service.BankService;
import com.frent.service.EmailService;
import com.frent.service.KategoriService;
import com.frent.service.LokasiService;
import com.frent.service.PaketService;
import com.frent.service.SettingService;
import com.frent.service.TransaksiPinjamService;
import com.frent.service.UnitService;
import com.frent.service.UserService;
import com.frent.service.VendorService;
import com.frent.util.AppConfig;
import com.frent.util.EmailTemplate;
import com.frent.util.NumberGenerator;

@Controller
@RequestMapping("service/guest")
public class GuestController {

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
	TransaksiPinjamService transaksiPinjamService;
	private final SimpleDateFormat FORMAT_TRX = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Autowired
	SettingService settingService;
	@Autowired
	UserService userService;
	@Autowired
	EmailService emailService;

	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	public @ResponseBody MessageStatus<Kategori> doFindAllKategori(UriComponentsBuilder uriComponentsBuilder) {

		MessageStatus<Kategori> status = new MessageStatus<>();
		status.setCode(HttpStatus.OK.value());
		status.setLink(uriComponentsBuilder.path("service/guest/categories").build().toUriString());
		try {

			if (true) {
				List<Kategori> kategories = kategoriService.kategoris();
				if (!kategories.isEmpty()) {
					status.setData(kategories);
					status.setMessage("Success.");
				} else {
					status.setCode(HttpStatus.NOT_FOUND.value());
					status.setMessage("Data is empty.");
					status.setDeveloperMessage("Please entry data.");
				}
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
	public @ResponseBody MessageStatus<Paket> doFindAllPaket(UriComponentsBuilder uriComponentsBuilder) {

		MessageStatus<Paket> status = new MessageStatus<>();
		status.setCode(HttpStatus.OK.value());
		status.setLink(uriComponentsBuilder.path("service/guest/pakets").build().toUriString());
		try {

			if (true) {
				List<Paket> pakets = paketService.pakets();
				if (!pakets.isEmpty()) {
					status.setData(pakets);
					status.setMessage("Success.");
				} else {
					status.setCode(HttpStatus.NOT_FOUND.value());
					status.setMessage("Data is empty.");
					status.setDeveloperMessage("Please entry data.");
				}
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
	public @ResponseBody MessageStatus<Unit> doFindAllUnit(UriComponentsBuilder uriComponentsBuilder) {

		MessageStatus<Unit> status = new MessageStatus<>();
		status.setCode(HttpStatus.OK.value());
		status.setLink(uriComponentsBuilder.path("service/guest/units").build().toUriString());
		try {

			if (true) {
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
	public @ResponseBody MessageStatus<Vendor> doFindAllVendor(UriComponentsBuilder uriComponentsBuilder) {

		MessageStatus<Vendor> status = new MessageStatus<>();
		status.setCode(HttpStatus.OK.value());
		status.setLink(uriComponentsBuilder.path("service/guest/vendors").build().toUriString());
		try {

			if (true) {
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
	public @ResponseBody Message<VendorHelper> doFindByVendorId(@RequestParam(value = "email") String email,
			UriComponentsBuilder uriComponentsBuilder) {

		Message<VendorHelper> status = new Message<>();
		status.setCode(HttpStatus.OK.value());
		status.setLink(uriComponentsBuilder.path("service/guest/vendor").build().toUriString());
		try {

			if (true) {
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
	public @ResponseBody MessageStatus<Lokasi> doFindAllLocations(UriComponentsBuilder uriComponentsBuilder) {

		MessageStatus<Lokasi> status = new MessageStatus<>();
		status.setCode(HttpStatus.OK.value());
		status.setLink(uriComponentsBuilder.path("service/guest/locations").build().toUriString());
		try {

			if (true) {
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
	public @ResponseBody MessageStatus<Bank> doGetAllBanks(UriComponentsBuilder uriComponentsBuilder) {

		MessageStatus<Bank> status = new MessageStatus<>();
		status.setCode(HttpStatus.OK.value());
		status.setLink(uriComponentsBuilder.path("service/guest/banks").build().toUriString());
		try {

			if (true) {
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

	@RequestMapping(value = "/getPinjamByParam", method = RequestMethod.POST)
	public @ResponseBody MessageStatus<VendorUnit> doGetPinjamByKategori(@RequestBody CariFilter cariFilter,
			UriComponentsBuilder uriComponentsBuilder) {
		MessageStatus<VendorUnit> status = new MessageStatus<>();
		status.setCode(HttpStatus.OK.value());
		status.setLink(uriComponentsBuilder.path("/service/guest/getPinjamByParam").build().toUriString());
		try {
			LOGGER.info(cariFilter);
			if (true) {

				String vendorId = Arrays.toString(cariFilter.getVendorId());
				vendorId = vendorId.replaceAll(" ", "");
				vendorId = vendorId.substring(1, vendorId.length() - 1);

				String paketId = Arrays.toString(cariFilter.getPaketId());
				paketId = paketId.replaceAll(" ", "");
				paketId = paketId.substring(1, paketId.length() - 1);

				String unitId = Arrays.toString(cariFilter.getUnitId());
				unitId = unitId.replaceAll(" ", "");
				unitId = unitId.substring(1, unitId.length() - 1);

				List<VendorUnit> vendorUnits = transaksiPinjamService.getTransaksiPinjamByParam(
						String.valueOf(cariFilter.getLokasiId()), String.valueOf(cariFilter.getKategoriId()),
						cariFilter.getStok(), cariFilter.getStartDate(), cariFilter.getEndDate(), unitId, vendorId,
						cariFilter.isDriver(), cariFilter.isBbm(), cariFilter.isTol(), paketId,
						cariFilter.isSortByPrice());
				if (!vendorUnits.isEmpty()) {
					List<VendorUnit> list = new ArrayList<>();
					for (VendorUnit vendorUnit : vendorUnits) {
						vendorUnit.getVendor()
								.setVendor_logo(appConfig.getImgVendor() + vendorUnit.getVendor().getVendor_logo());
						vendorUnit.getUnit()
								.setUnit_thumbnail(appConfig.getImgUnit() + vendorUnit.getUnit().getUnit_thumbnail());
						list.add(vendorUnit);
					}
					status.setData(list);
					status.setMessage("Success.");
				} else {
					status.setCode(HttpStatus.NOT_FOUND.value());
					status.setMessage("Data is empty.");
					status.setDeveloperMessage("Please entry data.");
				}
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

	@RequestMapping(value = "/insertPinjam", method = RequestMethod.POST)
	public @ResponseBody Message<Pesanan> doInsertPinjam(@RequestBody TransaksiPinjamHelper pinjam,
			UriComponentsBuilder uriComponentsBuilder) {
		LOGGER.info(pinjam);
		Message<Pesanan> status = new Message<>();
		status.setCode(HttpStatus.OK.value());
		status.setLink(uriComponentsBuilder.path("/service/guest/insertPinjam").build().toUriString());
		try {
			if (true) {
				TransaksiPinjam transaksiPinjam = new TransaksiPinjam();
				transaksiPinjam.setTrxpinjam_id(NumberGenerator.getSaltString(8));
				User profile = new User();
				profile.setUser_idcard(pinjam.getIdCard());
				profile.setUser_phone(pinjam.getNoHp());
				profile.setUser_email(pinjam.getEmail());
				profile.setUser_frontname(pinjam.getNamaLengkap());
				User user = userService.doGetUserId(pinjam.getEmail());
				if (user == null) {
					boolean insert = userService.doInsert(profile);
					if (!insert) {
						status.setCode(HttpStatus.NOT_MODIFIED.value());
						status.setMessage("Fail");
						status.setDeveloperMessage("Insert user fail");
						return status;
					}
				}		
				VendorLokasi vendorLokasi = new VendorLokasi();
				vendorLokasi.setVendorloc_id(pinjam.getVendorLocationId());

				VendorUnit vendorUnit = new VendorUnit();
				vendorUnit.setVendorunit_id(pinjam.getVendorUnitId());

				Pesanan pesan = vendorService.getVendorUnitPesanById(pinjam.getVendorUnitId(),
						pinjam.getVendorLocationId());
				LOGGER.info(pesan);
				if (pesan == null) {
					status.setCode(HttpStatus.NOT_FOUND.value());
					status.setMessage("Fail");
					status.setDeveloperMessage("Please insert vendor unit");
					return status;
				}

				transaksiPinjam.setUser(user);
				transaksiPinjam.setVendorLokasi(vendorLokasi);
				transaksiPinjam.setVendorUnit(vendorUnit);
				transaksiPinjam.setTrxpinjam_tanggalmulai(pinjam.getStartDate());
				transaksiPinjam.setTrxpinjam_tanggalselesai(pinjam.getEndDate());
				transaksiPinjam.setTrxpinjam_detail(pinjam.getLokasiPenjemputan());
				transaksiPinjam.setTrxpinjam_specialrequest(pinjam.getNote());
				transaksiPinjam.setTrxpinjam_totalunit(pinjam.getTotalUnit());
				transaksiPinjam.setTrxpinjam_totalnominal(pesan.getNominal() * pinjam.getTotalUnit());
				transaksiPinjam.setTrxpinjam_kodebayar(NumberGenerator.genreateNumeric(2));
				long currentTimeMillis = System.currentTimeMillis();
				Date trx = new Date(currentTimeMillis);
				transaksiPinjam.setTrxpinjam_date(FORMAT_TRX.format(trx));

				boolean doInsert = transaksiPinjamService.insert(transaksiPinjam);
				if (doInsert) {
					HashMap<String, String> settings = settingService.settings();
					EmailTemplate emailTemplate = new EmailTemplate();
					Map<String, String> replacements = new HashMap<String, String>();
					replacements.put("logo",
							"<img style='width: 20%;' src=\"" + appConfig.getBaseApp() + "/assets/img/logo.png\"/>");
					replacements.put("vendor", pesan.getVendor());
					replacements.put("order_id", transaksiPinjam.getTrxpinjam_id());
					replacements.put("date", transaksiPinjam.getTrxpinjam_date());
					replacements.put("nama", pinjam.getNamaLengkap());
					replacements.put("idcard", pinjam.getIdCard() == null ? "0000000000000000"
							: pinjam.getIdCard());
					replacements.put("telp",
							pinjam.getNoHp() == null ? "-" : pinjam.getNoHp());
					replacements.put("email", pinjam.getEmail());
					replacements.put("nama_unit", pesan.getUnit());
					replacements.put("tanggal_mulai", transaksiPinjam.getTrxpinjam_tanggalmulai());
					replacements.put("tanggal_selesai", transaksiPinjam.getTrxpinjam_tanggalselesai());
					replacements.put("lokasi", pesan.getLokasi());
					replacements.put("unit", "" + transaksiPinjam.getTrxpinjam_totalunit());
					replacements.put("total_harga", "" + transaksiPinjam.getTrxpinjam_totalnominal());
					replacements.put("detail_lokasi", transaksiPinjam.getTrxpinjam_detail());
					replacements.put("jadwal", " <tr>"
							+ "<td style=\"border: 1px solid #ccc; font-size: 10px; text-align: left; padding: 5px;\">1</td>"
							+ "<td style=\"border: 1px solid #ccc; font-size: 10px; text-align: left; padding: 5px;\">"
							+ transaksiPinjam.getTrxpinjam_date() + "</td>"
							+ "<td style=\"border: 1px solid #ccc; font-size: 10px; text-align: left; padding: 5px;\">"
							+ transaksiPinjam.getTrxpinjam_totalunit() + "</td>"
							+ "<td style=\"border: 1px solid #ccc; font-size: 10px; text-align: left; padding: 5px;\">"
							+ pesan.getPaket() + "</td>"
							+ "<td style=\"border: 1px solid #ccc; font-size: 10px; text-align: left; padding: 5px;\">"
							+ transaksiPinjam.getTrxpinjam_totalnominal() + "</td>"
							+ "<td style=\"border: 1px solid #ccc; font-size: 10px; text-align: left; padding: 5px;\"><img src=\""
							+ appConfig.getBaseApp() + "/assets/img/not_ok.png\" style=\"width:20px;\"/></td>"
							+ "</tr>");
					replacements.put("keterangan",
							"<em style=\"font-size:9px;\">*) <img src=\"" + appConfig.getBaseApp()
									+ "/assets/img/ok.png\" style=\"width:15px;\"/> Sudah konfirmasi bayar</em><br/>\n"
									+ "<em style=\"font-size:9px;padding-left: 11px;\"><img src=\""
									+ appConfig.getBaseApp()
									+ "/assets/img/not_ok.png\" style=\"width:15px;\"/> Belum konfirmasi bayar</em>");
					emailTemplate.setTemplate(settings.get("template_email_sewa"));
					SimpleMailMessage message = new SimpleMailMessage();
					message.setTo(pinjam.getEmail());
					message.setCc(settings.get("email_cs"));
					message.setSubject("Informasi frent.id");
					emailService.sendSimpleMessageUsingTemplate(message, emailTemplate.getTemplate(replacements), true);

					long limit_payment = Long.parseLong(settingService.settings().get("limit_pembayaran")) * 60;// seconds

					Pesanan pesanan = new Pesanan();
					pesanan.setId(transaksiPinjam.getTrxpinjam_id());
					pesanan.setTanggalmulai(transaksiPinjam.getTrxpinjam_tanggalmulai());
					pesanan.setTanggalselesai(transaksiPinjam.getTrxpinjam_tanggalselesai());
					pesanan.setDetail(transaksiPinjam.getTrxpinjam_detail());
					pesanan.setSpecialrequest(transaksiPinjam.getTrxpinjam_specialrequest());
					pesanan.setTotalunit(transaksiPinjam.getTrxpinjam_totalunit());
					pesanan.setTotalnominal(transaksiPinjam.getTrxpinjam_totalnominal());
					pesanan.setDate(transaksiPinjam.getTrxpinjam_date());
					pesanan.setKodebayar(transaksiPinjam.getTrxpinjam_kodebayar());
					pesanan.setDriver(pesan.isDriver());
					pesanan.setBbm(pesan.isBbm());
					pesanan.setTollparking(pesan.isTollparking());
					pesanan.setNominal(pesan.getNominal());
					pesanan.setUnit(pesan.getUnit());
					pesanan.setThumbnail(appConfig.getImgUnit() + pesan.getThumbnail());
					pesanan.setBaggage(pesan.getBaggage());
					pesanan.setPassenger(pesan.getPassenger());
					pesanan.setVendor(pesan.getVendor());
					pesanan.setLokasi(pesan.getLokasi());
					pesanan.setPaket(pesan.getPaket());
					pesanan.setDurasi(currentTimeMillis + limit_payment);
					pesanan.setStatus("Waiting payment");
					status.setMessage("Success.");
					status.setData(pesanan);
				} else {
					status.setMessage("Fail.");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (e.getMessage().contains("Expiration Time")) {
				status.setCode(HttpStatus.UNAUTHORIZED.value());
				status.setMessage("Token have expired!");
			} else {
				status.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
				status.setMessage("Fail.");
				status.setDeveloperMessage(e.getMessage());
			}
		}
		return status;
	}

}
