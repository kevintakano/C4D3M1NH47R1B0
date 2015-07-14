package com.jkl.view.fragments.impl;

import java.util.ArrayList;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import com.jkl.cademinhatribo.R;
import com.jkl.adapters.GroupListAdapter;
import com.jkl.domain.Group;
import com.jkl.cademinhatribo.RegisterCommentActivityImpl;
import com.jkl.view.fragments.GroupFragment;
import com.jkl.web.WebManager;

/**
 * The Class GroupFragmentImpl.
 * 
 * @author hdnn
 */
public class GroupFragmentImpl extends Fragment implements GroupFragment {
	

	/** The login edit. */
	//private TextView nomePrato;
	//private TextView nomeRestaurante;
	
	/** The password edit. */
	//private EditText passwordEdit;
	
	/** The login button. */
	private Button searchButton;
	
	private ProgressDialog pDialog;
	
	private ListView productsList;
	
	private GroupListAdapter adapter;
	private boolean baixaCaloria;
	private CheckBox baixaCaloriaFilter;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		return inflater.inflate(R.layout.fragment_groups_screen, null);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		setupView(view);
	}
	
	/**
	 * Setup view.
	 */
	private void setupView(View view) {
		productsList = (ListView) view.findViewById(R.id.list_products);
		searchButton = (Button) view.findViewById(R.id.btn_searchGrupos);

		baixaCaloria = baixaCaloriaFilter.isChecked();
		adapter = new GroupListAdapter(getActivity(), this);
		productsList.setAdapter(adapter);
		//productsList.setOnItemClickListener(this);
		searchButton.setOnClickListener(this);
	}
	
	/** {@inheritDoc} **/ 
	@Override
	public void onClick(final View view) {
		try {
			WebManager.getInstance().groupRequest(this);
			pDialog = new ProgressDialog(getActivity());
			pDialog.setMessage("Buscando...");
			pDialog.show();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void getGroupsSuccess(final ArrayList<Group> groups) {
		pDialog.dismiss();
		
		adapter.updateList(groups);
		//PreferencesUtils.saveUserLoggedIn(this, user);
		Toast.makeText(getActivity(), "Lista de produtos! "+groups.get(1).getName(), Toast.LENGTH_SHORT).show();
	}

	@Override
	public void requestFailed(final String message) {
		pDialog.dismiss();
		Toast.makeText(getActivity(), "Falha ao logar: "+message, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onListItemClick(int position) {
		//TODO: adapter.getItem(position) lhe devolvera a entidade completa clicada, fazer o que quiser.
		final Intent itRegister = new Intent(getActivity(), RegisterCommentActivityImpl.class);
		startActivity(itRegister);
	}

	/*@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		final Intent itRegister = new Intent(getActivity(), RegisterCommentActivityImpl.class);
		startActivity(itRegister);
		
	}*/

}
