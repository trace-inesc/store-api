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
package org.trace.store.middleware.drivers;

import java.util.List;

import org.trace.store.middleware.drivers.exceptions.UnableToPerformOperation;
import org.trace.store.services.api.data.TraceReward;

public interface RewarderDriver {
	
	/**
	 * Returns all the users that have traveled more than the specified kilometers.
	 * @param distance The minimum traveled distance in kilometers.
	 * @return List of all the users that have traveled more than the specified distance
	 * @throws UnableToPerformOperation
	 */
	public List<String> getUsersWithDistance(double distance) throws UnableToPerformOperation;
	
	public boolean registerDistanceBasedReward(int ownerId, double distance, String reward) throws UnableToPerformOperation;

	public List<TraceReward> getAllOwnerRewards(int ownerId) throws UnableToPerformOperation;

	public boolean ownsReward(int ownerId, int rewardId) throws UnableToPerformOperation;

	public boolean unregisterReward(int rewardId) throws UnableToPerformOperation;

	public double getUserDistance(int userId) throws UnableToPerformOperation;;
}
