package com.frent.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import com.frent.helper.Message;
import com.frent.model.User;
import com.frent.service.UserService;
import com.frent.util.AppConfig;
import com.frent.util.KeyGenerator;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Tokeninfo;
import com.google.api.services.oauth2.model.Userinfoplus;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@Controller
@RequestMapping("service/social")
public class LoginController {

	private final Logger LOGGER = LogManager.getLogger(LoginController.class);

	@Autowired
	AppConfig appConfig;
	@Autowired
	UserService userService;

	/** Global instance of the HTTP transport. */
	private static HttpTransport httpTransport;

	/** Global instance of the JSON factory. */
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

	private static Oauth2 oauth2;

	@RequestMapping(value = "/facebook", method = RequestMethod.GET)
	public @ResponseBody Message<User> loginFacebook(@RequestParam(value = "accessToken") String accessToken,
			UriComponentsBuilder uriComponentsBuilder) {
		LOGGER.debug(accessToken);
		Message<User> status = new Message<>();
		status.setCode(HttpStatus.OK.value());
		status.setLink(uriComponentsBuilder.path("/service/social").build().toUriString());
		try {
			String URL = "https://graph.facebook.com/v2.12/me?fields=id,email,first_name,last_name,picture&access_token="
					+ accessToken;
			LOGGER.debug(URL);
			Client client = Client.create();
			WebResource webResource = client.resource(URL);
			ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
			LOGGER.debug("Status: " + response.getStatus());
			if (response.getStatus() != 200) {
				status.setCode(response.getStatus());
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
			String output = response.getEntity(String.class);
			LOGGER.debug(output);
			User user = new User();
			JSONObject object = new JSONObject(output);
			user.setUser_email(object.getString("email").replaceAll("\u0040", "@"));
//			user.setUser_photo(
//					object.getJSONObject("picture").getJSONObject("data").getString("url").replaceAll("'\'", ""));
			user.setUser_photo("https://graph.facebook.com/"+object.get("id").toString()+"/picture?type=large");
			user.setUser_frontname(object.getString("first_name"));
			user.setUser_lastname(object.getString("last_name"));
			user.setUser_token(KeyGenerator.GetApiKey());
			User doGetUserId = userService.doGetUserId(user.getUser_email());
			LOGGER.debug(doGetUserId);
			if (doGetUserId != null) {
				status.setData(doGetUserId);
				status.setCode(HttpStatus.OK.value());
			} else {
				boolean doInsert = userService.doInsert(user);
				if (doInsert) {
					User id = userService.doGetUserId(user.getUser_email());
					if (id != null) {
						user.setUser_id(id.getUser_id());
						status.setData(user);
						status.setCode(HttpStatus.OK.value());
					} else {
						status.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
					}
				} else {
					status.setMessage("Insert data fail.");
					status.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
				}
			}
		} catch (Exception e) {
			status.setDeveloperMessage(e.getMessage());
			status.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			e.printStackTrace();
		}
		return status;
	}

	@RequestMapping(value = "/google", method = RequestMethod.GET)
	public @ResponseBody Message<User> loginGoogle(@RequestParam(value = "accessToken") String accessToken,
			UriComponentsBuilder uriComponentsBuilder) {
		LOGGER.debug("token: " + accessToken);
		Message<User> status = new Message<>();
		status.setCode(HttpStatus.OK.value());
		status.setLink(uriComponentsBuilder.path("/service/social").build().toUriString());
		try {

			httpTransport = GoogleNetHttpTransport.newTrustedTransport();
			GoogleCredential credential = new GoogleCredential().setAccessToken(accessToken);
			oauth2 = new Oauth2.Builder(httpTransport, JSON_FACTORY, credential).setApplicationName("Frent-id").build();
			Tokeninfo tokeninfo = oauth2.tokeninfo().setAccessToken(accessToken).execute();
			LOGGER.debug(tokeninfo.toPrettyString());
			Userinfoplus userinfo = oauth2.userinfo().get().execute();
			LOGGER.debug(userinfo.toPrettyString());
			if (tokeninfo.getAudience().equals(appConfig.getGoogleAppid())) {
				User user = new User();
				user.setUser_email(tokeninfo.getEmail());
				user.setUser_frontname(userinfo.getGivenName());
				user.setUser_lastname(userinfo.getFamilyName());
				user.setUser_photo(userinfo.getPicture());
				user.setUser_token(KeyGenerator.GetApiKey());

				User doGetUserId = userService.doGetUserId(tokeninfo.getEmail());
				LOGGER.debug(doGetUserId);
				if (doGetUserId != null) {
					status.setData(doGetUserId);
				} else {
					boolean doInsert = userService.doInsert(user);
					if (doInsert) {
						User id = userService.doGetUserId(user.getUser_email());
						if (id != null) {
							LOGGER.debug(id);
							status.setData(id);
							status.setCode(HttpStatus.OK.value());
						} else {
							status.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
						}
					} else {
						status.setMessage("Insert data fail.");
						status.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
					}
				}
			} else {
				LOGGER.debug("Token missmatch");
				status.setMessage("Invalid Id Token");
				status.setCode(HttpStatus.UNAUTHORIZED.value());
			}
		} catch (Exception e) {
			status.setDeveloperMessage(e.getMessage());
			status.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			e.printStackTrace();
		}
		return status;
	}
}
