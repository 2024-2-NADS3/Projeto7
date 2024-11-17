package com.example.riddle_beta1_definited;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TelaQuiz5 extends AppCompatActivity {

    // Variáveis existentes
    private TextView imgQuiz1;
    private TextView imgQuiz2;
    private TextView imgQuiz3;
    private TextView imgQuiz4;
    private TextView textTituloQuiz;
    private TextView textDescricao;
    private Set<Integer> perguntasExibidas = new HashSet<>();
    private Set<Integer> quizzesExibidos = new HashSet<>();
    private int dificuldade;

    // Nova variável para armazenar os resultados de cada quiz
    private List<Map<Integer, Boolean>> resultadosQuizzes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_quiz5);

        // Inicializando as Views
        imgQuiz1 = findViewById(R.id.btnQuiz1);
        imgQuiz2 = findViewById(R.id.btnQuiz2);
        imgQuiz3 = findViewById(R.id.btnQuiz3);
        imgQuiz4 = findViewById(R.id.btnQuiz4);
        textTituloQuiz = findViewById(R.id.textTituloQuiz);
        textDescricao = findViewById(R.id.TextDescricao);

        Bundle bundle = getIntent().getExtras();
        dificuldade = bundle.getInt("dificuldade");
        Log.d("TelaQuiz5", "Dificuldade recebida: " + dificuldade);

        // Carregar o quiz de dificuldade recebida
        carregarQuiz(dificuldade);

        // Ajuste de padding para as barras do sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void carregarQuiz(int dificuldade) {
        if (quizzesExibidos.size() < 3) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://wepapi-fyhrfda9gxdmfmch.canadacentral-01.azurewebsites.net/api/ControllerQuiz/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            QuizService quizService = retrofit.create(QuizService.class);
            Call<Quiz> call = quizService.obterQuizAleatorio(String.valueOf(dificuldade));

            call.enqueue(new Callback<Quiz>() {
                @Override
                public void onResponse(Call<Quiz> call, Response<Quiz> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        Quiz quiz = response.body();

                        if (quiz.getPerguntas() != null && !quiz.getPerguntas().isEmpty()) {
                            if (!quizzesExibidos.contains(quiz.getId_quiz())) {
                                quizzesExibidos.add(quiz.getId_quiz());
                                configurarPerguntas(quiz);
                            } else {
                                carregarQuiz(dificuldade);
                            }
                        } else {
                            Toast.makeText(TelaQuiz5.this, "Não há perguntas suficientes", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(TelaQuiz5.this, "Erro ao carregar o quiz", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Quiz> call, Throwable t) {
                    Toast.makeText(TelaQuiz5.this, "Falha na conexão: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Intent intent = new Intent(this, TelaPosQuiz6.class);
            intent.putExtra("resultadosQuizzes", (ArrayList<Map<Integer, Boolean>>) resultadosQuizzes);
            startActivity(intent);
        }
    }

    private void configurarPerguntas(Quiz quiz) {
        textTituloQuiz.setText(quiz.getTitulo());
        textDescricao.setText(quiz.getDescricao());

        atualizarPerguntas(quiz);

        View.OnClickListener listener = v -> {
            int index = -1;

            if (v.getId() == R.id.btnQuiz1) index = 0;
            else if (v.getId() == R.id.btnQuiz2) index = 1;
            else if (v.getId() == R.id.btnQuiz3) index = 2;
            else if (v.getId() == R.id.btnQuiz4) index = 3;

            if (index != -1 && quiz.getPerguntas() != null && index < quiz.getPerguntas().size()) {
                Pergunta perguntaSelecionada = quiz.getPerguntas().get(index);
                boolean respostaCorreta = perguntaSelecionada.isResposta();



                // Armazenar o resultado (ID do quiz e resultado da resposta)
                Map<Integer, Boolean> resultado = new HashMap<>();
                resultado.put(quiz.getId_quiz(), respostaCorreta);
                resultadosQuizzes.add(resultado);

                // Carregar o próximo quiz após responder
                carregarQuiz(dificuldade);
            }
        };

        imgQuiz1.setOnClickListener(listener);
        imgQuiz2.setOnClickListener(listener);
        imgQuiz3.setOnClickListener(listener);
        imgQuiz4.setOnClickListener(listener);
    }

    private void atualizarPerguntas(Quiz quiz) {
        List<Pergunta> perguntas = quiz.getPerguntas();
        if (perguntas != null && !perguntas.isEmpty()) {
            imgQuiz1.setText(getTextoPergunta(perguntas, 0));
            imgQuiz2.setText(getTextoPergunta(perguntas, 1));
            imgQuiz3.setText(getTextoPergunta(perguntas, 2));
            imgQuiz4.setText(getTextoPergunta(perguntas, 3));
        }
    }

    private String getTextoPergunta(List<Pergunta> perguntas, int index) {
        if (index < perguntas.size()) {
            Pergunta pergunta = perguntas.get(index);
            if (!perguntasExibidas.contains(pergunta.getId_perguntas())) {
                perguntasExibidas.add(pergunta.getId_perguntas());
                return pergunta.getPergunta();
            }
        }
        return "Pergunta não disponível";
    }
}
