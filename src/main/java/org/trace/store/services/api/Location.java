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

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Location {
	
	private long timestamp;
	
	private double latitude, longitude;
	
	private String attributes;
	
	public Location(){}

	public Location(double latitude, double longitude, long timestamp, String attributes){
		this.latitude = latitude;
		this.longitude = longitude;
		this.timestamp = timestamp;
		this.attributes = attributes;
	}
	
	public String getLocationJSON() {
		return getLocationAsJsonObject().toString();
	}

	
	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	
	
	public String getAttributes() {
		return attributes;
	}

	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}

	public JsonObject getLocationAsJsonObject(){
		
		JsonParser parser = new JsonParser();
		
		JsonObject location = new JsonObject();
		location.addProperty("latitude", latitude);
		location.addProperty("longitude", longitude);
		location.addProperty("timestamp", timestamp);
		location.add("attributes", parser.parse(attributes));
		return location;
	}
	
	@Override
	public String toString() {
		return getLocationJSON().toString();
	}
}
