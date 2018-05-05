/**
 * @author odeng
 *
 * Copyright © 2017 All Rights Reserved
 *
 */
package com.frent.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jwk.JsonWebKey;
import org.jose4j.jwk.PublicJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.keys.PbkdfKey;
import org.jose4j.lang.JoseException;

public class KeyGenerator {
	
	private final static Logger LOGGER = Logger.getLogger(KeyGenerator.class);

	private static JsonWebKey jsonWebKey;
	private static String keyId;
	private static PublicJsonWebKey webKey;

	static {
		try {
			if (webKey == null) {
//				System.out.println("path :" + System.getProperty("user.dir"));
				// ClassLoader classLoader = KeyGenerator.class.getClassLoader();
				// File file = new File(classLoader.getResource("../secret.key").getFile());
				InputStream inputStream = KeyGenerator.class.getResourceAsStream("/secret.key");
				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
				String temp = null, result = "";
				while ((temp = reader.readLine()) != null) {
//					System.out.println(temp);
					result = temp;
				}
				reader.close();
				JsonWebEncryption decryptingJwe = new JsonWebEncryption();
				decryptingJwe.setCompactSerialization(result);
				decryptingJwe.setKey(new PbkdfKey("od3ng@)!&"));
				String payload = decryptingJwe.getPayload();
				webKey = PublicJsonWebKey.Factory.newPublicJwk(payload);
				webKey.setKeyId(UUID.randomUUID().toString().toUpperCase());
				// share the public part with whomever/whatever needs to verify the signatures
				LOGGER.debug(webKey.toJson(JsonWebKey.OutputControlLevel.PUBLIC_ONLY));
				//System.out.println(webKey.toJson(JsonWebKey.OutputControlLevel.PUBLIC_ONLY));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static JsonWebKey produced() {
		try {
			if (jsonWebKey == null) {
				jsonWebKey = RsaJwkGenerator.generateJwk(2048);
				keyId = UUID.randomUUID().toString();
				// keyId = "68cb8b18-7959-48fa-9b2e-0cf864746894";
				// System.out.println(keyId);
				jsonWebKey.setKeyId(keyId);
			}
		} catch (JoseException e) {
			e.printStackTrace();
		}
		return jsonWebKey;
	}

	public static String GetApiKey() throws Exception {
		try {
			// Create the Claims, which will be the content of the JWT
			JwtClaims claims = new JwtClaims();
			claims.setIssuer("frent.id"); // who creates the token and signs it
			claims.setExpirationTimeMinutesInTheFuture(60 * 24 * 15); // token will expire (15 days from now)
			claims.setGeneratedJwtId(); // a unique identifier for the token
			claims.setIssuedAtToNow(); // when the token was issued/created (now)
			claims.setNotBeforeMinutesInThePast(2); // time before which the token is not yet valid (2 minutes ago)
			claims.setSubject("api-rent"); // the subject/principal is whom the token is about
			// claims.setStringListClaim("roles", "normal"); // multi-valued claims for
			// roles

			JsonWebSignature jws = new JsonWebSignature();

			jws.setPayload(claims.toJson());

			jws.setKeyIdHeaderValue(webKey.getKeyId());
			jws.setKey(webKey.getPrivateKey());

			jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);

			String jwt = null;
			try {
				jwt = jws.getCompactSerialization();
				LOGGER.debug(jwt);
//				System.out.println(jwt);
			} catch (JoseException e) {
				throw e;
			}
			return jwt;
		} catch (Exception e) {
			throw e;
		}
	}

	public static boolean isValid(String token) throws Exception {
		try {
			// Validate Token's authenticity and check claims
			JwtConsumer jwtConsumer = new JwtConsumerBuilder().setRequireExpirationTime()
					.setAllowedClockSkewInSeconds(30).setRequireSubject().setExpectedIssuer("frent.id")
					.setVerificationKey(webKey.getKey()).build();
			// Validate the JWT and process it to the Claims
			JwtClaims jwtClaims = jwtConsumer.processToClaims(token);
			LOGGER.debug(jwtClaims);
//			System.out.println(jwtClaims);
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

}
