package com.example.riddle_beta1_definited;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;

public class TelaOpcoes3 extends AppCompatActivity {

    private ImageView btnODS;
    private View lastSelectedButton = null;
    private Drawable[] originalBackgrounds;
    private ImageView LogoTela2;

    private ImageView btnDesenvolvedor;
    private ImageView btnUsuario;
    private int selectedDifficulty = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_opcoes3);

        // Inicializar GIF ODSs

        btnODS = findViewById(R.id.gif_ods);
        LogoTela2 = findViewById(R.id.imageView8);


        Glide.with(this).asGif().load(R.drawable.riddle_logo).into(LogoTela2);
        Glide.with(this).asGif().load(R.drawable.mini_mundo_animation).into(btnODS);

        // Botão Play para mudar a página
        btnODS.setOnClickListener(v -> {
            Intent intent = new Intent(this, TelaExplicaQuiz7.class); // Troque pela próxima tela desejada
            startActivity(intent);
        });



        // GIF funcionando como botão
        btnODS.setOnClickListener(v -> {
            if (btnODS.getVisibility() == View.VISIBLE) {
                Intent intent = new Intent(this, TelaExplicaQuiz7.class); // Troque pela próxima tela desejada
                startActivity(intent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.btnUsuario), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //deactivatePlayButton();
        initializeButtons();
    }

    private void initializeButtons() {
        MaterialButton button1 = findViewById(R.id.button1); // Dificuldade 1
        MaterialButton button2 = findViewById(R.id.button2); // Dificuldade 2
        MaterialButton button3 = findViewById(R.id.button3); // Dificuldade 3
        MaterialButton button4 = findViewById(R.id.button4); // Dificuldade 4
        MaterialButton button5 = findViewById(R.id.button5); // Dificuldade 5
        MaterialButton button6 = findViewById(R.id.button6); // Dificuldade 6

        originalBackgrounds = new Drawable[] {
                button1.getBackground(),
                button2.getBackground(),
                button3.getBackground(),
                button4.getBackground(),
                button5.getBackground(),
                button6.getBackground()
        };

        // Define os listeners de clique para os botões de dificuldade
        button1.setOnClickListener(v -> onOptionClick(v, 1));
        button2.setOnClickListener(v -> onOptionClick(v, 2));
        button3.setOnClickListener(v -> onOptionClick(v, 3));
        button4.setOnClickListener(v -> onOptionClick(v, 4));
        button5.setOnClickListener(v -> onOptionClick(v, 5));
        button6.setOnClickListener(v -> onOptionClick(v, 6));
    }

    public void onOptionClick(View view, int difficulty) {
        if (lastSelectedButton != null) {
            resetLastButtonBackground();
        }

        // Marcar o botão clicado como selecionado e guardar a dificuldade
        if (view instanceof MaterialButton) {
            view.setBackgroundResource(R.drawable.option_select_border);
            selectedDifficulty = difficulty; // Armazena a dificuldade selecionada
            lastSelectedButton = view;

            // Inicia a tela do quiz diretamente após a seleção
            Intent intent = new Intent(this, TelaQuiz5.class); // Alterado para TelaQuiz5
            intent.putExtra("dificuldade", selectedDifficulty); // Passa a dificuldade selecionada
            startActivity(intent);
        }
    }

    private void resetLastButtonBackground() {
        if (lastSelectedButton instanceof MaterialButton) {
            int index = getButtonIndex((MaterialButton) lastSelectedButton);
            if (index != -1 && index < originalBackgrounds.length) {
                ((MaterialButton) lastSelectedButton).setBackground(originalBackgrounds[index]);
            }
        }
    }

    private int getButtonIndex(MaterialButton button) {
        MaterialButton[] buttons = {
                findViewById(R.id.button1),
                findViewById(R.id.button2),
                findViewById(R.id.button3),
                findViewById(R.id.button4),
                findViewById(R.id.button5),
                findViewById(R.id.button6)
        };

        for (int i = 0; i < buttons.length; i++) {
            if (buttons[i] == button) {
                return i;
            }
        }
        return -1; // Retorna -1 se o botão não estiver na lista
    }
    public void mudarTelaDesenvolvedores(View view){
        btnDesenvolvedor = findViewById(R.id.btnDesenvolvedor);

        Intent intent = new Intent(this, TelaParticipantes4.class);
        startActivity(intent);
    }

    public void IrPaginaPerfilQuiz(View view){
        btnDesenvolvedor = findViewById(R.id.btnDesenvolvedor);

        Intent intent = new Intent(this, TelaExplicaQuiz7.class);
        startActivity(intent);
    }
    public void IrPaginaPerfil(View view){
        btnUsuario = findViewById(R.id.btnTrueUsuario);

            Intent intent = new Intent(this, TelaContrucao8.class);
        startActivity(intent);
    }


}