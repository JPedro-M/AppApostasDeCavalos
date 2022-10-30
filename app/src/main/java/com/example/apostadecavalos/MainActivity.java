package com.example.apostadecavalos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btNovoJogo, btLoja, btReset;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DBHelper(this);
        db.start();
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        btNovoJogo = findViewById(R.id.btNovoJogo);
        btLoja = findViewById(R.id.btLoja);
        btReset = findViewById(R.id.btReset);

        btNovoJogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, TelaDeJogo.class);
                startActivity(i);
            }
        });
        btLoja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, TelaDaLoja.class);
                startActivity(i);
            }
        });
        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.reset();
            }
        });
    }
}