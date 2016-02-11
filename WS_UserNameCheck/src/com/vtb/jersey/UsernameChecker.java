package com.vtb.jersey;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

//Path: http://localhost/<appln-folder-name>/unamechecker
@Path("/unamechecker")
public class UsernameChecker {
	// HTTP Get Method
	@GET
	// Path: http://localhost/<appln-folder-name>/unamechecker/docheck
	@Path("/docheck")
	// Produces JSON as response
	@Produces(MediaType.APPLICATION_JSON)
	// Query parameters :
	// http://localhost/<appln-folder-name>/unamechecker/docheck?username=username1
	public String doUnameCheck(@QueryParam("username") String uname) {
		String response = "";
		if (checkUnameExists(uname)) {
			response = Utility.constructJSON("unamecheck", true);
		} else {
			response = Utility.constructJSON("unamecheck", false,
					"Someone already has that username. Try another?");
		}
		return response;
	}

	/**
	 * Method to check whether the entered credential is valid
	 * 
	 * @param uname
	 * @param pwd
	 * @return
	 */
	private boolean checkUnameExists(String uname) {
		System.out.println("Inside checkCredentials");
		boolean result = false;
		if (Utility.isNotNull(uname)) {
			try {
				result = DBConnection.checkUnameAvailability(uname);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				result = false;
			}
		} else {
			result = false;
		}

		return result;
	}

}
