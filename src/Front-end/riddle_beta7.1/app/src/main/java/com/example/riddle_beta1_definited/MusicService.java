package com.example.riddle_beta1_definited;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class MusicService extends Service {

    private MediaPlayer mediaPlayer;

    @Override
    public void onCreate() {
        super.onCreate();

        // Carregar o arquivo de música da pasta raw
        mediaPlayer = MediaPlayer.create(this, R.raw.music_background_tetris);
        mediaPlayer.setLooping(true); // Repetir a música em loop
    }

    // Iniciar a música
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
            mediaPlayer.start();
            Log.d("MusicService", "Música iniciada.");
        }
        return START_STICKY; // Mantém o serviço rodando até ser explicitamente parado
    }

    // Parar a música quando o serviço for destruído
    @Override
    public void onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            Log.d("MusicService", "Música parada e recursos liberados.");
        }
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null; // Não precisa de binding para esse serviço
    }
}
