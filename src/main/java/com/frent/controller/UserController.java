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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
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
import org.springframework.web.util.UriComponentsBuilder;

import com.frent.helper.Message;
import com.frent.helper.MessageStatus;
import com.frent.helper.Register;
import com.frent.helper.UserUpdate;
import com.frent.model.User;
import com.frent.service.EmailService;
import com.frent.service.SettingService;
import com.frent.service.UserService;
import com.frent.util.AppConfig;
import com.frent.util.EmailTemplate;
import com.frent.util.KeyGenerator;
import com.frent.util.MD5Utils;

@Controller
@RequestMapping("service/security")
public class UserController {

	private final Logger LOGGER = Logger.getLogger(getClass());

	@Autowired
	UserService userService;
	@Autowired
	EmailService emailService;
	@Autowired
	SettingService settingService;

	@Autowired
	AppConfig appConfig;

//	@Bean(name = "multipartResolver")
//	public CommonsMultipartResolver commonsMultipartResolver() {
//		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
//		multipartResolver.setMaxUploadSize(10485760);
//		return multipartResolver;
//	}

	@RequestMapping(value = "/userdummy", method = RequestMethod.GET)
	public @ResponseBody MessageStatus<User> doGenreateUser() {

		User user = new User();
		user.setUser_id(1L);
		user.setUser_email("lepengdados@gmail.com");
		user.setUser_password("1234");
		user.setUser_frontname("mas");
		user.setUser_lastname("odeng");
		user.setUser_tanggallahir(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		MessageStatus<User> status = new MessageStatus<>();
		status.setCode(HttpStatus.OK.value());
		try {
			List<User> users = new ArrayList<>();
			users.add(user);
			status.setData(users);
			status.setMessage("Auth success.");
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

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public @ResponseBody Message<User> doLogin(@RequestHeader String useremail, @RequestHeader String userpassword,
			UriComponentsBuilder uriComponentsBuilder) {
		LOGGER.info(useremail);
		LOGGER.info(userpassword);
		Message<User> status = new Message<>();
		status.setCode(HttpStatus.OK.value());
		status.setLink(uriComponentsBuilder.path("/service/security/login").build().toUriString());
		try {
			User doLogin = userService.doLogin(useremail, MD5Utils.getMD5(userpassword));
			if (doLogin != null) {
				if (doLogin.getUser_token() == null || doLogin.getUser_token().equals("")) {
					String apiKey = KeyGenerator.GetApiKey();
					doLogin.setUser_token(apiKey);
					if (userService.doUpdate(doLogin)) {
						status.setData(doLogin);
						status.setMessage("Login success.");
					} else {
						status.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
						status.setData(doLogin);
						status.setMessage("Genreate token fail.");
					}
				} else {
					status.setData(doLogin);
					status.setMessage("Login success.");
				}
			} else {
				status.setCode(HttpStatus.NOT_FOUND.value());
				status.setDeveloperMessage("Check your username or password.");
				status.setMessage("Login fail.");
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

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public @ResponseBody Message<User> doInserUser(@RequestBody Register register,
			UriComponentsBuilder uriComponentsBuilder) {
		LOGGER.info(register);
		Message<User> status = new Message<>();
		status.setCode(HttpStatus.OK.value());
		status.setLink(uriComponentsBuilder.path("/service/security/insert").build().toUriString());
		User user = new User();
		try {
			user.setUser_email(register.getEmail());
			user.setUser_password(MD5Utils.getMD5(register.getPassword()));
			user.setUser_idcard(register.getIdcard());
			user.setUser_frontname(register.getFirstname());
			user.setUser_lastname(register.getLastname());
			user.setUser_photo(register.getPhoto());
			user.setUser_photo_idcard(register.getPhoto_idcard());
			user.setUser_jk(register.getJk());
			user.setUser_tanggallahir(register.getTanggallahir());
			user.setUser_phone(register.getPhone());
			user.setUser_address(register.getAddress());
			String apiKey = KeyGenerator.GetApiKey();
			user.setUser_token(apiKey);
			boolean doInsert = userService.doInsert(user);
			if (doInsert) {
				HashMap<String, String> settings = settingService.settings();
				EmailTemplate emailTemplate = new EmailTemplate();
				Map<String, String> replacements = new HashMap<String, String>();
				replacements.put("logo",
						"<img style='width: 20%;' src=\"" + appConfig.getBaseApp() + "/assets/img/logo.png\"/>");
				replacements.put("company", settings.get("instansi"));
				replacements.put("nama", register.getFirstname());
				replacements.put("email", register.getEmail());
				replacements.put("password", register.getPassword());
				emailTemplate.setTemplate(settings.get("template_email_register"));
				SimpleMailMessage message = new SimpleMailMessage();
				message.setTo(register.getEmail());
				message.setCc(settings.get("email_cs"));
				message.setSubject("Informasi frent.id");
				emailService.sendSimpleMessageUsingTemplate(message, emailTemplate.getTemplate(replacements), true);

				status.setData(user);
				status.setMessage("Success.");
			} else {
				status.setCode(HttpStatus.NOT_MODIFIED.value());
				status.setMessage("Fail.");
			}
		} catch (Exception e) {
			if (e.getMessage().contains("Duplicate entry")) {
				User doGetUserId = userService.doGetUserId(register.getEmail());
				if (doGetUserId != null) {
					user.setUser_id(doGetUserId.getUser_id());
					boolean doUpdate = userService.doUpdate(user);
					if (doUpdate) {
						HashMap<String, String> settings = settingService.settings();
						EmailTemplate emailTemplate = new EmailTemplate();
						Map<String, String> replacements = new HashMap<String, String>();
						replacements.put("logo", "<img src=\"" + appConfig.getBaseApp() + "/assets/img/logo.png\"/>");
						replacements.put("company", settings.get("instansi"));
						replacements.put("nama", register.getFirstname());
						replacements.put("email", register.getEmail());
						replacements.put("password", register.getPassword());
						emailTemplate.setTemplate(settings.get("template_email_register"));
						SimpleMailMessage message = new SimpleMailMessage();
						message.setTo(register.getEmail());
						message.setCc(settings.get("email_cs"));
						message.setSubject("Informasi frent.id");
						try {
							emailService.sendSimpleMessageUsingTemplate(message,
									emailTemplate.getTemplate(replacements), true);
							status.setData(user);
							status.setMessage("Success.");
						} catch (Exception e1) {
							status.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
							status.setMessage("Fail.");
							status.setDeveloperMessage(e1.getMessage());
							e1.printStackTrace();
						}
					} else {
						status.setCode(HttpStatus.NOT_MODIFIED.value());
						status.setMessage("Fail.");
					}
				} else {
					status.setCode(HttpStatus.NOT_FOUND.value());
					status.setMessage("Fail.");
					status.setDeveloperMessage(e.getMessage());
				}
			} else {
				status.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
				status.setMessage("Fail.");
				status.setDeveloperMessage(e.getMessage());
				e.printStackTrace();
			}
		}
		return status;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public @ResponseBody MessageStatus<String> doUpdateUser(@RequestHeader String token,@RequestBody UserUpdate update,
			UriComponentsBuilder uriComponentsBuilder) {
		LOGGER.debug(update);
		MessageStatus<String> status = new MessageStatus<>();
		status.setCode(HttpStatus.OK.value());
		status.setLink(uriComponentsBuilder.path("/service/security/update").build().toUriString());
		try {
			if (KeyGenerator.isValid(token)) {
				boolean doUpdate = userService.doUpdateProfile(update);
				if (doUpdate) {
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

	@RequestMapping(value = "/request_token/{userId}", method = RequestMethod.GET)
	public @ResponseBody Message<String> doRequestToken(@RequestHeader String token, @PathVariable String userId,
			UriComponentsBuilder uriComponentsBuilder) {
		Message<String> status = new Message<String>();
		status.setCode(HttpStatus.OK.value());
		status.setLink(uriComponentsBuilder.path("/service/security/request_token/" + userId).build().toUriString());
		try {
			User user = userService.doRequestToken(userId, token);
			if (user != null) {
				String apiKey = KeyGenerator.GetApiKey();
				user.setUser_token(apiKey);
				LOGGER.info("Password: " + user.getUser_password());
				boolean doUpdate = userService.doUpdate(user);
				if (doUpdate) {
					status.setData(apiKey);
					status.setMessage("Success.");
				} else {
					status.setCode(HttpStatus.NOT_MODIFIED.value());
					status.setMessage("Fail.");
				}
			} else {
				status.setCode(HttpStatus.NOT_FOUND.value());
				status.setMessage("Check user id or token!");
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

	@RequestMapping(value = "/profile/{userId}", method = RequestMethod.GET)
	public @ResponseBody Message<User> doRequestProfile(@RequestHeader String token, @PathVariable String userId,
			UriComponentsBuilder uriComponentsBuilder) {
		Message<User> status = new Message<User>();
		status.setCode(HttpStatus.OK.value());
		status.setLink(uriComponentsBuilder.path("/service/security/profile/" + userId).build().toUriString());
		try {
			if (KeyGenerator.isValid(token)) {
				User user = userService.doRequestProfile(userId);
				if (user != null) {
					user.setUser_photo(appConfig.getBaseApp() + appConfig.getImg() + user.getUser_photo());
					status.setData(user);
					status.setMessage("Success.");
				} else {
					status.setMessage("Check user id!");
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

	@RequestMapping(value = "/upload_profile/{id}", method = RequestMethod.PUT)
	public @ResponseBody Message<String> doUploadProfile(@RequestParam MultipartFile file,@RequestHeader String token,@PathVariable String id, UriComponentsBuilder uriComponentsBuilder) {
		Message<String> status = new Message<>();
		status.setCode(HttpStatus.OK.value());
		status.setLink(uriComponentsBuilder.path("/service/security/upload_profile").build().toUriString());
		try {
			if (KeyGenerator.isValid(token)) {
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
				User user = new User();
				user.setUser_id(Long.parseLong(id));
				user.setUser_photo(appConfig.getBaseApp() + appConfig.getImg() + path.toFile().getName());
				if (userService.doUpdateFotoProfile(user)) {
					status.setMessage("Success.");
					status.setData(appConfig.getBaseApp() + appConfig.getImg() + path.toFile().getName());
				}else {
					status.setCode(HttpStatus.NOT_MODIFIED.value());
					status.setMessage("Fail.");
				}
			} else {
				status.setCode(HttpStatus.UNAUTHORIZED.value());
				status.setMessage("Auth token fail.");
				status.setDeveloperMessage("Check your token.");
			}
			
		} catch (Exception e) {
			status.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			status.setMessage("Fail.");
			status.setDeveloperMessage(e.getMessage());
			e.printStackTrace();
		}
		return status;
	}

	@RequestMapping(value = "/upload_identity/{id}", method = RequestMethod.PUT)
	public @ResponseBody Message<String> doUploadIdentity(@RequestParam MultipartFile file, @RequestHeader String token,@PathVariable String id,UriComponentsBuilder uriComponentsBuilder) {
		Message<String> status = new Message<>();
		status.setCode(HttpStatus.OK.value());
		status.setLink(uriComponentsBuilder.path("/service/security/upload_identity").build().toUriString());
		try {
			if (KeyGenerator.isValid(token)) {
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
				LOGGER.debug(path.toString());
				User user = new User();
				user.setUser_id(Long.parseLong(id));
				user.setUser_photo_idcard(appConfig.getBaseApp() + appConfig.getImg() + path.toFile().getName());
				if (userService.doUpdateFotoIdCard(user)) {
					status.setMessage("Success.");
					status.setData(appConfig.getBaseApp() + appConfig.getImg() + path.toFile().getName());
				}else {
					status.setCode(HttpStatus.NOT_MODIFIED.value());
					status.setMessage("Fail.");
				}
			} else {
				status.setCode(HttpStatus.UNAUTHORIZED.value());
				status.setMessage("Auth token fail.");
				status.setDeveloperMessage("Check your token.");
			}
			
		} catch (Exception e) {
			status.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			status.setMessage("Fail.");
			status.setDeveloperMessage(e.getMessage());
			e.printStackTrace();
		}
		return status;
	}

	@RequestMapping(value = "/forgot_password", method = RequestMethod.POST)
	public @ResponseBody Message<String> doResetPassword(@RequestParam(value = "email") String email,
			UriComponentsBuilder uriComponentsBuilder) {
		Message<String> status = new Message<>();
		status.setCode(HttpStatus.OK.value());
		status.setLink(uriComponentsBuilder.path("/service/security/forgot_password").build().toUriString());
		try {
			User doGetUserId = userService.doGetUserId(email);
			LOGGER.info(doGetUserId);
			if (doGetUserId != null) {
				HashMap<String, String> settings = settingService.settings();
				EmailTemplate emailTemplate = new EmailTemplate();
				Map<String, String> replacements = new HashMap<String, String>();
				String data = "" + email + "|" + new Date().getTime();
				replacements.put("logo", "<img style='width: 20%;' src=\""+appConfig.getBaseApp()+"/assets/img/logo.png\"/>");
				replacements.put("company", settings.get("instansi"));
				replacements.put("nama", doGetUserId.getUser_frontname());
				replacements.put("limit-reset", settings.get("limit_url_reset_password"));
				replacements.put("url-reset", appConfig.getUrlReset()+"?d="
						+ new String(Base64.encodeBase64(data.getBytes())));
				emailTemplate.setTemplate(settings.get("template_email_reset_password"));
				SimpleMailMessage message = new SimpleMailMessage();
				message.setTo(email);
				message.setCc(email);
				message.setSubject("Informasi frent.id");
				emailService.sendSimpleMessageUsingTemplate(message, emailTemplate.getTemplate(replacements), true);
				status.setMessage("Success.");
				status.setData(appConfig.getUrlReset()+"?d="
						+ new String(Base64.encodeBase64(data.getBytes())));
			} else {
				status.setCode(HttpStatus.NOT_FOUND.value());
			}
		} catch (Exception e) {
			status.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			status.setMessage("Fail.");
			status.setDeveloperMessage(e.getMessage());
			e.printStackTrace();
		}
		return status;
	}

}
