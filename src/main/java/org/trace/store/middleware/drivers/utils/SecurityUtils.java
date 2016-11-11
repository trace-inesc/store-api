/*******************************************************************************
 * Copyright (c) 2016 Rodrigo Lourenço, Miguel Costa, Paulo Ferreira, João Barreto @  INESC-ID. 
 *  
 * This file is part of TRACE.
 *
 * TRACE is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * TRACE is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with TRACE.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package org.trace.store.middleware.drivers.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Random;

import org.apache.commons.codec.binary.Hex;
import org.joda.time.DateTime;

public class SecurityUtils {
	
	private static final Random r = new SecureRandom();
	
	public static byte[] generateSalt(int byteSize){
		byte[] salt = new byte[byteSize];
		r.nextBytes(salt);
		return salt;
	}
	
	public static byte[] getSecureHash(int iterations, String password, byte[] salt) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		MessageDigest digest = MessageDigest.getInstance("SHA-1");
		digest.reset();
		digest.update(salt);
		byte[] hash = digest.digest(password.getBytes("UTF-8"));
		for(int i=0; i < iterations; i++){ //Hardens brute-force attacks
			digest.reset();
			hash = digest.digest(hash);
		}
		
		return hash;
	}
	
	public static Date getExpirationDate(int days){
		Date dt = new Date();
		DateTime dtOrg = new DateTime(dt);
		DateTime dtPlusOne = dtOrg.plusDays(days);
		return dtPlusOne.toDate();
	}
	
	public static String generateSecureActivationToken(int size){
		byte[] token = new byte[size];
		r.nextBytes(token);
		return Hex.encodeHexString(token);
	}
	
	public static byte[] hashSHA1(String s) throws NoSuchAlgorithmException{
		MessageDigest digest = MessageDigest.getInstance("SHA-1");
		digest.reset();
		return digest.digest(s.getBytes());
	}
}
