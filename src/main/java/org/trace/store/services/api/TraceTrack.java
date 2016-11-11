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

import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.JsonObject;

/**
 *  A track can be perceived as an ordered sequence of locations
 *  that are associated with a timestamp, and that may contain 
 *  other semantic information. This additional semantic information 
 *  may contain, for instance, means of transportation, velocity, among others.
 */
@XmlRootElement
public class TraceTrack {
	
	private Location[] track;
	
	public TraceTrack(){}
	
	public TraceTrack(Location[] track){
		this.track = track;
	}

	public Location[] getTrack() {
		return track;
	}

	public void setTrack(Location[] track) {
		this.track = track;
	}
	
	public List<Location> getTrackAsList(){
		return Arrays.asList(getTrack());
	}
	
	public JsonObject getJsonLocation(int index){
		
		if(index > track.length) return null;
		
		return track[index].getLocationAsJsonObject();
	}
	
	public Location getLocation(int index){
		if(index > track.length) return null;
		
		return track[index];
	}
	
	public int getTrackSize(){
		return track.length;
	}
}
