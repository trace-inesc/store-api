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
package org.trace.store.services.api;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.JsonObject;

@XmlRootElement
public class UserRegistryRequest {

	private String 	name,
					username,
					email,
					password,
					confirm,
					phone,
					address;

	public UserRegistryRequest(){}
	
	public UserRegistryRequest(
			String name,
			String username, String email,
			String password, String confirm,
			String phone, String address){
		
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
		this.confirm = confirm;
		this.phone = phone;
		this.address = address;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public JsonObject toJson(){
		JsonObject request = new JsonObject();
		request.addProperty("name", name);
		request.addProperty("username", username);
		request.addProperty("email", email);
		request.addProperty("phone", phone);
		request.addProperty("address", address);
		return request;
	}
	
	@Override
	public String toString() {
		return toJson().toString();
	}
}
