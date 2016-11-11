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
package org.trace.DBAPI.data;

import java.util.Date;

public class TraceSession {
	
	private String _sessionID;
	private Date _date;
	private String _dateString;
	private String _vertexID;

	
	public TraceSession(){
		_sessionID = Math.random() + "";
		_date = new Date();
		_dateString = null;
	}
	
	public TraceSession(String sessionID, String date, String vertexID){
		_sessionID = sessionID;
		_dateString = date;
		_vertexID = vertexID;
	}

	public String getSessionID() {
		return _sessionID;
	}
	
	public Date getDate() {
		return _date;
	}
	
	public String getDateString(){
		
		if(_dateString == null){
			return _date.toString();
		}
		
		return _dateString;
	}
	
	public String getVertexID() {
		return _vertexID;
	}
	
	public void setVertexID(String vertexID){
		_vertexID = vertexID;
	}
}
