package com.example.riddle_beta1_definited;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TelaParticipantes3 extends AppCompatActivity {

    private ImageView btnDesenvolvedro;
    private ImageView btnPlay;
    private ImageView btnLinkedinFilipi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_participantes3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void mudarTelaPlay(View view) {
        Intent intent = new Intent(this, TelaOpcoes2.class);
        startActivity(intent);
    }

    public void linkLinkedinFilipi(View view) {
        String url = "https://www.linkedin.com/in/filipi-pires-219331211/";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url)); // Define a URL correta
        startActivity(intent);
    }

    public void linkLinkedinRyan(View view) {
        String url = "https://www.linkedin.com/in/ryan-monsores-de-oliveira-2b54092a1/";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    public void linkLinkedinJoao(View view) {
        String url = "https://www.linkedin.com/in/jo%C3%A3o-albuquerquepeer/";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    public void linkLinkedinKaua(View view) {
        String url = "https://www.linkedin.com/in/kau%C3%A3-silva-rocha-0a2b0a1a5/overlay/photo/";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}