package com.example.riddle_beta1_definited;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TelaLoading1_1 extends AppCompatActivity {

    private static final int SPLASH_DISPLAY_LENGTH = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_loading1_1);

        // Configurar o temporizador para mudar para a próxima tela após o SPLASH_DISPLAY_LENGTH
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(TelaLoading1_1.this, TelaLoading1_2.class);
            startActivity(intent);
            finish();  // Opcional: fecha a tela de loading para que não seja exibida ao voltar
        }, SPLASH_DISPLAY_LENGTH);

        // Ajustes de layout para bordas
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
