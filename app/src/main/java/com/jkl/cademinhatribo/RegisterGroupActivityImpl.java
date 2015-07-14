package com.jkl.cademinhatribo;

import java.io.File;
import java.io.IOException;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import com.jkl.domain.Group;

import com.jkl.domain.Group;
import com.jkl.view.impl.RegisterGroupActivity;
import com.jkl.web.WebManager;

/**
 * The Class RegisterActivityImpl.
 *
 * @author llobo
 */
public class RegisterGroupActivityImpl extends Activity implements
		RegisterGroupActivity {
	
	int TAKE_PHOTO_CODE = 0;
	public static int count=0;
	/** The photo button. */
	private ImageButton takeShot;
	/** The register button. */
	private Button registerButton;

	/** The name edit. */
	private EditText NomeGrupoEdit;
	private EditText Descricao;



	// private EditText Comentario;

	/** The p dialog. */
	private ProgressDialog pDialog;

	/** {@inheritDoc} **/
	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.group_add_new);

		setupView();
	}

	/**
	 * Setup view.
	 */
	private void setupView() {
		registerButton = (Button) findViewById(R.id.btnRegistraGrupo);

		NomeGrupoEdit = (EditText) findViewById(R.id.editNomeGrupo);
		Descricao = (EditText) findViewById(R.id.editDescricao);

		//takeShot = (ImageButton) findViewById(R.id.imageGrupo);
		// Comentario = (EditText) findViewById(R.id.editComentario);

		registerButton.setOnClickListener(this);
		//takeShot.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v == registerButton) {
			if (isValidFields()) {
				final Group grupo = new Group();
				grupo.setName(NomeGrupoEdit.getText().toString());
				//grupo.setDescricao(Descricao.getText().toString());


				//grupo.setFoto_Url("");
	
				try {
					WebManager.getInstance().registerGroup(grupo, this);
					pDialog = new ProgressDialog(this);
					pDialog.setMessage("Cadastrando...");
					pDialog.show();
				} catch (final Exception e) {
					e.printStackTrace();
				}
			}
			
		} else {
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
		Toast.makeText(this, "Produto cadastrado com sucesso!",
				Toast.LENGTH_SHORT).show();
		finish();
	}

	/**
	 * Validate if fields are not empty.
	 *
	 * @return true if they are not, false otherwise
	 */
	private boolean isValidFields() {
		if ("".equals(NomeGrupoEdit.getText().toString().trim())
				|| "".equals(Descricao.getText().toString().trim())
				) {
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
						"Voce realmente deseja sair do cadastro de produtos? Seus dados nao serao salvos")
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

}
