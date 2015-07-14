package com.jkl.web.listeners;

/**
 * The listener interface for receiving default events.
 * The class that is interested in processing a default
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addDefaultListener<code> method. When
 * the default event occurs, that object's appropriate
 * method is invoked.
 *
 * @see DefaultEvent
 */
public interface DefaultListener {
	
	/**
	 * Request failed.
	 *
	 * @param message the message
	 */
	void requestFailed(String message);

}
