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

import org.trace.store.services.api.TRACEPlannerQuery;
import org.trace.store.services.api.TRACEPlannerResultSet;

/**
 * TRACE has the potential to acquire extensive and very rich information 
 * regarding the characteristics of a city’s transportation network, and its
 * citizen’s mobility patterns. This information is of great value for urban
 * planner entities, as it enables more responsive and improve urban planning
 * initiatives. For instance,  the  knowledge  of  which  streets  are  preferred
 * by  cyclists  can  be  used  to  guide  the construction of new bicycle paths.
 * For this purpose, TRACEstore also contemplates a TRACEPlannerDriver.
 * The latter was designed to allow urban planner entities to query for 
 * higher-level information, in a flexible manner.
 */
public interface TRACEPlannerDriver {
	
	/**
	 *  Enables urban planners to lookup statistical data based on the provided
	 *  planner queries.
	 *   
	 * @param query Planner queries which are to be answered by TRACEstore.
	 * @return
	 */
	public TRACEPlannerResultSet get(TRACEPlannerQuery query);

}
