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
package org.trace.store;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.trace.store.middleware.drivers.utils.FormFieldValidator;

public class FormValidatorTest {

	private final String[] testValidUsernames = {
			"common",
			"slingsymptoms",
			"whimbrelcommon",
			"porcelaincrib",
			"avowcollect",
			"fishycascaded",
			"golf_is_sad",
			"nonagontawny",
			"transitevident",
			"pilekuwaiti",
			"balmabrasive",
			"omicronmatch",
			"disks_fit",
			"wifeoctave",
			"landquintic",
			"awfulpogostick",
			"towh333vacuate",
			"havingwreck",
			"leverheels",
			"bosonplenty",
			"totteringtear"
	};

	private final String[] testInvalidUsernames = {
			"bob",
			"evil",
			"bob2",
			"c!drulestaking",
			"abscissafurrowed",
			"chloridescassiopeia",
			"sousaphonechanged",
			"consultantsavannah",
			"totte#ingtear",
			"a",
			"!"
	};

	@Test
	public void testUsernameValidation(){
		int i;
		for(i=0; i<testValidUsernames.length; i++)
			assertTrue(FormFieldValidator.isValidUsername(testValidUsernames[i]));
		
		for(i=0; i<testInvalidUsernames.length; i++)
			assertFalse(FormFieldValidator.isValidUsername(testInvalidUsernames[i]));
	}


}
