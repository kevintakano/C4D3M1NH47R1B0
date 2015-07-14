package com.jkl.web.listeners;

import java.util.ArrayList;

import com.jkl.domain.Comment;

public interface CommentsListener extends DefaultListener {
	
	void getCommentSuccess(ArrayList<Comment> comments);

}
