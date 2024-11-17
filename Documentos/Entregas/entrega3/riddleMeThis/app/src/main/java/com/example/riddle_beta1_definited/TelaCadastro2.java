package com.example.riddle_beta1_definited;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class TelaCadastro2 extends AppCompatActivity {

    private EditText emailInput;
    private Button loginButton;
    private TextView cifra, textResultadoLogin;
    private Button btnLog;
    private ImageView cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro2);

        emailInput = findViewById(R.id.email_input);
        loginButton = findViewById(R.id.login_button);
        cifra = findViewById(R.id.cifra);
        textResultadoLogin = findViewById(R.id.textResultadoLogin);

        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String nome = emailInput.getText().toString();

                // Exibe o texto cifrado na TextView 'cifra' usando a cifra de César
                String textoCifrado = cifraDeCesar(nome, 3); // 3 é o deslocamento da cifra
                cifra.setText(textoCifrado);

                // Envia o texto original para o cadastro
                realizarCadastro(textoCifrado);

            }
        });
    }

    private void realizarCadastro(String email) {
        String url = "https://wepapi-fyhrfda9gxdmfmch.canadacentral-01.azurewebsites.net/api/Servidor/Cadastrar";

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("nome", email);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonBody,
                response -> {
                    try {
                        boolean sucesso = response.getBoolean("sucesso");
                        if (sucesso) {
                            Intent intent = new Intent(this, TelaParticipantes4.class);
                            startActivity(intent);
                            Toast.makeText(this, "Seja Bem-Vindo!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(this, "Falha no cadastro, tente novamente!", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Intent intent = new Intent(this, TelaExplicaQuiz7.class);
                        startActivity(intent);
                        Toast.makeText(this, "Erro ao processar a resposta!", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    // Verifica se o erro é um problema de nome duplicado, analisando a resposta de erro
                    if (error.networkResponse != null && error.networkResponse.statusCode == 409) { // Exemplo de código para nome duplicado
                        Toast.makeText(this, "Esse nome já existe", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Cadastro Realizado!", Toast.LENGTH_SHORT).show();
                        //Intent intent = new Intent(this, TelaLogin.class);
                        //startActivity(intent);
                    }
                    error.printStackTrace();
                });

        RequestQueue queue = Volley.newRequestQueue(TelaCadastro2.this);
        queue.add(request);
    }

        // Função para cifrar o nome com cifra de César
    public String cifraDeCesar(String texto, int deslocamento) {
        StringBuilder resultado = new StringBuilder();

        for (char caracter : texto.toCharArray()) {
            if (Character.isLetter(caracter)) {
                char base = Character.isUpperCase(caracter) ? 'A' : 'a';
                char novaLetra = (char) ((caracter - base + deslocamento) % 26 + base);
                resultado.append(novaLetra);
            } else {
                resultado.append(caracter); // Mantém espaços e pontuação
            }
        }

        return resultado.toString();
    }

    public void EntrarLogin10(View view){
        Intent intent = new Intent(this, TelaLogin1.class);
        startActivity(intent);
    }
    public void VoltarTela2(View view){
        cancelButton = findViewById(R.id.cancelButton);
        Intent intent = new Intent(this, TelaOpcoes3.class);
        startActivity(intent);
    }
}
