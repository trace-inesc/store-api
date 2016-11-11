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
package org.trace.DBAPI.data;

import com.google.gson.JsonObject;

/**
 * Simple session that is characterized by its session identifier, and the timestamp of 
 * the date it was created.
 */
public class SimpleSession {

	private String sessionId;
	private long timestamp;
	
	public SimpleSession(){}
	
	public SimpleSession(String sessionId, long timestamp){
		this.sessionId = sessionId;
		this.timestamp = timestamp;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	public JsonObject toJson(){
		JsonObject json = new JsonObject();
		json.addProperty("session", sessionId);
		json.addProperty("date", timestamp);
		return json;
	}
	@Override
	public String toString() {
		return toJson().toString();
	}
	
}
