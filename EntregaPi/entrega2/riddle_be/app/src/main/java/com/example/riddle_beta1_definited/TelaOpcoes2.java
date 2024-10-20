package com.example.riddle_beta1_definited;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;

public class TelaOpcoes2 extends AppCompatActivity {
    private ImageView botao_play;
    private ImageView playButtonGif;
    private View lastSelectedButton = null;
    private Drawable[] originalBackgrounds;
    private ImageView LogoTela2;

    private ImageView btnDesenvolvedor;
    private ImageView btnUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_opcoes2);

        // Inicializar ImageView
        botao_play = findViewById(R.id.btnPlay);
        playButtonGif = findViewById(R.id.play_button_gif);
        LogoTela2 = findViewById(R.id.imageView8);

        // Estado inicial do botão Play
        deactivatePlayButton();


        Glide.with(this).asGif().load(R.drawable.riddle_logo).into(LogoTela2);

        // Botão Play para mudar a página
        botao_play.setOnClickListener(v -> {
            Intent intent = new Intent(this, TelaPaginaQuiz4.class); // Troque pela próxima tela desejada
            startActivity(intent);
        });

        // GIF funcionando como botão
        playButtonGif.setOnClickListener(v -> {
            if (playButtonGif.getVisibility() == View.VISIBLE) {
                mostrarSobreposicao(); // Funcionalidade ao clicar no GIF
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.btnUsuario), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializar botões e armazenar fundos originais
        initializeButtons();
    }

    private void initializeButtons() {
        // Encontrar todos os botões e armazenar seus fundos originais
        MaterialButton button1 = findViewById(R.id.button1);
        MaterialButton button2 = findViewById(R.id.button2);
        MaterialButton button3 = findViewById(R.id.button3);
        MaterialButton button4 = findViewById(R.id.button4);
        MaterialButton button5 = findViewById(R.id.button5);
        MaterialButton button6 = findViewById(R.id.button6);

        originalBackgrounds = new Drawable[] {
                button1.getBackground(),
                button2.getBackground(),
                button3.getBackground(),
                button4.getBackground(),
                button5.getBackground(),
                button6.getBackground()
        };

        // Definir os listeners de clique para os botões de opção
        button1.setOnClickListener(this::onOptionClick);
        button2.setOnClickListener(this::onOptionClick);
        button3.setOnClickListener(this::onOptionClick);
        button4.setOnClickListener(this::onOptionClick);
        button5.setOnClickListener(this::onOptionClick);
        button6.setOnClickListener(this::onOptionClick);
    }

    // Método de clique para os botões de opção
    public void onOptionClick(View view) {
        if (view == lastSelectedButton) {
            // Desmarcar o botão selecionado e desativar o botão Play
            deactivatePlayButton();

            // Restaurar o fundo original do botão
            if (lastSelectedButton instanceof MaterialButton) {
                int index = getButtonIndex((MaterialButton) lastSelectedButton);
                if (index != -1) {
                    ((MaterialButton) lastSelectedButton).setBackground(originalBackgrounds[index]);
                }
            }

            lastSelectedButton = null;
        } else {
            // Ativar o botão Play e exibir o GIF
            activatePlayButton();

            // Desmarcar o botão selecionado anteriormente, se houver
            if (lastSelectedButton != null && lastSelectedButton instanceof MaterialButton) {
                // Restaurar o fundo original do último botão
                int index = getButtonIndex((MaterialButton) lastSelectedButton);
                if (index != -1) {
                    ((MaterialButton) lastSelectedButton).setBackground(originalBackgrounds[index]);
                }
                lastSelectedButton.setSelected(false);
            }

            // Marcar o botão clicado como selecionado
            if (view instanceof MaterialButton) {
                // Aplicar a borda verde ao botão selecionado
                view.setBackgroundResource(R.drawable.option_select_border);


            }

            view.setSelected(true);
            lastSelectedButton = view;
        }
    }

    // Obtém o índice do botão na lista de botões
    private int getButtonIndex(MaterialButton button) {
        if (button.getId() == R.id.button1) return 0;
        if (button.getId() == R.id.button2) return 1;
        if (button.getId() == R.id.button3) return 2;
        if (button.getId() == R.id.button4) return 3;
        if (button.getId() == R.id.button5) return 4;
        if (button.getId() == R.id.button6) return 5;
        return -1;
    }

    // Ativa o botão Play
    private void activatePlayButton() {
        botao_play.setEnabled(true);
        playButtonGif.setVisibility(View.VISIBLE);
        Glide.with(this).asGif().load(R.drawable.play_button_animation).into(playButtonGif);
    }

    // Desativa o botão Play
    private void deactivatePlayButton() {
        botao_play.setEnabled(false);
        playButtonGif.setVisibility(View.GONE);
    }

    public void mudarTelaDesenvolvedores(View view){
        btnDesenvolvedor = findViewById(R.id.btnDesenvolvedor);

        Intent intent = new Intent(this, TelaParticipantes3.class);
        startActivity(intent);
    }

    public void IrPaginaPerfilQuiz(View view){
        btnDesenvolvedor = findViewById(R.id.btnDesenvolvedor);

        Intent intent = new Intent(this, TelaPaginaQuiz4.class);
        startActivity(intent);
    }


    private void mostrarSobreposicao() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.actiivity_tela_pagina_quiz4);

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, 1800);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.dialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }
}