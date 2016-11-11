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

import java.util.HashMap;
import java.util.Map;

public class TraceEdge {
	
	private String _aVertex;
	private String _bVertex;
	private String _name;
	private Map<String, Object> _properties;

	public TraceEdge(String a, String b){
		_aVertex = a;
		_bVertex = b;
		_name = "unSet";
		_properties = new HashMap<>();
	}
	
	public TraceEdge(String name, String a, String b){
		_aVertex = a;
		_bVertex = b;
		_name = name;
		_properties = new HashMap<>();
	}
	
	public TraceEdge(String name, String a, String b, Map<String, Object> properties){
		_aVertex = a;
		_bVertex = b;
		_name = name;
		_properties = properties;
	}
	
	public TraceEdge(String a, String b, Map<String, Object> properties){
		_aVertex = a;
		_bVertex = b;
		_name = "unSet";
		_properties = properties;
	}

	public String getAVertex() {
		return _aVertex;
	}

	public void setAVertex(String aVertex) {
		_aVertex = aVertex;
	}

	public String getBVertex() {
		return _bVertex;
	}

	public void setBVertex(String bVertex) {
		_bVertex = bVertex;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public Map<String, Object> getProperties() {
		return _properties;
	}

	public void setProperties(Map<String, Object> properties) {
		_properties = properties;
	}
	
	
}
