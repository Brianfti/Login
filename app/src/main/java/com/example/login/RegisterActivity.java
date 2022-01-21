package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText etUser;
    EditText etPass;
    Button btnRegister;
    EditText etRepass;
    TextView tvRegister;

    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUser = (EditText) findViewById(R.id.etUserReg);
        etPass = (EditText) findViewById(R.id.etPassReg);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        tvRegister = (TextView) findViewById(R.id.tvIniciar);
        etRepass = (EditText) findViewById(R.id.etPassReg2);

        db = new DBHelper(this);

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = etUser.getText().toString();
                String password = etPass.getText().toString();
                String rePassword = etRepass.getText().toString();

                if (user.equals("") || password.equals("") || rePassword.equals("")){
                    Toast.makeText(RegisterActivity.this, "Fields incompletes", Toast.LENGTH_SHORT).show();
                } else if (password.equals(rePassword)) {
                    Boolean checkuser = db.checkUsername(user);
                        if (checkuser == false){
                            Boolean insert = db.insertData(user, password);
                            if (insert == true){
                                Toast.makeText(RegisterActivity.this, "Registered succesfully", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(RegisterActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(RegisterActivity.this, "User already exists", Toast.LENGTH_SHORT).show();
                        }
                } else {
                    Toast.makeText(RegisterActivity.this, "Incorrect password", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}