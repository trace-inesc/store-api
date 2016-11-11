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
import com.google.gson.JsonParser;

@XmlRootElement
public class BeaconLocation {

	private long timestamp;
	private int beaconId;
	private String attributes;
	
	public BeaconLocation(){}
	
	public BeaconLocation(int beaconId, long timestamp){
		this.beaconId = beaconId;
		this.timestamp = timestamp;
		
		this.attributes = "";
	}
	
	public BeaconLocation(int beaconId, long timestamp, String attributes){
		this.beaconId = beaconId;
		this.timestamp = timestamp;
		
		this.attributes = attributes;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public int getBeaconId() {
		return beaconId;
	}

	public void setBeaconId(int beaconId) {
		this.beaconId = beaconId;
	}

	public String getAttributes() {
		return attributes;
	}

	public void setAttributes(String attributes) {
		this.attributes = attributes;
	} 
	
	public JsonObject getAttributesAsJson(){
		
		if(attributes.isEmpty()) return null;
		
		JsonParser parser = new JsonParser();
		return (JsonObject)parser.parse(getAttributes());
	}
}
