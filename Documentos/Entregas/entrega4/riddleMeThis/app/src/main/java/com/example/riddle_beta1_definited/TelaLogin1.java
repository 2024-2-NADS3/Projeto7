package com.example.riddle_beta1_definited;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class TelaLogin1 extends AppCompatActivity {

    private Button btnLogin;
    private EditText textEntrar;
    private TextView textFalhaLogin;
    private ImageView cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_telalogin1);

        textEntrar = findViewById(R.id.textVai); // inicializa textEntrar

        btnLogin = findViewById(R.id.btnLogin); // inicializa o botão de login (se precisar)
        textFalhaLogin = findViewById(R.id.textFalhaLogin);

        // Configura o listener para o botão de login, caso precise
        btnLogin.setOnClickListener(v -> {
            String nome = textEntrar.getText().toString();

            // Criptografa o nome para validação no login
            String nomeCifradoParaLogin = cifraDeCesar(nome, 3);

            // Realizar o login com o nome cifrado
            realizarLogin(nomeCifradoParaLogin);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    private void realizarLogin(String nomeCifrado) {
        String url = "https://wepapi-fyhrfda9gxdmfmch.canadacentral-01.azurewebsites.net/api/Servidor/Login";

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("nome", nomeCifrado);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonBody,
                response -> {
                    try {
                        boolean sucesso = response.getBoolean("sucesso");
                        if (sucesso) {
                            Intent intent = new Intent(this, TelaOpcoes3.class);
                            startActivity(intent);
                            Toast.makeText(this, "Seja Bem-Vindo!", Toast.LENGTH_SHORT).show();
                        } else {
                            Intent intent = new Intent(this, TelaExplicaQuiz7.class);
                            startActivity(intent);
                            Toast.makeText(this, "Seja Bem-Vindo2!", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Intent intent = new Intent(this, TelaParticipantes4.class);
                        startActivity(intent);
                        Toast.makeText(this, "Seja Bem-Vindo3!", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    error.printStackTrace();
                    Toast.makeText(this, "Login Incorreto", Toast.LENGTH_SHORT).show();
                    //Errei fui pra ca
                });

        RequestQueue queue = Volley.newRequestQueue(TelaLogin1.this);
        queue.add(request);
    }

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
    public void VoltarTela2(View view){
        Intent intent = new Intent(this, TelaCadastro2.class);
        startActivity(intent);
    }

}