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

import org.trace.store.services.api.RewardingPolicy;
import org.trace.store.services.api.data.Beacon;

/**
 * Local  businesses,  for  instance  shop  owners,  may  leverage  TRACE  to
 * promote  themselves  by providing  rewards  to  users  that come to their
 * businesses.  For  this  purpose,  TRACEstore contemplates a TRACERewardDriver
 * that enables interested business owners to register themselves in TRACE. Once  
 * they  have  been  registered,  they  may  then  specify the rewards and 
 * corresponding conditions through the TRACERewardDriver. 
 */
public interface TRACERewardDriver {

	/**
	 * Enables third parties to associate themselves with a geographical location. 
	 * 
	 * @param identifier The 3rd party's identifier
	 * @param latitude The 3rd party's location's latitude
	 * @param longitude The 3rd party's location's longitude
	 * 
	 * @return True if the operation was successful, false otherwise.
	 */
	public boolean setLocation(String identifier, float latitude, float longitude);
	
	/**
	 * Enables third parties to associate themselves with a beacon. 
	 * 
	 * @param identifier The 3rd party's identifier
	 * @param beacon The 3rd party's beacon.
	 * 
	 * @return True if the operation was successful, false otherwise.
	 */
	public boolean setLocation(String identifier, Beacon beacon);
	
	/**
	 * Enables third parties to set a reward based on a set of policies.
	 *   
	 * @param identifier The 3rd party's identifier.
	 * @param description A brief description of the reward
	 * @param policy The policy that specified the conditions that must be fulfield 
	 * 
	 * @return True if the operation was successful, false otherwise.
	 */
	public boolean setReward(String identifier, String description, RewardingPolicy policy);
}
