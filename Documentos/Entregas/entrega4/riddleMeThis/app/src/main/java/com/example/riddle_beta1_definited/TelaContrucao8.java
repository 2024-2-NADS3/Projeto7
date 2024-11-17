package com.example.riddle_beta1_definited;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class TelaContrucao8 extends AppCompatActivity {

    private ImageView gif12, cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contrucao8);

        gif12 = findViewById(R.id.gif12);
        Glide.with(this).asGif().load(R.drawable.construction).into(gif12);
    }

    public void mudarTelaContrs(View view){
        Intent intent = new Intent(this, TelaOpcoes3.class);
        startActivity(intent);
    }
}