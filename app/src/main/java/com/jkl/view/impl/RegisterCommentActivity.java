package com.jkl.view.impl;

import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import com.jkl.web.listeners.CommentsListener;
import com.jkl.web.listeners.DefaultListener;

public interface RegisterCommentActivity extends OnClickListener, OnItemClickListener, CommentsListener{

	void registerSuccess();

	void onListItemClick(int position);

}
