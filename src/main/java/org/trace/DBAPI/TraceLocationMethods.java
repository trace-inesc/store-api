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
package org.trace.DBAPI;

/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
/*::                                                                         :*/
/*::  This routine calculates the distance between two points (given the     :*/
/*::  latitude/longitude of those points). It is being used to calculate     :*/
/*::  the distance between two locations using GeoDataSource (TM) prodducts  :*/
/*::                                                                         :*/
/*::  Definitions:                                                           :*/
/*::    South latitudes are negative, east longitudes are positive           :*/
/*::                                                                         :*/
/*::  Passed to function:                                                    :*/
/*::    lat1, lon1 = Latitude and Longitude of point 1 (in decimal degrees)  :*/
/*::    lat2, lon2 = Latitude and Longitude of point 2 (in decimal degrees)  :*/
/*::    unit = the unit you desire for results                               :*/
/*::           where: 'M' is statute miles (default)                         :*/
/*::                  'K' is kilometers                                      :*/
/*::                  'N' is nautical miles                                  :*/
/*::  Worldwide cities and other features databases with latitude longitude  :*/
/*::  are available at http://www.geodatasource.com                          :*/
/*::                                                                         :*/
/*::  For enquiries, please contact sales@geodatasource.com                  :*/
/*::                                                                         :*/
/*::  Official Web site: http://www.geodatasource.com                        :*/
/*::                                                                         :*/
/*::           GeoDataSource.com (C) All Rights Reserved 2015                :*/
/*::                                                                         :*/
/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
//38.0001


import java.util.*;

import org.apache.log4j.Logger;
import org.apache.tinkerpop.shaded.minlog.Log;
import org.trace.DBAPI.data.RouteBoundingBox;
import org.trace.DBAPI.data.TraceVertex;
import org.trace.store.services.TRACEStoreService;

import java.lang.*;
import java.io.*;

class TraceLocationMethods {

	private final static Logger LOG = Logger.getLogger(TRACEStoreService.class); 

	//	protected static final double MAX_DISTANCE = 0.016; 

	//	public static void main (String[] args) throws java.lang.Exception
	//	{
	//		System.out.println(distance(32.9697, -96.80322, 29.46786, -98.53506, "M") + " Miles\n");
	//		System.out.println(distance(32.9697, -96.80322, 29.46786, -98.53506, "K") + " Kilometers\n");
	//		System.out.println(distance(32.9697, -96.80322, 29.46786, -98.53506, "N") + " Nautical Miles\n");
	//	}

	public static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
		
//		System.out.println("lat1:" + lat1 + " lon1:" + lon1 + " lat2:" + lat2 + " long2:" + lon2);
		
		if(lat1 == lat2 && lon1 == lon2){
			return 0;
		}
		
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		if (unit == "K") {
			dist = dist * 1.609344;
		} else if (unit == "N") {
			dist = dist * 0.8684;
		}
		return (dist);
	}

	public static double midPoint(double p1, double p2){
		return ((p1 + p2) / 2);
	}

	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	/*::	This function converts decimal degrees to radians						 :*/
	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	/*::	This function converts radians to decimal degrees						 :*/
	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	private static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}


	public static String getGridID(double coord){
		int intCoord = (int) (coord * 10000);
		double gridID = intCoord / 10000.0;

		return "" + gridID;
	}

	public static List<String> getAdjacentGridIDs(double coord){
		List<String> adjacentGridIDs = new ArrayList<>();

		int intCoord = (int) (coord * 10000);
		int intCoord1 = intCoord - 1;
		int intCoord2 = intCoord + 1;

		adjacentGridIDs.add("" + (intCoord1 / 10000.0));
		adjacentGridIDs.add("" + (intCoord / 10000.0));
		adjacentGridIDs.add("" + (intCoord2 / 10000.0));

		return adjacentGridIDs;
	}

	public static List<TraceVertex> splitRoad(String p1, double lat1, double lon1, String p2, double lat2, double lon2, double distance){
		List<TraceVertex> roadVertices = new ArrayList<>();

		if(distance(lat1, lon1, lat2, lon2, "K") > distance){
			double lat3 = TraceLocationMethods.midPoint(lat1, lat2);
			double lon3 = TraceLocationMethods.midPoint(lon1, lon2);
			String p3 = "" + lat3 + "_" + lon3;

			List<TraceVertex> newRoadVerticesLeft = splitRoadAux(p1, lat1, lon1, p3, lat3, lon3, distance);
			List<TraceVertex> newRoadVerticesRight = splitRoadAux(p3, lat3, lon3, p2, lat2, lon2, distance);

			for(TraceVertex v : newRoadVerticesLeft){
				if(!roadVertices.contains(v)){
					roadVertices.add(v);
				}
			}
			for(TraceVertex v : newRoadVerticesRight){
				if(!roadVertices.contains(v)){
					roadVertices.add(v);
				}
			}
		}else{
			roadVertices.add(new TraceVertex(p2,lat2,lon2));
		}
		return roadVertices;
	}

	private static List<TraceVertex> splitRoadAux(String p1, double lat1, double lon1, String p2, double lat2, double lon2, double distance){
		List<TraceVertex> roadVertices = new ArrayList<>();

		roadVertices.add(new TraceVertex(p1, lat1, lon1));
		
		if(distance(lat1, lon1, lat2, lon2, "K") > distance){
			double lat3 = TraceLocationMethods.midPoint(lat1, lat2);
			double lon3 = TraceLocationMethods.midPoint(lon1, lon2);
			String p3 = "" + lat3 + "_" + lon3;

			List<TraceVertex> newRoadVerticesLeft = splitRoadAux(p1, lat1, lon1, p3, lat3, lon3, distance);
			List<TraceVertex> newRoadVerticesRight = splitRoadAux(p3, lat3, lon3, p2, lat2, lon2, distance);

			for(TraceVertex v : newRoadVerticesLeft){
				roadVertices.add(v);
			}

			for(TraceVertex v : newRoadVerticesRight){
				roadVertices.add(v);
			}
		}else{
			roadVertices.add(new TraceVertex(p2,lat2,lon2));
		}

		return roadVertices;
	}
	
	public static List<TraceVertex> routeParser(List<TraceVertex> submittedRoute){
		List<TraceVertex> route = new ArrayList<>();
		
		TraceVertex lastVertex = null;
		
		if(submittedRoute == null){
			LOG.error("routeParser: The submittedRoute is null");
			return null;
		}
		
		for(TraceVertex v : submittedRoute){

//			Make sure there are no two following vertices with the same coords.
			if(lastVertex != null){
				if(lastVertex.getLatitude() != v.getLatitude() && lastVertex.getLongitude() != v.getLongitude() ){
					route.add(v);
					lastVertex = v;
				}
			}else{
				route.add(v);
				lastVertex = v;
			}
		}
		return route;
	}

	
	public static double routeTotalDistance(List<TraceVertex> route){
		double totalDistance = 0;
		
		TraceVertex lastVertice = null;
		for(TraceVertex v : route){
			if(lastVertice != null){
//				double d = distance(lastVertice.getLatitude(), lastVertice.getLongitude(), v.getLatitude(), v.getLongitude(), "K");
//				System.out.println("d:" + d);
				totalDistance+=distance(lastVertice.getLatitude(), lastVertice.getLongitude(), v.getLatitude(), v.getLongitude(), "K");;
//				System.out.println("totalDistance:" + totalDistance);
			}
			lastVertice = v;
		}
		
		return totalDistance;
	}
	
	public static RouteBoundingBox getRouteBoundingBox(List<TraceVertex> route){
		if(route == null){
			LOG.error("getBoundingBox: route is null");
			return null;
		}
		
		double minLat = route.get(0).getLatitude();
		double maxLat = route.get(0).getLatitude();
		double minLon = route.get(0).getLongitude();
		double maxLon = route.get(0).getLongitude();
				
		for(TraceVertex v : route){
			double lat = v.getLatitude();
			double lon = v.getLongitude();
			
			if(lat < minLat){
				minLat = lat;
			}else{
				if(lat > maxLat){
					maxLat = lat;
				}
			}
			
			if(lon < minLon){
				minLon = lon;
			}else{
				if(lon > maxLon){
					maxLon = lon;
				}
			}
		}
		return new RouteBoundingBox(minLat, maxLat, minLon, maxLon);
	}
}