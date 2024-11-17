package com.example.riddle_beta1_definited;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class
TelaParticipantes4 extends AppCompatActivity {


    private ImageView LogoTela2, btnODS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_participantes4);

        btnODS = findViewById(R.id.gif_ods);
        LogoTela2 = findViewById(R.id.imageView7);


        Glide.with(this).asGif().load(R.drawable.riddle_logo).into(LogoTela2);
        Glide.with(this).asGif().load(R.drawable.mini_mundo_animation).into(btnODS);

        // Botão Play para mudar a página
        btnODS.setOnClickListener(v -> {
            Intent intent = new Intent(this, TelaExplicaQuiz7.class); // Troque pela próxima tela desejada
            startActivity(intent);
        });
    }

    public void mudarTelaPlay(View view) {
        Intent intent = new Intent(this, TelaOpcoes3.class);
        startActivity(intent);
    }

    public void mudarTelaUsu(View view) {
        Intent intent = new Intent(this, TelaContrucao8.class);
        startActivity(intent);
    }

    public void linkLinkedinFilipi(View view) {
        String url = "https://www.linkedin.com/in/filipi-pires-219331211/";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url)); // Define a URL correta
        startActivity(intent);
    }

    public void linkLinkedinRyan(View view) {
        String url = "https://www.linkedin.com/in/ryan-oliveira-2b54092a1/";
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
        String url = "https://www.linkedin.com/in/kau%C3%A3-silva-rocha-0a2b0a1a5/";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}