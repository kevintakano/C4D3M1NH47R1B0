package com.jkl.web.parsers;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;

import com.jkl.domain.User;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The Class UsersJsonDataHolder.
 * 
 * @author Aguiar
 */
public class UsersJsonDataHolder {
	
	/**
	 * Gets the users.
	 *
	 * @param json the json
	 * @return the user logged in
	 * @throws JSONException the JSON exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static ArrayList<User> getUsers(final String json) throws JSONException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		
	    final ArrayList<User> users = objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(
                ArrayList.class, User.class));

	    return users;
	}

}
