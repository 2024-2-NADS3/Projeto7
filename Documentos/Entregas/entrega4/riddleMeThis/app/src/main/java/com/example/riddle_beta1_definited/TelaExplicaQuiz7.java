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

public class TelaExplicaQuiz7 extends AppCompatActivity {

    private ImageView cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_explicaquiz7);



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void linkProYoutube(View view) {
        String url = "https://www.youtube.com/watch?v=HCB2Rxxj7zE";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url)); // Define a URL correta
        startActivity(intent);
    }

    public void SaibaMais(View view) {
        String url = "https://brasil.un.org/pt-br/sdgs";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url)); // Define a URL correta
        startActivity(intent);
    }

    public void VoltarTela2(View view){
        cancelButton = findViewById(R.id.cancelButton);
        Intent intent = new Intent(this, TelaOpcoes3.class);
        startActivity(intent);
    }
}