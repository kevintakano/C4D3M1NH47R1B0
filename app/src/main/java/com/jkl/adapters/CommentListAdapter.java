package com.jkl.adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jkl.domain.Comment;
import com.jkl.view.impl.RegisterCommentActivity;
import com.jkl.cademinhatribo.R;

public class CommentListAdapter extends BaseAdapter

{
	/** The objects. */
	private List<Comment> objects = new ArrayList<Comment>();
	
	/** The inflater. */
	private final LayoutInflater inflater;
	
	private RegisterCommentActivity activity;
	
	/**
	 * Instantiates a new example list adapter.
	 *
	 * @param context the context
	 */
	public CommentListAdapter(final Context context, RegisterCommentActivity activity) {
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.activity = activity;
	}
	
	
	/** {@inheritDoc} **/ 
	@Override
	public int getCount() {
		return objects.size();
	}

	/** {@inheritDoc} **/ 
	@Override
	public Comment getItem(final int position) {
		return objects.get(position);
	}

	/** {@inheritDoc} **/ 
	@Override
	public long getItemId(final int position) {
		return position;
	}

	/** {@inheritDoc} **/ 
	@Override
	public View getView(final int position, final View convertView, final ViewGroup parent) {
		
		//Inflar o layout da linha
		final View view = inflater.inflate(R.layout.comment_line, null);
		
		//fazer o bind dos componentes e preenche-los com os dados do 
		final Comment comment = getItem(position);
		
		final TextView comentario = (TextView) view.findViewById(R.id.comentario);
		
		
		//TODO nao esta vindo o nome do restaurante do servico...
		comentario.setText(comment.getMensagem());
		
		
		
		view.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(final View view) {
				//chamando o callback de click para a activity dona do adapter;
				activity.onListItemClick(position);
			}
		});
		
		return view;
	}


	public void updateList(final ArrayList<Comment> comment) {
		this.objects = comment;
		notifyDataSetChanged();
	}


}
