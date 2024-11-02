package com.example.riddle_beta1_definited;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;

public class TelaPaginaQuiz4 extends AppCompatActivity {

    private static final String VIDEO_URL = "https://www.youtube.com/watch?v=HCB2Rxxj7zE"; // Link do vídeo do YouTube

    WebView webView;
    ImageButton Videoeducativo;
    ImageView buttonsobreposicao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_opcoes2);

        buttonsobreposicao = findViewById(R.id.play_button_gif);
        buttonsobreposicao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarSobreposicao();
            }
        });
    }

    private void mostrarSobreposicao() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.actiivity_tela_pagina_quiz4); // Corrigido o nome do layout

        // Inicialize o WebView
        webView = dialog.findViewById(R.id.WebViewTest); // Certifique-se de que o ID do WebView esteja correto

        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());

        // Habilitar JavaScript
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Carregar a URL do vídeo do YouTube
        webView.loadUrl(VIDEO_URL);

        // Você pode adicionar um botão de "Abrir no navegador" caso queira
        Videoeducativo = dialog.findViewById(R.id.Videoeducativo);
        Videoeducativo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(VIDEO_URL));
                startActivity(intent);
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT); // Ajuste para altura dinâmica
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.dialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

        // Fechar o WebView ao fechar o diálogo
        dialog.setOnDismissListener(dialogInterface -> {
            if (webView != null) {
                webView.stopLoading(); // Para o carregamento do WebView
            }
        });
    }

    @Override
    protected void onDestroy() {
        if (webView != null) {
            webView.destroy(); // Limpa o WebView
        }
        super.onDestroy();
    }
}
