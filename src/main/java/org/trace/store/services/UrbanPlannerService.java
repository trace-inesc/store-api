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
package org.trace.store.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.trace.store.filters.Role;
import org.trace.store.filters.Secured;
import org.trace.store.services.api.TRACEQuery;
import org.trace.store.services.api.UserRegistryRequest;

/**
 * TRACE has the potential to acquire extensive and very rich information 
 * regarding the characteristics of a city’s transportation network, and its 
 * citizen’s mobility patterns. This information is of great value for urban 
 * planner entities, as it enables more responsive and improve urban planning
 * initiatives. For instance,  the  knowledge  of  which  streets  are  
 * preferred  by cyclists  can  be  used  to  guide  the construction of new
 * bicycle paths. For this purpose, TRACEstore also contemplates a 
 * UrbanPlannerService API. The latter was designed to allow urban planner 
 * entities, not only to register themselves, but also, to query for 
 * higher-level information, in a flexible manner. 
 */
@Path("/urban")
public class UrbanPlannerService {

private final String LOG_TAG = "UrbanPlannerService"; 
	
	@Path("/test")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String test(){
		return "Welcome to the "+LOG_TAG;
	}
	
	/*
	 ************************************************************************
	 ************************************************************************
	 * User-based Requests													*
	 ************************************************************************
	 ************************************************************************
	 */
	
	/**
	 * Allows Urban Planners to register themselves into TRACE’s system. 
	 *  
	 * @param request This request contains all the fields necessary to register a new urban planner.
	 * @return 
	 * 
	 * @see UserRegistryRequest
	 */
	@POST
	@Path("/register")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response registerUser(UserRegistryRequest request){
		throw new UnsupportedOperationException();
	}
		
	/**
	 * Enables urban planners to lookup statistical data based on the provided 
	 * planner queries
	 *  
	 * @param query
	 * @return
	 */
	@POST
	@Path("/get")
	@Secured(Role.planner)
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response get(TRACEQuery query){
		throw new UnsupportedOperationException();
	}	
}
