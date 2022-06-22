package com.gedalias.gerenciafinancas.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.gedalias.gerenciafinancas.R;

public class MainActivity extends Activity {
    private final int DELAY = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler()
            .postDelayed(() -> {
                        startActivity(new Intent(MainActivity.this, HomeActivity.class));
                        finishAffinity();
                    },
            DELAY);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        startActivity(new Intent(MainActivity.this, HomeActivity.class));
        finishAffinity();
    }
}