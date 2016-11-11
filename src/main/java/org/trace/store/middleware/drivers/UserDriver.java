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


import java.sql.SQLException;
import java.util.List;

import org.trace.store.filters.Role;
import org.trace.store.middleware.drivers.exceptions.EmailAlreadyRegisteredException;
import org.trace.store.middleware.drivers.exceptions.ExpiredTokenException;
import org.trace.store.middleware.drivers.exceptions.InvalidEmailException;
import org.trace.store.middleware.drivers.exceptions.InvalidIdentifierException;
import org.trace.store.middleware.drivers.exceptions.InvalidPasswordException;
import org.trace.store.middleware.drivers.exceptions.InvalidUsernameException;
import org.trace.store.middleware.drivers.exceptions.NonMatchingPasswordsException;
import org.trace.store.middleware.drivers.exceptions.PasswordReuseException;
import org.trace.store.middleware.drivers.exceptions.UnableToPerformOperation;
import org.trace.store.middleware.drivers.exceptions.UnableToRegisterUserException;
import org.trace.store.middleware.drivers.exceptions.UnableToUnregisterUserException;
import org.trace.store.middleware.drivers.exceptions.UnknownUserException;
import org.trace.store.middleware.drivers.exceptions.UnknownUserIdentifierException;
import org.trace.store.middleware.drivers.exceptions.UserRegistryException;
import org.trace.store.middleware.drivers.exceptions.UsernameAlreadyRegisteredException;
import org.trace.store.services.api.PrivacyPolicies;

public interface UserDriver {

	/**
	 * Creates a new user. This method must also be responsible for
	 * validating all the provided fields. If successful, the
	 * method will return the user's activation token, which
	 * must then be used to activate its account.
	 * 
	 * @param username The user's username.
	 * @param email The user's email address.
	 * @param pass1 The user's password.
	 * @param pass2 The user's password confirmation.
	 * 
	 * @return The activation token
	 * 
	 * @throws UserRegistryException If one of the fields is invalid.
	 * @throws NonMatchingPasswordsException If the passwords do not match.
	 * @throws UnableToRegisterUserException If the user creation was unsuccessful for some unforseeable event.
	 * @throws UnableToPerformOperation 
	 *  
	 */
	public String registerUser(String username, String email, String pass1, String pass2, String name, String address, String phone, Role role)
		throws UnableToRegisterUserException, UnableToPerformOperation;
	
	/**
	 * 
	 * @param username
	 * @param email
	 * @param name
	 * @param role
	 * @return
	 * @throws UserRegistryException
	 * @throws UnableToRegisterUserException
	 * @throws UnableToPerformOperation
	 */
	public String registerFederatedUser(String username, String email, String name)
			throws UserRegistryException, UnableToRegisterUserException, UnableToPerformOperation;
	
	/**
	 * Unregister a user, however, only if presents a correct password.
	 * 
	 * @param identifier The user's identifier, which may either be his username or email.
	 * @param password THe user's password.
	 * 
	 * @return True if the operation was successful. 
	 * @throws UnableToUnregisterUserException 
	 * @throws NonMatchingPasswordsException 
	 * @throws UnknownUserException 
	 */
	public boolean unregisterUser(String identifier, String password) throws UnableToUnregisterUserException, NonMatchingPasswordsException, UnknownUserException;
	
	/**
	 * Fetches a given user's UID.
	 * 
	 * @param identifier The user's identification, which may either be his username or email.
	 * 
	 * @return The user's UID
	 * 
	 * @throws UnknownUserException There is no user registered with the provided identifier.
	 * @throws UnableToPerformOperation The operation was unsuccessful due to unforseeable reasons.
	 */
	public int getUserID(String identifier) throws UnknownUserException, UnableToPerformOperation;
	
	
	/**
	 * Checks if a given user account is still pending activation.
	 * 
	 * @param userID The user's UID
	 * 
	 * @return True if the account has not yet been activated, false otherwise.
	 */
	public boolean isPendingActivation(int userID);
	
		
	/**
	 * Activated a specific user account given the provided activation token.
	 * 
	 * @param token The activation token (cleartext)
	 * @return True if the operation was successful, false otherwise.
	 * 
	 * @throws ExpiredTokenException
	 * @throws UnableToPerformOperation 
	 */
	public boolean activateAccount(String token) throws ExpiredTokenException, UnableToPerformOperation;
	
	
	
	public boolean changePassword(String username, String oldPass, String newPass, String confirmPass) 
			throws NonMatchingPasswordsException, PasswordReuseException, UnknownUserIdentifierException;

	
	public String recoverPassword(String email) 
			throws UnknownUserIdentifierException, UnableToPerformOperation;
	
	
	public boolean resetPassword(String username, String token, String newPass, String confirmPass) 
			throws ExpiredTokenException, UnknownUserIdentifierException;
	
	
	public boolean isValidPassword(String identifier, String password) throws InvalidIdentifierException, UnableToPerformOperation, UnknownUserException;
	
	public boolean isValidRole(String identifier, Role role) throws UnableToPerformOperation, UnknownUserException;
	
	/**
	 * Allows users to set security and privacy policies about their data.
	 *  
	 * @param identifier The user's unique identifier, either its username or email.
	 * @param policies The privacy policies.
	 * 
	 * @return True if the privacy policies were successfully added, false otherwise.
	 */
	public boolean setPrivacyPolicies(String identifier, PrivacyPolicies policies);

	public List<Role> getUserRoles(String identifier) throws UnableToPerformOperation, UnknownUserException;
	
}
