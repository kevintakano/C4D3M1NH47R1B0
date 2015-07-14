package com.jkl.web.listeners;

import com.jkl.domain.User;

/**
 * The listener interface for receiving login events.
 * The class that is interested in processing a login
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addLoginListener<code> method. When
 * the login event occurs, that object's appropriate
 * method is invoked.
 *
 * @see LoginEvent
 */
public interface LoginListener extends DefaultListener {
	
	/**
	 * Login success.
	 * 
	 * @param user the logged user.
	 */
	void loginSuccess(User user);
	
}
