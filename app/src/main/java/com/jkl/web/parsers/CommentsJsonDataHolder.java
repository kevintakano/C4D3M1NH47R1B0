package com.jkl.web.parsers;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;

import com.jkl.domain.Comment;
import com.jkl.domain.Group;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CommentsJsonDataHolder {

	/**
	 * Gets the list of comments.
	 *
	 * @param json the json
	 * @return the comments
	 * @throws JSONException the JSON exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static ArrayList<Comment> getComment(final String json) throws JSONException, IOException {
		
		ObjectMapper objectMapper = new ObjectMapper();
		
	    final ArrayList<Comment> comments = objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(
                ArrayList.class, Comment.class));

	    return comments;
	}
}
