package com.jkl.cademinhatribo;

/**
 * Created by Aguiar on 12/07/2015.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.jkl.view.impl.RegisterActivity;
import com.jkl.domain.User;
import com.jkl.web.WebManager;



public class RegisterActivityImpl extends Activity implements RegisterActivity {

    /** The register button. */
    private Button registerButton;

    /** The name edit. */
    private EditText nameEdit;

    /** The last name edit. */
    private EditText lastNameEdit;

    /** The email edit. */
    private EditText emailEdit;

    /** The password edit. */
    private EditText passwordEdit;

    /** The password confirm edit. */
    private EditText passwordConfirmEdit;

    /** The city edit. */
    private EditText cityEdit;

    /** The nick name edit. */
    private EditText nickNameEdit;

    /** The sex radio. */
    private RadioGroup sexRadio;

    /** The p dialog. */
    private ProgressDialog pDialog;

    /** {@inheritDoc} **/
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_user_screen);

        setupView();
    }

    /**
     * Setup view.
     */
    private void setupView() {
        registerButton = (Button) findViewById(R.id.btn_register);
        nameEdit = (EditText) findViewById(R.id.edt_name_user);
        lastNameEdit = (EditText) findViewById(R.id.edt_lastname_user);
        emailEdit = (EditText) findViewById(R.id.edt_email_user);
        passwordEdit = (EditText) findViewById(R.id.edt_password);
        passwordConfirmEdit = (EditText) findViewById(R.id.edt_password_confirm);
        cityEdit = (EditText) findViewById(R.id.edt_city_user);
        nickNameEdit = (EditText) findViewById(R.id.edt_nickname_user);
        sexRadio = (RadioGroup) findViewById(R.id.radioSex);
        registerButton.setOnClickListener(this);
    }

    /** {@inheritDoc} **/
    //@Override
    public void registerSuccess() {
        Toast.makeText(this, "Usuario cadastrado com sucesso! Log-in!",
                Toast.LENGTH_SHORT).show();
        finish();
    }

    /** {@inheritDoc} **/
    @Override
    public void onClick(final View view) {
        if (isValidFields()) {
            final User user = new User();
            user.setName(nameEdit.getText().toString());
            user.setLastName(lastNameEdit.getText().toString());
            user.setEmail(emailEdit.getText().toString());
            user.setNickName(nickNameEdit.getText().toString());
            user.setPassword(passwordEdit.getText().toString());
            user.setCity(cityEdit.getText().toString());
            if (sexRadio.getCheckedRadioButtonId() == R.id.radioMale) {
                user.setGender("M");
            } else {
                user.setGender("F");
            }

            try {
                WebManager.getInstance().registerUser(user, this);
                pDialog = new ProgressDialog(this);
                pDialog.setMessage("Cadastrando...");
                pDialog.show();
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Validate if fields are not empty.
     *
     * @return true if they are not, false otherwise
     */
    private boolean isValidFields() {
        if ("".equals(nameEdit.getText().toString().trim())
                || "".equals(lastNameEdit.getText().toString().trim())
                || "".equals(nameEdit.getText().toString().trim())
                || "".equals(emailEdit.getText().toString().trim())
                || "".equals(passwordEdit.getText().toString().trim())
                || "".equals(passwordConfirmEdit.getText().toString().trim())
                || "".equals(cityEdit.getText().toString().trim())
                || "".equals(nickNameEdit.getText().toString().trim())) {
            Toast.makeText(this, "Todos os campos sao obrigatorios!",
                    Toast.LENGTH_SHORT).show();
            return false;
        } else {
            if (passwordEdit.getText().toString()
                    .equals(passwordConfirmEdit.getText().toString())) {
                return true;
            } else {
                Toast.makeText(this, "As senhas nao batem!", Toast.LENGTH_SHORT)
                        .show();
                return false;
            }

        }
    }

    /** {@inheritDoc} **/
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Cancelar Registro")
                .setMessage(
                        "Voce realmente deseja sair do cadastro de usuarios? Seus dados nao serao salvos")
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
