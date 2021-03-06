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

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DistanceBasedRewardRequest {
	
	private double travelledDistance;
	private String reward;
	
	public DistanceBasedRewardRequest(){}
	
	public DistanceBasedRewardRequest(double distanceTravelled, String reward){
		this.travelledDistance = distanceTravelled;
		this.reward = reward;
	}

	public double getTravelledDistance() {
		return travelledDistance;
	}

	public void setTravelledDistance(double travelledDistance) {
		this.travelledDistance = travelledDistance;
	}

	public String getReward() {
		return reward;
	}

	public void setReward(String reward) {
		this.reward = reward;
	}
	
	

}
