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

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.apache.tinkerpop.gremlin.driver.Client;
import org.apache.tinkerpop.gremlin.driver.Result;
import org.apache.tinkerpop.gremlin.driver.ResultSet;

public class DBAPI {
	
	private Client _client;
	protected static final double GPS_TOLERANCE = 0.008; //Tolerance in kilometers

	public DBAPI(Client client){
		_client = client;
	}
	
	//query method
	protected List<Result> query(String query, Map<String,Object> params){
		//return values list
		List<Result> results = null; 
		try {
			//query
//			System.out.println("-----------");
//			System.out.println("Query: " + query);
//			System.out.println("-----------");
//			Date date1 = new Date();
			
			//query or command being issued
			ResultSet resultSet = _client.submit(query, params);

			//List that will be filled as the DB answers to the query
			CompletableFuture<List<Result>> complete = resultSet.all();

			//wait for the answer of the DB to be complete
			results = complete.get();
			
			complete.cancel(true);

//			Date date2 = new Date();
//			Long time = date2.getTime() - date1.getTime();
//			
//			//Printing the results
//			System.out.println("-----------");
//			System.out.println("Results (" + time + "ms):");
//			for (Result result : results) {
//				System.out.println(result.getString());
//			}
//			System.out.println("-----------");


		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return results;		
	}
}
