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
package org.trace.store;

import org.apache.log4j.PropertyConfigurator;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.trace.store.filters.AuthenticationFilter;
import org.trace.store.filters.CORSResponseFilter;
import org.trace.store.middleware.TRACESecurityManager;
import org.trace.store.middleware.TRACEStore;

public class TraceStoreApp extends ResourceConfig{

	public TraceStoreApp(){
		
		//Force eager initialization of the middleware
		TRACEStore.getTRACEStore();
		TRACESecurityManager.getManager();
		packages("org.trace.store.services");
		
		//Incomming Filters
		 // Register resources and providers using package-scanning.
 
        // Register my custom provider - not needed if it's in my.package.
        register(AuthenticationFilter.class); 
        //register(AuthorizationFilter.class);  
        
        
        // Register an instance of LoggingFilter.
        //register(new LoggingFilter(LOGGER, true));
 
        //Outgoing Filters
        register(CORSResponseFilter.class);
        
        // Enable Tracing support.
        property(ServerProperties.TRACING, "ALL");
        
        PropertyConfigurator.configure(System.getenv("LOG4J_CONFIG"));
	}
	
}
