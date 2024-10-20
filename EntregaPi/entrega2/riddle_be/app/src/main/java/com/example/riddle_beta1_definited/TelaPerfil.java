package com.example.riddle_beta1_definited;


import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class TelaPerfil extends AppCompatActivity {

    private TextView nomeUsuario, instituicaoEnsino;
    private ExpandableListView historicoTentativas;
    private List<String> listGroup;
    private HashMap<String, List<String>> listItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_pagina_perfil);
//
//        nomeUsuario = findViewById(R.id.nomeUsuario);
//        instituicaoEnsino = findViewById(R.id.instituicaoEnsino);
//        historicoTentativas = findViewById(R.id.historicoTentativas);
//
//        // Dados fictícios do usuário
//        nomeUsuario.setText("João Silva");
//        instituicaoEnsino.setText("Instituto de Tecnologia");
//
//        // Configuração do histórico de tentativas
//        listGroup = new ArrayList<>();
//        listItem = new HashMap<>();
//        initHistoricoDados();
    }

    private void initHistoricoDados() {
        listGroup.add("Últimas 10 Tentativas");

        List<String> tentativas = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            tentativas.add("Tentativa " + i + ": Pontuação - " + (int)(Math.random() * 100) + "/100");
        }

        listItem.put(listGroup.get(0), tentativas);

    }
}
