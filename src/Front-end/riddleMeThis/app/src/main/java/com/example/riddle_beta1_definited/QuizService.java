package com.example.riddle_beta1_definited;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface QuizService {

    @GET("ObterQuizAleatorio") // Endpoint da API
    Call<Quiz> obterQuizAleatorio(@Query("dificuldade") String dificuldade);
}
