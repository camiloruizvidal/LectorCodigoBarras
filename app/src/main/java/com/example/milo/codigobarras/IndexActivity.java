package com.example.milo.codigobarras;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

public class IndexActivity extends AppCompatActivity {
    private MediaPlayer reproductor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_index);
        reproductor=MediaPlayer.create(this, R.raw.intro);
        reproductor.setLooping(false);
        reproductor.start();
        esperar();
    }

    private void esperar() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // acciones que se ejecutan tras los milisegundos
                Iniciar();
            }
        }, 1500);
    }

    public void Iniciar() {
        Intent i = new Intent(this, tienda_actual.class);
        startActivity(i);
        finish();
    }
}
