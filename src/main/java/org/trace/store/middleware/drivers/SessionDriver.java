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
package org.trace.store.middleware.drivers;

import java.util.Date;
import java.util.List;

import org.trace.DBAPI.data.SimpleSession;
import org.trace.store.middleware.drivers.exceptions.SessionNotFoundException;
import org.trace.store.middleware.drivers.exceptions.UnableToPerformOperation;

public interface SessionDriver {
	
	/**
	 * Creates a new tracking session for the specified user.
	 * @param username The user's username.
	 * @param sessionToken The tracking session pseudonym.
	 * @throws UnableToPerformOperation
	 */
	public void openTrackingSession(int userId, String sessionToken) throws UnableToPerformOperation;
	
	/**
	 * Closes a currently active tracking session identified by its session token.
	 * @param sessionToken The session's token.
	 * @throws UnableToPerformOperation
	 */
	public void closeTrackingSession(String sessionToken) throws UnableToPerformOperation;
	
	/**
	 * Checks if the tracking session identified by the session token is closed or still active.
	 * @param sessionToken The tracking session's token.
	 * @return True is the tracking session is closed, false otherwise.
	 * @throws SessionNotFoundException 
	 */
	public boolean isTrackingSessionClosed(String sessionToken) throws UnableToPerformOperation, SessionNotFoundException;
	
	/**
	 * Checks is a tracking session exists for the specified session token.
	 * @param sessionToken The tracking session token.
	 * @return True if the tracking session is registered, false otherwise.
	 * 
	 * @throws UnableToPerformOperation
	 */
	public boolean trackingSessionExists(String sessionToken) throws UnableToPerformOperation;
	
	/**
	 * Removes all tracking sessions created before the specified date.
	 * @param date The date.
	 * @throws UnableToPerformOperation
	 */
	public void clearTrackingSessionsCreatedBefore(Date date) throws UnableToPerformOperation;
	
	/**
	 * Fetches all tracking sessions' tokens.
	 * @return List of all tracking sessions.
	 * @throws UnableToPerformOperation
	 */
	public List<SimpleSession> getAllTrackingSessions() throws UnableToPerformOperation;
	
	/**
	 * Fetches all closed tracking sessions' tokens.
	 * @return List of all tracking sessions.
	 * @throws UnableToPerformOperation
	 */
	public List<SimpleSession> getAllClosedTrackingSessions() throws UnableToPerformOperation;
	
	/**
	 * Fetches all closed tracking sessions' tokens associeted with a specific user.
	 * @param userId The user's identifier.
	 * @return List of tracking session tokens.
	 * @throws UnableToPerformOperation
	 */
	public List<SimpleSession> getAllUserTrackingSessions(int userId) throws UnableToPerformOperation;
	
	/**
	 * Fetches all closed tracking sessions created after the specified date.
	 * @param date The date
	 * @return List of tracking session tokens.
	 * @throws UnableToPerformOperation
	 */
	public List<SimpleSession> getAllTrackingSessionsCreatedAfter(Date date) throws UnableToPerformOperation;
	
	/**
	 * Fetches all closed tracking sessions created before the specified date.
	 * @param date The date
	 * @return List of tracking session tokens.
	 * @throws UnableToPerformOperation
	 */
	public List<SimpleSession> getAllTrackingSessionsCreatedBefore(Date date) throws UnableToPerformOperation;

	void reopenTrackingSession(String sessionToken) throws UnableToPerformOperation;

	void updateSessionDistance(String sessionToken, double distance) throws UnableToPerformOperation;

}
