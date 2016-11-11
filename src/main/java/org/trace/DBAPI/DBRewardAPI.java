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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tinkerpop.gremlin.driver.Client;
import org.apache.tinkerpop.gremlin.driver.Result;

public class DBRewardAPI extends DBAPI{
	
	public DBRewardAPI(Client client){
		super(client);
	}

	//TODO implement this method
	public boolean register(String username, String password, String name){
		boolean success = true;
		List<Result> results = null;
		
		//params
		Map<String,Object> params = new HashMap<>();
		params.put("username",username);
		params.put("password",password);
		params.put("name",name);

		//query
		results = query("graph.addVertex(label,'shopOwner','username', username, 'password', password, 'name', name)", params);
		
		return success;
	}
	
	public boolean associateLocation(String username, String password, double xCoord, double yCoord){
		boolean success = true;
		List<Result> results = null;
		
		//params
		Map<String,Object> params = new HashMap<>();
		params.put("username",username);
		params.put("password",password);
		params.put("x",xCoord);
		params.put("y",yCoord);

		//query
		results = query("O = g.V().hasLabel('shopOwner').has('username',username).has('password',password).next(); "
				+ "L = g.V().hasLabel('location').has('location',Geoshape.point(x,y)).next(); "
				+ "O.addEdge('shopLocation',L)", params);
		
		return success;
	}
	
	public List<Result> sessionsOnShop(String username, String password){
		boolean success = true;
		List<Result> results = null;
		
		//params
		Map<String,Object> params = new HashMap<>();
		params.put("username",username);
		params.put("password",password);

		//query
		results = query("L = g.V().hasLabel('shopOwner').has('username',username).has('password',password).out('shopLocation'); "
				+ "L.inE('session').values('sessionID')", params);
		
//		L.inE('session').values('sessionID')
		
		return results;
	}
	
	public List<Result> countSessionsOnShop(String username, String password){
		boolean success = true;
		List<Result> results = null;
		
		//params
		Map<String,Object> params = new HashMap<>();
		params.put("username",username);
		params.put("password",password);

		//query
		results = query("L = g.V().hasLabel('shopOwner').has('username',username).has('password',password).out('shopLocation'); "
				+ "L.inE('session').count()", params);
		
//		L.inE('session').values('sessionID')
		
		return results;
	}
	
	public boolean usersOnShop(String username, String password){
		boolean success = true;
		List<Result> results = null;
		
		//params
		Map<String,Object> params = new HashMap<>();
		params.put("username",username);
		params.put("password",password);

		//query
		results = query("L = g.V().hasLabel('shopOwner').has('username',username).has('password',password).out('shopLocation'); "
				+ "SIDs = L.inE('session'); "
				+ "users = new ArrayList<String>(); "
				+ "A = 1; B = 2; "
//				+ "while(SIDs.hasNext()) {A = g.V().hasLabel('session').has('sessionID',SIDs.next().values('sessionID'))}; "
//				+ "SIDs.next().values('sessionID')"
				+ "SIDs"
				+ "", params);
		
		return success;
	}
	
	public boolean sessionVertexOfEdge(String sessionID){
		//params
		Map<String,Object> params = new HashMap<>();
		params.put("sessionID",sessionID);
		
		query("session = g.V().hasLabel('location').has('name','D').next(); "
				+ "A = session; "
//				+ "while(A.label() == 'location') {A = A.inVertex('session').has('sessionID', A.values('sessionID').next())}"
//				+ "g.V(A).in('session').has('sessionID')"
				+ "A.class",params);
		
		return true;
	}
}
