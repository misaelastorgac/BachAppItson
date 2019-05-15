package itson.mx.bachapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView createBtn, forgot, login;
    private EditText firstName, lastName, phone, email, password;
    private Spinner city;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        city = findViewById(R.id.reg_city);

        firstName = findViewById(R.id.reg_firstName);
        lastName = findViewById(R.id.reg_lastName);
        phone = findViewById(R.id.reg_phone);
        email = findViewById(R.id.reg_email);
        password = findViewById(R.id.reg_password);

        createBtn = findViewById(R.id.reg_createBtn);
        createBtn.setOnClickListener(this);
        forgot = findViewById(R.id.reg_forgotBtn);
        forgot.setOnClickListener(this);
        login = findViewById(R.id.reg_loginBtn);
        login.setOnClickListener(this);

        auth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.reg_loginBtn) {
            Intent intent = new Intent(getApplicationContext(), InicioActivity.class);
            startActivity(intent);
            finish();
        }
        if(v.getId() == R.id.reg_forgotBtn) {
            Toast.makeText(this, "Test Forgot", Toast.LENGTH_SHORT).show();
        }
        if(v.getId() == R.id.reg_createBtn) {
            if(TextUtils.isEmpty(firstName.getText().toString()) ||
            TextUtils.isEmpty(lastName.getText().toString()) ||
            TextUtils.isEmpty(phone.getText().toString()) ||
            TextUtils.isEmpty(email.getText().toString()) ||
            TextUtils.isEmpty(password.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Hay campos vacios", Toast.LENGTH_SHORT).show();
            } else {
                registrarUsuario();
            }
        }
    }
    private void registrarUsuario() {
        auth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Usuario creado con exito.", Toast.LENGTH_SHORT)
                                    .show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT)
                                    .show();
                        }
                    }
                });
    }
}
