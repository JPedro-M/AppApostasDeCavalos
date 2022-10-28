package com.example.apostadecavalos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class TelaDeJogo extends AppCompatActivity {
    private ImageView iv1, iv2, iv3, iv4, iv5, iv6, iv7, iv8, iv9, iv10;
    private TextView tvN1, tvN2, tvN3, tvN4, tvN5, tvN6, tvN7, tvN8, tvN9, tvN10, tvC1, tvC2, tvC3, tvC4, tvC5, tvC6, tvC7, tvC8, tvC9, tvC10;
    private RatingBar rb1, rb2, rb3, rb4, rb5, rb6, rb7, rb8, rb9, rb10;
    private Button bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9, bt10;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;


    ArrayList<Cavalos> cavalo;
    Random r = new Random();
    int cavaloSelc = 0;
    int[] ordem;

    int cod = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_de_jogo);
        iniciarComponentes();
        cavalo = new ArrayList<Cavalos>();

        if (cavalo.isEmpty()) {
            criarCavalos();
        }
        startGame();


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
                Intent i = new Intent(TelaDeJogo.this, MainActivity.class);
                startActivity(i);
                return true;
            case R.id.btAjuda:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onStart(){
        super.onStart();
        criarCavalos();
    }

    private void iniciarComponentes() {
        iv1 = findViewById(R.id.ivCavalo1);
        iv2 = findViewById(R.id.ivCavalo2);
        iv3 = findViewById(R.id.ivCavalo3);
        iv4 = findViewById(R.id.ivCavalo4);
        iv5 = findViewById(R.id.ivCavalo5);
        iv6 = findViewById(R.id.ivCavalo6);
        iv7 = findViewById(R.id.ivCavalo7);
        iv8 = findViewById(R.id.ivCavalo8);
        iv9 = findViewById(R.id.ivCavalo9);
        iv10 = findViewById(R.id.ivCavalo10);
        tvN1 = findViewById(R.id.tvNome1);
        tvN2 = findViewById(R.id.tvNome2);
        tvN3 = findViewById(R.id.tvNome3);
        tvN4 = findViewById(R.id.tvNome4);
        tvN5 = findViewById(R.id.tvNome5);
        tvN6 = findViewById(R.id.tvNome6);
        tvN7 = findViewById(R.id.tvNome7);
        tvN8 = findViewById(R.id.tvNome8);
        tvN9 = findViewById(R.id.tvNome9);
        tvN10 = findViewById(R.id.tvNome10);
        tvC1 = findViewById(R.id.tvCavalo1);
        tvC2 = findViewById(R.id.tvCavalo2);
        tvC3 = findViewById(R.id.tvCavalo3);
        tvC4 = findViewById(R.id.tvCavalo4);
        tvC5 = findViewById(R.id.tvCavalo5);
        tvC6 = findViewById(R.id.tvCavalo6);
        tvC7 = findViewById(R.id.tvCavalo7);
        tvC8 = findViewById(R.id.tvCavalo8);
        tvC9 = findViewById(R.id.tvCavalo9);
        tvC10 = findViewById(R.id.tvCavalo10);
        rb1 = findViewById(R.id.rbCavalo1);
        rb2 = findViewById(R.id.rbCavalo2);
        rb3 = findViewById(R.id.rbCavalo3);
        rb4 = findViewById(R.id.rbCavalo4);
        rb5 = findViewById(R.id.rbCavalo5);
        rb6 = findViewById(R.id.rbCavalo6);
        rb7 = findViewById(R.id.rbCavalo7);
        rb8 = findViewById(R.id.rbCavalo8);
        rb9 = findViewById(R.id.rbCavalo9);
        rb10 = findViewById(R.id.rbCavalo10);
        bt1 = findViewById(R.id.btCavalo1);
        bt2 = findViewById(R.id.btCavalo2);
        bt3 = findViewById(R.id.btCavalo3);
        bt4 = findViewById(R.id.btCavalo4);
        bt5 = findViewById(R.id.btCavalo5);
        bt6 = findViewById(R.id.btCavalo6);
        bt7 = findViewById(R.id.btCavalo7);
        bt8 = findViewById(R.id.btCavalo8);
        bt9 = findViewById(R.id.btCavalo9);
        bt10 = findViewById(R.id.btCavalo10);

    }

    public ArrayList<Cavalos> getCavalo() {
        return cavalo;
    }

    public void addCavalo(int qual, int desc, String nome, int url) {
        getCavalo().add(new Cavalos(cod++, qual, desc, nome, url));
    }

    public void criarCavalos() {
        addCavalo(0, getDiaDesc(), "Pé de Pano", R.drawable.background_cavalos);
        addCavalo(0, getDiaDesc(), "Old and Rusty", R.drawable.background_cavalos);
        addCavalo(0, getDiaDesc(), "Star Platinum", R.drawable.background_cavalos);
        addCavalo(0, getDiaDesc(), "Scary Monster", R.drawable.background_cavalos);
        addCavalo(0, getDiaDesc(), "Bites the Dust", R.drawable.background_cavalos);
        addCavalo(0, getDiaDesc(), "Slow Dancer", R.drawable.background_cavalos);
        addCavalo(0, getDiaDesc(), "Sleipnir", R.drawable.background_cavalos);
        addCavalo(0, getDiaDesc(), "Cooper Road", R.drawable.background_cavalos);
        addCavalo(0, getDiaDesc(), "Pegasus", R.drawable.background_cavalos);
        addCavalo(0, getDiaDesc(), "Maximus", R.drawable.background_cavalos);
        addCavalo(0, getDiaDesc(), "Thunder Horseshoe", R.drawable.background_cavalos);
        addCavalo(0, getDiaDesc(), "Lightening Strikes", R.drawable.background_cavalos);
        addCavalo(0, getDiaDesc(), "Killer Queen", R.drawable.background_cavalos);
        addCavalo(0, getDiaDesc(), "King Crimson", R.drawable.background_cavalos);
        addCavalo(0, getDiaDesc(), "Sleep Silver", R.drawable.background_cavalos);
        addCavalo(0, getDiaDesc(), "Golden Experience", R.drawable.background_cavalos);
        addCavalo(0, getDiaDesc(), "Puro Sangue Réquiem", R.drawable.background_cavalos);
        addCavalo(0, getDiaDesc(), "Wonder of U", R.drawable.background_cavalos);
        addCavalo(0, getDiaDesc(), "Ruby Kars", R.drawable.background_cavalos);
        addCavalo(0, getDiaDesc(), "Zeppeli", R.drawable.background_cavalos);
    }

    public int[] sortOrd() {
        int[] num = new int[10];
        int find, c;

        for(int i = 0; i < num.length; i++)
        {
            find = r.nextInt(19) + 1;
            if ( i == 0 ) {
                num[i] = find;
            } else {
                c = 0;
                while (c < i)
                {
                    if (num[c] == find)
                    {
                        find = r.nextInt(19) + 1;
                        c = 0;
                    } else {
                        c++;
                    }
                }
                num[i] = find;
            }
        }

        return num;
    }

    public int getQual() {
        int n = r.nextInt(9) + 1;
        int qual = 0;
        switch (n) {
            case 1:
                qual = 10;
                break;
            case 2:
                qual = 20;
                break;
            case 3:
                qual = 30;
                break;
            case 4:
                qual = 40;
                break;
            case 5:
                qual = 50;
                break;
            case 6:
                qual = 60;
                break;
            case 7:
                qual = 70;
                break;
            case 8:
                qual = 80;
                break;
            case 9:
                qual = 90;
                break;
            case 10:
                qual = 100;
                break;
        }
        return n;
    }

    public void startGame() {

        ordem = sortOrd();

        for (int i = 0; i < ordem.length; i++) {
            setQualCav(ordem[i], getQual());
            setCavalos(i + 1, ordem[i]);

        }
    }

    public int getDiaDesc() {
        int n = r.nextInt(9) + 1;
        return n;
    }

    public void setCavalos(int id, int ord) {
        switch (id) {
            case 1:
                tvN1.setText(cavalo.get(ord).getNome());
                tvC1.setText("Descanso do cavalo: \n" + (cavalo.get(ord).getDescanso()) + " ");
                rb1.setProgress(cavalo.get(ord).getQualidade());
                break;
            case 2:
                tvN2.setText(cavalo.get(ord).getNome());
                tvC2.setText("Descanso do cavalo: \n" + (cavalo.get(ord).getDescanso()) + " ");
                rb2.setProgress(cavalo.get(ord).getQualidade());
                break;
            case 3:
                tvN3.setText(cavalo.get(ord).getNome());
                tvC3.setText("Descanso do cavalo: \n" + (cavalo.get(ord).getDescanso()) + " ");
                rb3.setProgress(cavalo.get(ord).getQualidade());
                break;
            case 4:
                tvN4.setText(cavalo.get(ord).getNome());
                tvC4.setText("Descanso do cavalo: \n" + (cavalo.get(ord).getDescanso()) + " ");
                rb4.setProgress(cavalo.get(ord).getQualidade());
                break;
            case 5:
                tvN5.setText(cavalo.get(ord).getNome());
                tvC5.setText("Descanso do cavalo: \n" + (cavalo.get(ord).getDescanso()) + " ");
                rb5.setProgress(cavalo.get(ord).getQualidade());
                break;
            case 6:
                tvN6.setText(cavalo.get(ord).getNome());
                tvC6.setText("Descanso do cavalo: \n" + (cavalo.get(ord).getDescanso()) + " ");
                rb6.setProgress(cavalo.get(ord).getQualidade());
                break;
            case 7:
                tvN7.setText(cavalo.get(ord).getNome());
                tvC7.setText("Descanso do cavalo: \n" + (cavalo.get(ord).getDescanso()) + " ");
                rb7.setProgress(cavalo.get(ord).getQualidade());
                break;
            case 8:
                tvN8.setText(cavalo.get(ord).getNome());
                tvC8.setText("Descanso do cavalo: \n" + (cavalo.get(ord).getDescanso()) + " ");
                rb8.setProgress(cavalo.get(ord).getQualidade());
                break;
            case 9:
                tvN9.setText(cavalo.get(ord).getNome());
                tvC9.setText("Descanso do cavalo: \n" + (cavalo.get(ord).getDescanso()) + " ");
                rb9.setProgress(cavalo.get(ord).getQualidade());
                break;
            case 10:
                tvN10.setText(cavalo.get(ord).getNome());
                tvC10.setText("Descanso do cavalo: \n" + (cavalo.get(ord).getDescanso()) + " ");
                rb10.setProgress(cavalo.get(ord).getQualidade());
                break;
        }
    }

    public void setQualCav(int id, int quali){
        cavalo.get(id).setQualidade(quali);
    }

    public void criarDialog(){
        dialogBuilder = new AlertDialog.Builder(this);
        final View contactPopup = getLayoutInflater().inflate(R.layout.activity_popup, null);
    }

    public void botaoAposta(){
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cavaloSelc = ordem[0];

            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cavaloSelc = ordem[1];
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cavaloSelc = ordem[2];
            }
        });
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cavaloSelc = ordem[3];
            }
        });
        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cavaloSelc = ordem[4];
            }
        });
        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cavaloSelc = ordem[5];
            }
        });
        bt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cavaloSelc = ordem[6];
            }
        });
        bt8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cavaloSelc = ordem[7];
            }
        });
        bt9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cavaloSelc = ordem[8];
            }
        });
        bt10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cavaloSelc = ordem[9];
            }
        });
    }
}