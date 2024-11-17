package com.example.riddle_beta1_definited;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Map;

public class TelaPosQuiz6 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_pos_quiz6);


        // Suponha que este seja o TextView onde queremos exibir os resultados
        TextView textViewResultados = findViewById(R.id.textResultado);

// Extrai os dados do ArrayList passado pela Intent
        ArrayList<Map<Integer, Boolean>> resultadosQuizzes =
                (ArrayList<Map<Integer, Boolean>>) getIntent().getSerializableExtra("resultadosQuizzes");

// Verifica se o ArrayList não é nulo
        if (resultadosQuizzes != null) {
            StringBuilder resultadosTexto = new StringBuilder();

            // Itera sobre cada Map no ArrayList
            for (int i = 0; i < resultadosQuizzes.size(); i++) {
                Map<Integer, Boolean> quizResultado = resultadosQuizzes.get(i);

                // Adiciona os resultados de cada quiz ao StringBuilder
                resultadosTexto.append("Quiz ").append(i + 1).append(":\n");

                for (Map.Entry<Integer, Boolean> entrada : quizResultado.entrySet()) {
                    int idPergunta = entrada.getKey();
                    boolean acertou = entrada.getValue();

                    resultadosTexto.append("Pergunta ").append(idPergunta)
                            .append(": ")
                            .append(acertou ? "Correta" : "Incorreta")
                            .append("\n");
                }
                resultadosTexto.append("\n");
            }

            // Define o texto formatado no TextView
            textViewResultados.setText(resultadosTexto.toString());
        } else {
            textViewResultados.setText("Nenhum resultado disponível.");
        }


    }

    public void MudarTelaSiteQuiz(View view){
            String url = "https://brasil.un.org/pt-br/sdgs";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url)); // Define a URL correta
            startActivity(intent);
    }

    public void MudarTelaPrincipal5(View view){
        Intent intent = new Intent(this, TelaOpcoes3.class);
        startActivity(intent);
    }

}