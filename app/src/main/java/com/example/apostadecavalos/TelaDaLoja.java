package com.example.apostadecavalos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TelaDaLoja extends AppCompatActivity {
    private TextView txtUpgrade1, txtPontos;
    private Button btUpgrade1;

    DBHelper db;
    AlertDialog.Builder builder;
    int melhoriaAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_da_loja);
        db = new DBHelper(this);
        builder = new AlertDialog.Builder(this);

        txtUpgrade1 = findViewById(R.id.txtUpgrade1);
        btUpgrade1 = findViewById(R.id.btUpgrade1);
        txtPontos = findViewById(R.id.txtPontos);

        checarMelhoria();
        setarPontos();

        btUpgrade1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int valor = compra1();
                if (db.getPontos() >= valor){
                    builder.setTitle("Confirmação").setMessage("Você tem certeza?").setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            db.setUpgrade1(db.getUpgrade1()+1);
                            db.setPontos(db.getPontos()-valor);
                            checarMelhoria();
                            setarPontos();
                        }
                    }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    }).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.menu_jogo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btSair:
                Intent i = new Intent(TelaDaLoja.this, MainActivity.class);
                startActivity(i);
                return true;
            case R.id.btAjuda:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void checarMelhoria(){
        melhoriaAtual = db.getUpgrade1();

        switch (melhoriaAtual){
            case 1:
                txtUpgrade1.setText("      9 > 8\n15 pontos");
                break;
            case 2:
                txtUpgrade1.setText("      8 > 7\n20 pontos");
                break;
            case 3:
                txtUpgrade1.setText("      7 > 6\n25 pontos");
                break;
            case 4:
                txtUpgrade1.setText("      6 > 5\n30 pontos");
                break;
            case 5:
                txtUpgrade1.setText("Máximo");
                btUpgrade1.setEnabled(false);
                break;
        }
    }

    public void setarPontos(){
        txtPontos.setText("Pontos: "+db.getPontos());
    }

    public int compra1(){
        int x = 0;

        switch (melhoriaAtual){
            case 1:
                x = 15;
                break;
            case 2:
                x = 20;
                break;
            case 3:
                x = 25;
                break;
            case 4:
                x = 30;
                break;
            default:
                x= 10;
        }
        return x;
    }
}