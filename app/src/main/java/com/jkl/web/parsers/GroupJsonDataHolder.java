package com.jkl.web.parsers;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;

import com.jkl.domain.Group;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GroupJsonDataHolder {
	
	/**
	 * Gets the list os products.
	 *
	 * @param json the json
	 * @return the user logged in
	 * @throws JSONException the JSON exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static ArrayList<Group> getProducts(final String json) throws JSONException, IOException {
		
		ObjectMapper objectMapper = new ObjectMapper();
		
	    final ArrayList<Group> groups = objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(
                ArrayList.class, Group.class));

	    return groups;
	}

}
