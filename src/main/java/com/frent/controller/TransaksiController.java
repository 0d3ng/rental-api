/**
 * @author odeng
 *
 * Copyright © 2017 All Rights Reserved
 *
 */
package com.frent.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.frent.helper.CariFilter;
import com.frent.helper.Konfirmasi;
import com.frent.helper.Message;
import com.frent.helper.MessageStatus;
import com.frent.helper.Pesanan;
import com.frent.helper.TransaksiBatalHelper;
import com.frent.helper.TransaksiKomentarHelper;
import com.frent.helper.TransaksiPinjamHelper;
import com.frent.helper.TransaksiVendorHelper;
import com.frent.model.TransaksiBatal;
import com.frent.model.TransaksiKembali;
import com.frent.model.TransaksiKomentar;
import com.frent.model.TransaksiKonfirmasi;
import com.frent.model.TransaksiPinjam;
import com.frent.model.User;
import com.frent.model.VendorLokasi;
import com.frent.model.VendorUnit;
import com.frent.service.EmailService;
import com.frent.service.SettingService;
import com.frent.service.TransaksiBatalService;
import com.frent.service.TransaksiKembaliService;
import com.frent.service.TransaksiKomentarService;
import com.frent.service.TransaksiKonfirmasiService;
import com.frent.service.TransaksiPinjamService;
import com.frent.service.UserService;
import com.frent.service.VendorService;
import com.frent.util.AppConfig;
import com.frent.util.EmailTemplate;
import com.frent.util.KeyGenerator;
import com.frent.util.NumberGenerator;
import com.frent.util.StringUtils;

@Controller
@RequestMapping("service/transaksi")
public class TransaksiController {

	private final Logger LOGGER = Logger.getLogger(getClass());

	@Autowired
	TransaksiBatalService transaksiBatalService;
	@Autowired
	TransaksiKomentarService transaksiKomentarService;
	@Autowired
	TransaksiKonfirmasiService transaksiKonfirmasiService;
	@Autowired
	TransaksiPinjamService transaksiPinjamService;
	@Autowired
	TransaksiKembaliService transaksiKembaliService;
	@Autowired
	VendorService vendorService;
	@Autowired
	SettingService settingService;
	@Autowired
	UserService userService;
	@Autowired
	EmailService emailService;
	@Autowired
	AppConfig appConfig;

	private final SimpleDateFormat FORMAT_TRX = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@RequestMapping(value = "/insertBatal", method = RequestMethod.POST)
	public @ResponseBody Message<TransaksiBatalHelper> doInsertBatal(@RequestHeader String token,
			@RequestBody TransaksiBatalHelper btl, UriComponentsBuilder uriComponentsBuilder) {
		LOGGER.info(btl);
		Message<TransaksiBatalHelper> status = new Message<>();
		status.setCode(HttpStatus.OK.value());
		status.setLink(uriComponentsBuilder.path("/service/transaksi/insertBatal").build().toUriString());

		try {
			if (KeyGenerator.isValid(token)) {
				TransaksiBatal batal = new TransaksiBatal();
				batal.setCancel_id(NumberGenerator.getSaltString(8));
				batal.setCancel_reason(btl.getAlasan());
				batal.setCancel_telp(btl.getTelp());
				batal.setCancel_user(btl.getNama());
				TransaksiPinjam pinjam = new TransaksiPinjam();
				pinjam.setTrxpinjam_id(btl.getTransaksiId());
				batal.setPinjam(pinjam);
				Date date = new Date();
				batal.setCancel_date(FORMAT_TRX.format(date));
				boolean doInsert = transaksiBatalService.insert(batal);
				if (doInsert) {
					btl.setId(batal.getCancel_id());
					btl.setTanggal(batal.getCancel_date());
					status.setData(btl);
					status.setMessage("Success.");
				} else {
					status.setMessage("Fail.");
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

	@RequestMapping(value = "/insertKomentar", method = RequestMethod.POST)
	public @ResponseBody Message<TransaksiKomentarHelper> doInsertKomentar(@RequestHeader String token,
			@RequestBody TransaksiKomentarHelper komentar, UriComponentsBuilder uriComponentsBuilder) {
		LOGGER.info(komentar);
		Message<TransaksiKomentarHelper> status = new Message<>();
		status.setCode(HttpStatus.OK.value());
		status.setLink(uriComponentsBuilder.path("/service/transaksi/insertKomentar").build().toUriString());
		try {
			if (KeyGenerator.isValid(token)) {
				TransaksiKomentar comment = new TransaksiKomentar();
				comment.setTestimoni_content(komentar.getIsi());
				comment.setTestimoni_rate(komentar.getRate());
				TransaksiPinjam pinjam = new TransaksiPinjam();
				pinjam.setTrxpinjam_id(komentar.getTransaksiId());
				User user = new User();
				user.setUser_id(Long.parseLong(komentar.getUserId()));
				comment.setPinjam(pinjam);
				comment.setUser(user);
				boolean doInsert = transaksiKomentarService.insert(comment);
				if (doInsert) {
					status.setMessage("Success.");
					komentar.setId(comment.getTestimoni_id());
					status.setData(komentar);
				} else {
					status.setMessage("Fail.");
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

	@RequestMapping(value = "/updateKomentar", method = RequestMethod.PUT)
	public @ResponseBody Message<TransaksiKomentarHelper> doUpdateKomentar(@RequestHeader String token,
			@RequestBody TransaksiKomentarHelper komentar, UriComponentsBuilder uriComponentsBuilder) {
		LOGGER.info(komentar);
		Message<TransaksiKomentarHelper> status = new Message<>();
		status.setCode(HttpStatus.OK.value());
		status.setLink(uriComponentsBuilder.path("/service/transaksi/updateKomentar").build().toUriString());
		try {
			if (KeyGenerator.isValid(token)) {
				TransaksiKomentar comment = new TransaksiKomentar();
				comment.setTestimoni_content(komentar.getIsi());
				comment.setTestimoni_id((int) komentar.getId());
				comment.setTestimoni_rate(komentar.getRate());
				TransaksiPinjam pinjam = new TransaksiPinjam();
				pinjam.setTrxpinjam_id(komentar.getTransaksiId());
				User user = new User();
				user.setUser_id(Long.parseLong(komentar.getUserId()));
				comment.setPinjam(pinjam);
				comment.setUser(user);
				comment.setTestimoni_create(new SimpleDateFormat("yyyy:MM:dd HH:mm:ss").format(new Date()));
				boolean doInsert = transaksiKomentarService.update(comment);
				if (doInsert) {
					status.setMessage("Success.");
					komentar.setIsi(comment.getTestimoni_content());
					komentar.setRate(comment.getTestimoni_rate());
					status.setData(komentar);
				} else {
					status.setMessage("Fail.");
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

	@RequestMapping(value = "/deleteKomentar", method = RequestMethod.DELETE)
	public @ResponseBody MessageStatus<String> doDeleteKomentar(@RequestHeader String token,
			@RequestBody TransaksiKomentar komentar, UriComponentsBuilder uriComponentsBuilder) {
		LOGGER.info(komentar);
		MessageStatus<String> status = new MessageStatus<>();
		status.setCode(HttpStatus.OK.value());
		status.setLink(uriComponentsBuilder.path("/service/transaksi/deleteKomentar").build().toUriString());
		try {
			if (KeyGenerator.isValid(token)) {
				boolean doInsert = transaksiKomentarService.delete(komentar);
				if (doInsert) {
					status.setMessage("Success.");
				} else {
					status.setMessage("Fail.");
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

	@RequestMapping(value = "/getKomentarById/{komentarId}", method = RequestMethod.GET)
	public @ResponseBody MessageStatus<TransaksiKomentar> doGetKomentarById(@RequestHeader String token,
			@PathVariable String komentarId, UriComponentsBuilder uriComponentsBuilder) {

		MessageStatus<TransaksiKomentar> status = new MessageStatus<>();
		status.setCode(HttpStatus.OK.value());
		status.setLink(
				uriComponentsBuilder.path("/service/transaksi/getKomentarById/" + komentarId).build().toUriString());
		try {

			if (KeyGenerator.isValid(token)) {
				TransaksiKomentar komentar = new TransaksiKomentar();
				komentar.setTestimoni_id(Integer.parseInt(komentarId));
				List<TransaksiKomentar> komentars = transaksiKomentarService.getById(komentar);
				if (!komentars.isEmpty()) {
					status.setData(komentars);
					status.setMessage("Success.");
				} else {
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

	@RequestMapping(value = "/getKomentarByIdTransaksi/{transaksiId}", method = RequestMethod.GET)
	public @ResponseBody MessageStatus<TransaksiKomentar> doGetKomentarByIdTransaksi(@RequestHeader String token,
			@PathVariable String transaksiId, UriComponentsBuilder uriComponentsBuilder) {

		MessageStatus<TransaksiKomentar> status = new MessageStatus<>();
		status.setCode(HttpStatus.OK.value());
		status.setLink(uriComponentsBuilder.path("/service/transaksi/getKomentarByIdTransaksi/" + transaksiId).build()
				.toUriString());
		try {

			if (KeyGenerator.isValid(token)) {
				TransaksiKomentar komentar = new TransaksiKomentar();
				TransaksiPinjam pinjam = new TransaksiPinjam();
				pinjam.setTrxpinjam_id(transaksiId);
				komentar.setPinjam(pinjam);
				List<TransaksiKomentar> komentars = transaksiKomentarService.getByIdTransaksi(komentar);
				if (!komentars.isEmpty()) {
					status.setData(komentars);
					status.setMessage("Success.");
				} else {
					status.setMessage("Data is empty.");
					status.setDeveloperMessage("Please entry data.");
					status.setCode(HttpStatus.NOT_FOUND.value());
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

	@RequestMapping(value = "/insertKonfirmasi", method = RequestMethod.POST)
	public @ResponseBody Message<Konfirmasi> doInsertKonfirmasi(@RequestHeader String token,
			@RequestBody Konfirmasi konfirmasi, UriComponentsBuilder uriComponentsBuilder) {
		LOGGER.info(konfirmasi);
		Message<Konfirmasi> status = new Message<>();
		status.setCode(HttpStatus.OK.value());
		status.setLink(uriComponentsBuilder.path("/service/transaksi/insertKonfirmasi").build().toUriString());
		try {
			if (KeyGenerator.isValid(token)) {

				TransaksiKonfirmasi konf = new TransaksiKonfirmasi();
				User user = new User();
				user.setUser_id(konfirmasi.getUserId());
				TransaksiPinjam pinjam = new TransaksiPinjam();
				pinjam.setTrxpinjam_id(konfirmasi.getTransaksiId());
				konf.setPinjam(pinjam);
				konf.setUser(user);
				konf.setConfirmation_image(konfirmasi.getImage());
				konf.setConfirmation_transfer(konfirmasi.getNote());
				konf.setConfirmation_bank(konfirmasi.getBank());
				Date date = new Date();
				konf.setConfirmation_date(FORMAT_TRX.format(date));
				boolean doInsert = transaksiKonfirmasiService.insert(konf);
				if (doInsert) {
					User doRequestProfile = userService.doRequestProfile("" + konfirmasi.getUserId());
					TransaksiPinjam transaksiById = transaksiPinjamService
							.getTransaksiById(konfirmasi.getTransaksiId());
					HashMap<String, String> settings = settingService.settings();
					EmailTemplate emailTemplate = new EmailTemplate();
					Map<String, String> replacements = new HashMap<String, String>();
					replacements.put("logo", appConfig.getBaseApp() + "/assets/img/logo.png");
					replacements.put("order_id", konfirmasi.getTransaksiId());
					replacements.put("nama", doRequestProfile.getUser_frontname());
					replacements.put("harga", "" + transaksiById.getTrxpinjam_totalnominal());
					replacements.put("company", settings.get("instansi"));
					replacements.put("tanggal_pinjam", transaksiById.getTrxpinjam_date());
					replacements.put("pengguna", doRequestProfile.getUser_frontname());
					replacements.put("bayar", "Transfer");
					replacements.put("unit", transaksiById.getVendorUnit().getUnit().getUnit_name());
					replacements.put("vendor", transaksiById.getVendorUnit().getVendor().getVendor_name());
					replacements.put("kode-bayar", "" + transaksiById.getTrxpinjam_kodebayar());
					replacements.put("total-harga",
							"" + transaksiById.getTrxpinjam_totalnominal() + transaksiById.getTrxpinjam_kodebayar());
					emailTemplate.setTemplate(settings.get("template_email_pembayaran"));
					SimpleMailMessage message = new SimpleMailMessage();
					message.setTo(doRequestProfile.getUser_email());
					message.setCc(settings.get("email_cs"));
					message.setSubject("Informasi frent.id");
					emailService.sendSimpleMessageUsingTemplate(message, emailTemplate.getTemplate(replacements), true);
					konfirmasi.setTanggal(konf.getConfirmation_date());
					status.setData(konfirmasi);
					status.setMessage("Success.");
				} else {
					status.setMessage("Fail.");
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

	@RequestMapping(value = "/insertKembali", method = RequestMethod.POST)
	public @ResponseBody Message<TransaksiKembali> doInsertKembali(@RequestHeader String token,
			@RequestBody TransaksiKembali kembali, UriComponentsBuilder uriComponentsBuilder) {
		LOGGER.info(kembali);
		Message<TransaksiKembali> status = new Message<>();
		status.setCode(HttpStatus.OK.value());
		status.setLink(uriComponentsBuilder.path("/service/transaksi/insertKembali").build().toUriString());
		try {
			if (KeyGenerator.isValid(token)) {
				Date date = new Date();
				kembali.setTanggal(FORMAT_TRX.format(date));
				boolean doInsert = transaksiKembaliService.insert(kembali);
				if (doInsert) {
					status.setData(kembali);
					status.setMessage("Success.");
				} else {
					status.setMessage("Fail.");
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

	@RequestMapping(value = "/getKembaliByUserId/{userId}", method = RequestMethod.GET)
	public @ResponseBody MessageStatus<TransaksiKembali> doGetKembaliByUserId(@RequestHeader String token,
			@PathVariable String userId, UriComponentsBuilder uriComponentsBuilder) {

		MessageStatus<TransaksiKembali> status = new MessageStatus<>();
		status.setCode(HttpStatus.OK.value());
		status.setLink(
				uriComponentsBuilder.path("/service/transaksi/getKembaliByUserId/" + userId).build().toUriString());
		try {

			if (KeyGenerator.isValid(token)) {
				List<TransaksiKembali> geTransaksiKembalisByUserId = transaksiKembaliService
						.geTransaksiKembalisByUserId(Integer.parseInt(userId));
				if (!geTransaksiKembalisByUserId.isEmpty()) {
					status.setData(geTransaksiKembalisByUserId);
					status.setMessage("Success.");
				} else {
					status.setMessage("Data is empty.");
					status.setDeveloperMessage("Please entry data.");
				}
			} else {
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

	@RequestMapping(value = "/getPinjamByUserId/{userId}", method = RequestMethod.GET)
	public @ResponseBody MessageStatus<Pesanan> doGetPinjamByUserId(@RequestHeader String token,
			@PathVariable String userId, UriComponentsBuilder uriComponentsBuilder) {

		MessageStatus<Pesanan> status = new MessageStatus<>();
		status.setCode(HttpStatus.OK.value());
		status.setLink(
				uriComponentsBuilder.path("/service/transaksi/getPinjamByUserId/" + userId).build().toUriString());
		try {

			if (KeyGenerator.isValid(token)) {
				List<Pesanan> geTransaksiKembalisByUserId = transaksiPinjamService.pinjamsByUserId(userId);
				List<Pesanan> pesanans = new ArrayList<>();
				for (Pesanan pesanan : geTransaksiKembalisByUserId) {
					pesanan.setThumbnail("assets/img/units/" + pesanan.getThumbnail());
					Date pick = FORMAT_TRX.parse(pesanan.getDate());
					long substractTime = Math.abs(StringUtils.substractTime(pick, new Date()));
					if (pesanan.getStatus() == null) {
						long limit_payment = Long.parseLong(settingService.settings().get("limit_pembayaran")) * 60;// seconds
						if (substractTime <= limit_payment) {
							pesanan.setStatus("Waiting payment");
						} else {
							pesanan.setStatus("Expired");
						}
					} else {
						if (pesanan.getStatus().equals("1")) {
							pesanan.setStatus("Completed");
						} else if (pesanan.getStatus().equals("0")) {
							pesanan.setStatus("Checking");
						} else {
							pesanan.setStatus("Unknown");
						}
					}

					pesanans.add(pesanan);
				}
				if (!geTransaksiKembalisByUserId.isEmpty()) {
					status.setData(pesanans);
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

	@RequestMapping(value = "/getPinjamByVendorId/{id}", method = RequestMethod.GET)
	public @ResponseBody MessageStatus<TransaksiVendorHelper> doGetPinjamByVendorId(@RequestHeader String token,
			@PathVariable String id, UriComponentsBuilder uriComponentsBuilder) {

		MessageStatus<TransaksiVendorHelper> status = new MessageStatus<>();
		status.setCode(HttpStatus.OK.value());
		status.setLink(uriComponentsBuilder.path("/service/transaksi/getPinjamByVendorId/" + id).build().toUriString());
		try {

			if (KeyGenerator.isValid(token)) {
				List<TransaksiVendorHelper> allTranskasiByVendorId = transaksiPinjamService
						.getAllTranskasiByVendorId(Integer.parseInt(id));
				if (!allTranskasiByVendorId.isEmpty()) {
					status.setData(allTranskasiByVendorId);
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

	@RequestMapping(value = "/getKonfirmasiById/{konfirmasiId}", method = RequestMethod.GET)
	public @ResponseBody MessageStatus<TransaksiKonfirmasi> doGetKonfirmasiById(@RequestHeader String token,
			@PathVariable String konfirmasiId, UriComponentsBuilder uriComponentsBuilder) {

		MessageStatus<TransaksiKonfirmasi> status = new MessageStatus<>();
		status.setCode(HttpStatus.OK.value());
		status.setLink(uriComponentsBuilder.path("/service/transaksi/getKonfirmasiById/" + konfirmasiId).build()
				.toUriString());
		try {

			if (KeyGenerator.isValid(token)) {
				TransaksiKonfirmasi konfirmasi = new TransaksiKonfirmasi();
				konfirmasi.setConfirmation_id(Integer.parseInt(konfirmasiId));
				List<TransaksiKonfirmasi> konfirmasis = transaksiKonfirmasiService.getById(konfirmasi);
				if (!konfirmasis.isEmpty()) {
					status.setData(konfirmasis);
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

	@RequestMapping(value = "/getKonfirmasiByIdTransaksi/{transaksiId}", method = RequestMethod.GET)
	public @ResponseBody MessageStatus<TransaksiKonfirmasi> doGetKonfirmasiByIdTransaksi(@RequestHeader String token,
			@PathVariable String transaksiId, UriComponentsBuilder uriComponentsBuilder) {

		MessageStatus<TransaksiKonfirmasi> status = new MessageStatus<>();
		status.setCode(HttpStatus.OK.value());
		status.setLink(uriComponentsBuilder.path("/service/transaksi/getKonfirmasiByIdTransaksi/" + transaksiId).build()
				.toUriString());
		try {

			if (KeyGenerator.isValid(token)) {
				TransaksiKonfirmasi konfirmasi = new TransaksiKonfirmasi();
				TransaksiPinjam pinjam = new TransaksiPinjam();
				pinjam.setTrxpinjam_id(transaksiId);
				konfirmasi.setPinjam(pinjam);
				List<TransaksiKonfirmasi> konfirmasis = transaksiKonfirmasiService.getByIdTransaksi(konfirmasi);
				if (!konfirmasis.isEmpty()) {
					status.setData(konfirmasis);
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

	@RequestMapping(value = "/getKonfirmasiByUserId/{userId}", method = RequestMethod.GET)
	public @ResponseBody MessageStatus<TransaksiKonfirmasi> doGetKonfirmasiByUserId(@RequestHeader String token,
			@PathVariable String userId, UriComponentsBuilder uriComponentsBuilder) {

		MessageStatus<TransaksiKonfirmasi> status = new MessageStatus<>();
		status.setCode(HttpStatus.OK.value());
		status.setLink(
				uriComponentsBuilder.path("/service/transaksi/getKonfirmasiByUserId/" + userId).build().toUriString());
		try {

			if (KeyGenerator.isValid(token)) {
				TransaksiKonfirmasi konfirmasi = new TransaksiKonfirmasi();
				TransaksiPinjam pinjam = new TransaksiPinjam();
				pinjam.setTrxpinjam_id(userId);
				konfirmasi.setPinjam(pinjam);
				List<TransaksiKonfirmasi> konfirmasis = transaksiKonfirmasiService.getByIdTransaksi(konfirmasi);
				if (!konfirmasis.isEmpty()) {
					status.setData(konfirmasis);
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

	@RequestMapping(value = "/insertPinjam", method = RequestMethod.POST)
	public @ResponseBody Message<Pesanan> doInsertPinjam(@RequestHeader String token,
			@RequestBody TransaksiPinjamHelper pinjam, UriComponentsBuilder uriComponentsBuilder) {
		LOGGER.debug(pinjam);
		Message<Pesanan> status = new Message<>();
		status.setCode(HttpStatus.OK.value());
		status.setLink(uriComponentsBuilder.path("/service/transaksi/insertPinjam").build().toUriString());
		try {
			if (KeyGenerator.isValid(token)) {
				TransaksiPinjam transaksiPinjam = new TransaksiPinjam();
				transaksiPinjam.setTrxpinjam_id(NumberGenerator.getSaltString(8));
				User profile = userService.doRequestProfile(""+pinjam.getUserId());
				if (profile == null) {
					status.setCode(HttpStatus.NOT_FOUND.value());
					status.setMessage("Fail");
					status.setDeveloperMessage("User do not exsist");
					return status;
				}
				profile.setUser_idcard(pinjam.getIdCard());
				profile.setUser_phone(pinjam.getNoHp());
				profile.setUser_email(pinjam.getEmail());
				profile.setUser_frontname(pinjam.getNamaLengkap());

				VendorLokasi vendorLokasi = new VendorLokasi();
				vendorLokasi.setVendorloc_id(pinjam.getVendorLocationId());

				VendorUnit vendorUnit = new VendorUnit();
				vendorUnit.setVendorunit_id(pinjam.getVendorUnitId());

				Pesanan pesan = vendorService.getVendorUnitPesanById(pinjam.getVendorUnitId(),
						pinjam.getVendorLocationId());
				LOGGER.debug(pesan);
				if (pesan == null) {
					status.setCode(HttpStatus.NOT_FOUND.value());
					status.setMessage("Fail");
					status.setDeveloperMessage("Please insert vendor unit");
					return status;
				}
				LOGGER.debug(profile);
				if (userService.doUpdate(profile)) {
					transaksiPinjam.setUser(profile);
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
					LOGGER.debug(transaksiPinjam);
					if (doInsert) {
						User doRequestProfile = userService.doRequestProfile("" + pinjam.getUserId());
						HashMap<String, String> settings = settingService.settings();
						EmailTemplate emailTemplate = new EmailTemplate();
						Map<String, String> replacements = new HashMap<String, String>();
						replacements.put("logo", "<img style='width: 20%;' src=\"" + appConfig.getBaseApp()
								+ "/assets/img/logo.png\"/>");
						replacements.put("vendor", pesan.getVendor());
						replacements.put("order_id", transaksiPinjam.getTrxpinjam_id());
						replacements.put("date", transaksiPinjam.getTrxpinjam_date());
						replacements.put("nama", doRequestProfile.getUser_frontname());
						replacements.put("idcard", doRequestProfile.getUser_idcard()==null?"0000000000000000":doRequestProfile.getUser_idcard());
						replacements.put("telp",
								doRequestProfile.getUser_phone() == null ? "-" : doRequestProfile.getUser_phone());
						replacements.put("email", doRequestProfile.getUser_email());
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
						replacements.put("keterangan", "<em style=\"font-size:9px;\">*) <img src=\""
								+ appConfig.getBaseApp()
								+ "/assets/img/ok.png\" style=\"width:15px;\"/> Sudah konfirmasi bayar</em><br/>\n"
								+ "<em style=\"font-size:9px;padding-left: 11px;\"><img src=\"" + appConfig.getBaseApp()
								+ "/assets/img/not_ok.png\" style=\"width:15px;\"/> Belum konfirmasi bayar</em>");
						emailTemplate.setTemplate(settings.get("template_email_sewa"));
						SimpleMailMessage message = new SimpleMailMessage();
						message.setTo(doRequestProfile.getUser_email());
						message.setCc(settings.get("email_cs"));
						message.setSubject("Informasi frent.id");
							emailService.sendSimpleMessageUsingTemplate(message, emailTemplate.getTemplate(replacements),
								true);

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
				} else {
					status.setMessage("Fail.");
				}

			} else {
				status.setCode(HttpStatus.UNAUTHORIZED.value());
				status.setMessage("Auth token fail.");
				status.setDeveloperMessage("Check your token.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			if (e != null && e.getMessage().contains("Expiration Time")) {
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

	@RequestMapping(value = "/updatePinjam", method = RequestMethod.PUT)
	public @ResponseBody MessageStatus<String> doUpdatePinjam(@RequestHeader String token,
			@RequestBody String pinjamJson, UriComponentsBuilder uriComponentsBuilder) {
		ObjectMapper mapper = new ObjectMapper();
		LOGGER.info(pinjamJson);
		MessageStatus<String> status = new MessageStatus<>();
		status.setCode(HttpStatus.OK.value());
		status.setLink(uriComponentsBuilder.path("/service/transaksi/updatePinjam").build().toUriString());
		try {
			if (KeyGenerator.isValid(token)) {
				TransaksiPinjam pinjam = mapper.readValue(pinjamJson, TransaksiPinjam.class);
				boolean doInsert = transaksiPinjamService.update(pinjam);
				if (doInsert) {
					status.setMessage("Success.");
				} else {
					status.setMessage("Fail.");
				}
			} else {
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

	@RequestMapping(value = "/getAllPinjams", method = RequestMethod.GET)
	public @ResponseBody MessageStatus<TransaksiPinjam> doGetAllPinjams(@RequestHeader String token,
			UriComponentsBuilder uriComponentsBuilder) {

		MessageStatus<TransaksiPinjam> status = new MessageStatus<>();
		status.setCode(HttpStatus.OK.value());
		status.setLink(uriComponentsBuilder.path("/service/transaksi/getAllPinjams").build().toUriString());
		try {

			if (KeyGenerator.isValid(token)) {
				List<TransaksiPinjam> pinjams = transaksiPinjamService.pinjams();
				if (!pinjams.isEmpty()) {
					status.setData(pinjams);
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

	@RequestMapping(value = "/getPinjamByParam", method = RequestMethod.POST)
	public @ResponseBody MessageStatus<VendorUnit> doGetPinjamByKategori(@RequestHeader String token,
			@RequestBody CariFilter cariFilter, UriComponentsBuilder uriComponentsBuilder) {
		MessageStatus<VendorUnit> status = new MessageStatus<>();
		status.setCode(HttpStatus.OK.value());
		status.setLink(uriComponentsBuilder.path("/service/transaksi/getPinjamByParam").build().toUriString());
		try {
			LOGGER.info(cariFilter);
			if (KeyGenerator.isValid(token)) {

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

	@RequestMapping(value = "/upload_konfirmasi", method = RequestMethod.POST)
	public @ResponseBody Message<String> doUploadKonfirmasi(@RequestParam MultipartFile file,UriComponentsBuilder uriComponentsBuilder) {
		Message<String> status = new Message<>();
		status.setCode(HttpStatus.OK.value());
		status.setLink(uriComponentsBuilder.path("/service/transaksi/upload_konfirmasi").build().toUriString());
		try {
			LOGGER.debug(file.getOriginalFilename());
			if (file.isEmpty()) {
				status.setMessage("File is empty.");
				return status;
			}
			// Get the file and save it somewhere
			byte[] bytes = file.getBytes();
			String name = new SimpleDateFormat("yyyyMMddHHmmssSSSSSS").format(new Date());
			Path path = Paths.get(appConfig.getImgUploaded() + "img_" + name + file.getOriginalFilename()
					.substring(file.getOriginalFilename().indexOf("."), file.getOriginalFilename().length()));
			Files.write(path, bytes);
			status.setMessage("Success.");
			status.setData(appConfig.getBaseApp() + appConfig.getImg() + path.toFile().getName());
		} catch (Exception e) {
			status.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			status.setMessage("Fail.");
			status.setDeveloperMessage(e.getMessage());
			e.printStackTrace();
		}
		return status;
	}

	@RequestMapping(value = "/upload_return", method = RequestMethod.POST)
	public @ResponseBody Message<String> doUploadKembali(@RequestParam MultipartFile file,
			RedirectAttributes redirectAttributes, UriComponentsBuilder uriComponentsBuilder) {
		Message<String> status = new Message<>();
		status.setCode(HttpStatus.OK.value());
		status.setLink(uriComponentsBuilder.path("/service/transaksi/upload_return").build().toUriString());
		try {
			if (file.isEmpty()) {
				status.setMessage("File is empty.");
				return status;
			}
			// Get the file and save it somewhere
			byte[] bytes = file.getBytes();
			String name = new SimpleDateFormat("yyyyMMddHHmmssSSSSSS").format(new Date());
			Path path = Paths.get(appConfig.getImgUploaded() + "img_" + name + file.getOriginalFilename()
					.substring(file.getOriginalFilename().indexOf("."), file.getOriginalFilename().length()));
			Files.write(path, bytes);
			status.setMessage("Success.");
			status.setData(appConfig.getBaseApp() + appConfig.getImg() + path.toFile().getName());
		} catch (Exception e) {
			status.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			status.setMessage("Fail.");
			status.setDeveloperMessage(e.getMessage());
			e.printStackTrace();
		}
		return status;
	}

}
