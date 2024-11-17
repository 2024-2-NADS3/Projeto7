package com.example.riddle_beta1_definited;

import com.google.gson.annotations.SerializedName;

public class Pergunta {
    @SerializedName("id_perguntas")
    private int id_perguntas;

    @SerializedName("perguntas") // Mapeia o campo JSON 'perguntas' para o campo 'pergunta' em Java
    private String pergunta;

    @SerializedName("respostas") // Mapeia o campo JSON 'respostas' para o campo 'resposta' em Java
    private boolean resposta;

    private String num_pergunta;

    // Getters e Setters
    public int getId_perguntas() {
        return id_perguntas;
    }

    public void setId_perguntas(int id_perguntas) {
        this.id_perguntas = id_perguntas;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public boolean isResposta() {
        return resposta;
    }

    public void setResposta(boolean resposta) {
        this.resposta = resposta;
    }

    public String getNum_pergunta() {
        return num_pergunta;
    }

    public void setNum_pergunta(String num_pergunta) {
        this.num_pergunta = num_pergunta;
    }
}
