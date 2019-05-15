package itson.mx.bachapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class InicioActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView login, forgot, create;
    private EditText user, pass;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        login = findViewById(R.id.login_Loginbtn);
        login.setOnClickListener(this);
        forgot = findViewById(R.id.login_forgot);
        forgot.setOnClickListener(this);
        create = findViewById(R.id.login_create);
        create.setOnClickListener(this);

        user = findViewById(R.id.login_emailPhone);
        pass = findViewById(R.id.login_password);

        auth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.login_create) {
            Intent intent = new Intent(getApplicationContext(), RegistroActivity.class);
            startActivity(intent);
            finish();
        }
        if(v.getId() == R.id.login_forgot) {
            Toast.makeText(this, "Test Forgot", Toast.LENGTH_SHORT).show();
        }
        if(v.getId() == R.id.login_Loginbtn) {
            if(TextUtils.isEmpty(user.getText().toString()) || TextUtils.isEmpty(pass.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Hay campos vacios", Toast.LENGTH_SHORT)
                        .show();
            } else {
                loginUsuario();
            }
        }

    }
    private void loginUsuario() {
        auth.signInWithEmailAndPassword(user.getText().toString(), pass.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Login exitoso", Toast.LENGTH_SHORT)
                                    .show();

                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
}
