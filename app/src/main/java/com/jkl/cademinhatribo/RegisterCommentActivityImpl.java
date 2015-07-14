package com.jkl.cademinhatribo;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

//import com.jkl.app.component.adapters.CommentListAdapter;
import com.jkl.domain.Comment;
import com.jkl.view.impl.RegisterCommentActivity;
import com.jkl.web.WebManager;
import com.jkl.adapters.CommentListAdapter;
import com.jkl.cademinhatribo.R;
public class RegisterCommentActivityImpl extends Activity implements RegisterCommentActivity {
	
	
	//private String id_prato;
	//private String id_usuario;
	//private String mensagem;
	//private String data;
	int TAKE_PHOTO_CODE = 0;
	public static int count=0;
	
	/** The photo button. */
	private ImageButton takeShot;
	
	/** The Comment register button. */
	private Button registerButton;

	/** The comment edit. */
	private EditText Comentario;

	private ListView commentsList;
	
	private CommentListAdapter adapter;

	/** The p dialog. */
	private ProgressDialog pDialog;

	/** {@inheritDoc} **/
	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.group_detail);

		setupView();
		
		try {
			WebManager.getInstance().getCommentRequest(this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pDialog = new ProgressDialog(this);
		pDialog.setMessage("Carregando...");
		pDialog.show();
		
	}

	/**
	 * Setup view.
	 */
	private void setupView() {
		 registerButton = (Button) findViewById(R.id.btnRegistraComentario);
		 Comentario = (EditText) findViewById(R.id.editComentario);
		 commentsList = (ListView) findViewById(R.id.list_comments);
		 adapter = new CommentListAdapter(this, this);
		 commentsList.setAdapter(adapter);
		 //takeShot = (ImageButton) findViewById(R.id.imagePrato);
		 registerButton.setOnClickListener(this);
		 takeShot.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		
		if (v == registerButton){
			if (isValidFields()) {
				final Comment comment = new Comment();
				comment.setMensagem(Comentario.getText().toString());
				comment.setId_grupo("1" );
				comment.setId_usuario("1");
				comment.setData("2015-02-19 20:48:40.331620");
				
				
	
				try {
					WebManager.getInstance().registerComment(comment,this);
					pDialog = new ProgressDialog(this);
					pDialog.setMessage("Enviando...");
					pDialog.show();
				} catch (final Exception e) {
					e.printStackTrace();
				}
			}
		}else {
			final String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/picFolder/"; 
	        File newdir = new File(dir); 
	        newdir.mkdirs();
	        // here,counter will be incremented each time,and the picture taken by camera will be stored as 1.jpg,2.jpg and likewise.
            count++;
            String file = dir+count+".jpg";
            File newfile = new File(file);
            try {
                newfile.createNewFile();
            } catch (IOException e) {}       

            Uri outputFileUri = Uri.fromFile(newfile);

            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); 
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);

            startActivityForResult(cameraIntent, TAKE_PHOTO_CODE);
		}

	}

	/** {@inheritDoc} **/
	@Override
	public void registerSuccess() {
		if (pDialog != null){
			pDialog.dismiss();
		}
		Toast.makeText(this, "Comentário cadastrado com sucesso!",
				Toast.LENGTH_SHORT).show();
		finish();
	}


	@Override
	public void getCommentSuccess(final ArrayList<Comment> comment) {
		pDialog.dismiss();
		
		adapter.updateList(comment);
		//PreferencesUtils.saveUserLoggedIn(this, user);
		Toast.makeText(this, "Lista de comentarios! "+comment.get(1).getMensagem(), Toast.LENGTH_SHORT).show();
	}

	/*@Override
	public void requestFailed(final String message) {
		pDialog.dismiss();
		Toast.makeText(this, "Falha ao logar: "+message, Toast.LENGTH_SHORT).show();
	}*/
	
	
	/**
	 * Validate if fields are not empty.
	 *
	 * @return true if they are not, false otherwise
	 */
	private boolean isValidFields() {
		if ("".equals(Comentario.getText().toString().trim())) {
			Toast.makeText(this, "Todos os campos sao obrigatorios!",
					Toast.LENGTH_SHORT).show();
			return false;
		} else {

			return true;
		}

	}

	/** {@inheritDoc} **/
	@Override
	public void onBackPressed() {
		new AlertDialog.Builder(this)
				.setIcon(android.R.drawable.ic_dialog_alert)
				.setTitle("Cancelar Registro")
				.setMessage(
						"Voce realmente deseja sair do cadastro de comentario? Seus dados nao serao salvos")
				.setPositiveButton("Sim",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								finish();
							}

						}).setNegativeButton("Nao", null).show();
	}

	/** {@inheritDoc} **/
	@Override
	public void requestFailed(final String message) {
		if (pDialog != null) {
			pDialog.dismiss();
		}
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onListItemClick(int position) {
		// TODO Auto-generated method stub
		
		
	}

}
