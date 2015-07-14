package com.jkl.adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;
import com.jkl.cademinhatribo.R;
import com.jkl.domain.Group;
import com.jkl.view.fragments.impl.GroupFragmentImpl;
import com.jkl.view.fragments.GroupFragment;

/**
 * The Class ExampleListAdapter.
 */
public class GroupListAdapter extends BaseAdapter {
	
	/** The objects. */
	private List<Group> objects = new ArrayList<Group>();
	
	/** The inflater. */
	private final LayoutInflater inflater;
	
	private GroupFragment activity;
	private boolean liberado;
	
	/**
	 * Instantiates a new example list adapter.
	 *
	 * @param context the context
	 */
	public GroupListAdapter(final Context context, GroupFragment activity) {
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
	public Group getItem(final int position) {
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
		final View view = inflater.inflate(R.layout.fragment_group_line, null);
		
		//fazer o bind dos componentes e preenche-los com os dados do 
		final Group group = getItem(position);
		
		//tentativa de implementacao dos filtros
		/*if (!baixaCaloria) liberado=true; //filtro baixa caloria desativado
			else { //filtro baixa caloria ativado
				if (Integer.getInteger(product.getValorEnergetico()) < 250) { //se calorias sao, de fato, baixa
					liberado = true;
				} else liberado = false;
			
		}
		if (liberado) {*/
		final TextView nomeGrupoText = (TextView) view.findViewById(R.id.textNomeGrupo);
		final TextView descricao = (TextView) view.findViewById(R.id.textDescricao);
		
		//TODO nao esta vindo o nome do restaurante do servico...
		nomeGrupoText.setText("Nome: " + group.getName());
		//descricao.setText("descricao: " + group.getDescricao());

		

		view.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(final View view) {
				//chamando o callback de click para a activity dona do adapter;
				activity.onListItemClick(position);
			}
		});
		//} //parenteses fechando a condicao dos filtros
		return view;
		
	}


	public void updateList(final ArrayList<Group> groups) {
		this.objects = groups;
		notifyDataSetChanged();
	}

}
