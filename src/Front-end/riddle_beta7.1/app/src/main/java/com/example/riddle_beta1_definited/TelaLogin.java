package com.example.riddle_beta1_definited;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
public class TelaLogin extends AppCompatActivity {

    private EditText emailInput;
    private EditText passwordInput;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);

        // Vincular os campos e o botão ao layout
        emailInput = findViewById(R.id.email_input);
        passwordInput = findViewById(R.id.password_input);
        loginButton = findViewById(R.id.login_button);

        // Configurar o evento de clique no botão de login
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailInput.getText().toString();
                String password = passwordInput.getText().toString();

                // Validação básica (você pode melhorar esta parte)
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(TelaLogin.this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
                } else {
                    // Exemplo de validação simples para login
                    if (email.equals("user@example.com") && password.equals("123456")) {
                        // Login bem-sucedido, ir para outra tela
                        Toast.makeText(TelaLogin.this, "Login bem-sucedido!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(TelaLogin.this, TelaPerfil.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(TelaLogin.this, "E-mail ou senha incorretos", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
