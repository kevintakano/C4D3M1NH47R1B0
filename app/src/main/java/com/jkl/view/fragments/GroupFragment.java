package com.jkl.view.fragments;

import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import com.jkl.web.listeners.GroupListener;

/**
 * The Interface WToEatFragment.
 */
public interface GroupFragment extends OnClickListener, GroupListener {
	
	/**
	 * On list item click.
	 *
	 * @param position the position
	 */
	void onListItemClick(int position);

}
