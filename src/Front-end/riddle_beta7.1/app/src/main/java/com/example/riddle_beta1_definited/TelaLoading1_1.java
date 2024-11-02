package com.example.riddle_beta1_definited;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class TelaLoading1_1 extends AppCompatActivity {

    private static final int SPLASH_DISPLAY_LENGTH = 3000; // Tempo em milissegundos (3 segundos)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_loading1_1); // Define o layout da tela de splash

        // Iniciar o serviço de música
        Intent musicIntent = new Intent(this, MusicService.class);
        startService(musicIntent);

        // Após o delay, iniciar a tela principal
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(TelaLoading1_1.this, TelaLoading1_2.class);
            startActivity(intent);
            finish(); // Finaliza a atividade de splash para que o usuário não possa voltar a ela
        }, SPLASH_DISPLAY_LENGTH);
    }

}
