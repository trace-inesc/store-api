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
package org.trace.store.services.api.data;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class TraceReward {

	private int identifier;
	private String condition;
	private String reward;
	
	public TraceReward(){}
	
	public TraceReward(int identifier, String condition, String reward){
		this.identifier = identifier;
		this.condition = condition;
		this.reward = reward;
	}

	public int getIdentifier() {
		return identifier;
	}

	public void setIdentifier(int identifier) {
		this.identifier = identifier;
	}

	public String getReward() {
		return reward;
	}

	public void setReward(String reward) {
		this.reward = reward;
	}
	
	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public JsonObject toJson(){
		JsonParser parser = new JsonParser();
		JsonObject json = new JsonObject();
		json.addProperty("identifier", getIdentifier());
		json.add("conditions", parser.parse(getCondition()));
		json.addProperty("reward", getReward());
		return json;
	}
	
	public double getMinimumDistance(){ //TODO: temporary
		JsonObject c = this.toJson().getAsJsonObject("conditions");
		return c.has("distance") ? c.get("distance").getAsDouble() : 0;
	}	
}
