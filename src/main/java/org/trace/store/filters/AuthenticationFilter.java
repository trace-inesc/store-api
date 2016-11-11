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
package org.trace.store.filters;

import java.io.IOException;
import java.security.Principal;

import javax.annotation.Priority;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;
import org.trace.store.middleware.TRACESecurityManager;
import org.trace.store.middleware.TRACESecurityManager.TokenType;

@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter{

	private final Logger LOG = Logger.getLogger(AuthenticationFilter.class); 
	
	private TRACESecurityManager manager = TRACESecurityManager.getManager();

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

		final String username;
		
		
		// Get the HTTP Authorization header from the request
		String authorizationHeader = 
				requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

		// Check if the HTTP Authorization header is present and formatted correctly 
		if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
			throw new NotAuthorizedException("Authorization header must be provided");
		}

		// Extract the token from the HTTP Authorization header
		String token = authorizationHeader.substring("Bearer".length()).trim();

		try {

			// Validate the token
			username = validateToken(token);
			
			if(username == null || username.isEmpty())
				throw new Exception();
			
			requestContext.setSecurityContext(new SecurityContext() {
				
				@Override
				public boolean isUserInRole(String arg0) {
					return true;
				}
				
				@Override
				public boolean isSecure() {
					return false;
				}
				
				@Override
				public Principal getUserPrincipal() {
					return new Principal() {
						
						@Override
						public String getName() {
							return username;
						}
					};
				}
				
				@Override
				public String getAuthenticationScheme() {
					// TODO Auto-generated method stub
					return null;
				}
			});

		} catch (Exception e) {
			requestContext.abortWith(
					Response.status(Response.Status.UNAUTHORIZED).build());
		}
	}


	private String validateToken(String token) throws Exception {

		try {
			String username = null;
			TokenType type = manager.getTokenType(token);
			
			switch (type) {
			case google:
				username = manager.validateGoogleAuthToken(token).getSubject();
				break;
			case trace:
				username = manager.validateAndExtractSubject(token);
			default:
				break;
			}
			
			if(username!=null && !username.isEmpty())
				return username;
			else
				return null;
			
		}catch(Exception e){
			LOG.error(e.getMessage());
			return null;
		}
	}
}