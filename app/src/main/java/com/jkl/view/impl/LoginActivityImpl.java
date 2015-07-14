package com.jkl.view.impl;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jkl.cademinhatribo.MainActivity;
import com.jkl.view.LoginActivity;
import com.jkl.cademinhatribo.R;
/**
 * Created by Aguiar on 04/07/2015.
 */
public class LoginActivityImpl extends Activity implements LoginActivity {

    /** The login edit. */
    private EditText loginEdit;

    /** The password edit. */
    private EditText passwordEdit;

    /** The login button. */
    private Button loginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupView();
    }

    /**
     * Setup view.
     */
    private void setupView() {
        loginEdit = (EditText) findViewById(R.id.edt_login);
        passwordEdit = (EditText) findViewById(R.id.edt_password);
        loginButton = (Button) findViewById(R.id.btn_login);
        loginButton.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /** {@inheritDoc} **/
    @Override
    public void onClick(final View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

