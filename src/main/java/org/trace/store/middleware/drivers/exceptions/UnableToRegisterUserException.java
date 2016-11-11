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
package org.trace.store.middleware.drivers.exceptions;

public class UnableToRegisterUserException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public UnableToRegisterUserException(){
		this.message = "Unable to register user";
	}
	
	public UnableToRegisterUserException(String cause){
		this.message = cause; 
	}
	
	@Override
	public String getMessage() {
		return this.message;
	}
}
