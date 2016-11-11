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

import java.util.List;

//Created for parsing effects only

public class RouteBoundingBox {
	private double _minLat,_maxLat,_minLon,_maxLon;
	
	public RouteBoundingBox(double minLat, double maxLat, double minLon, double maxLon){
		_minLat = minLat;
		_maxLat = maxLat;
		_minLon = minLon;
		_maxLon = maxLon;
	}

	public double getMinLat() {
		return _minLat;
	}

	public double getMaxLat() {
		return _maxLat;
	}

	public double getMinLon() {
		return _minLon;
	}

	public double getMaxLon() {
		return _maxLon;
	}
	
	
	public double getSWLat() {
		return _minLat;
	}
	
	public double getSWLon() {
		return _minLon;
	}

	public double getNELat() {
		return _maxLat;
	}

	public double getNELon() {
		return _maxLon;
	}
	
}
