package com.jkl.web.listeners;

import java.util.ArrayList;

import com.jkl.domain.Group;

public interface GroupListener extends DefaultListener{
	
	/**
	 * Login success.
	 * 
	 * @param user the logged user.
	 */
	void getGroupsSuccess(ArrayList<Group> products);

}
