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
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.slider.Slider;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class TelaDeJogo extends AppCompatActivity {
    private ImageView iv1, iv2, iv3, iv4, iv5, iv6, iv7, iv8, iv9, iv10;
    private TextView txtDinheiroAtual, tvN1, tvN2, tvN3, tvN4, tvN5, tvN6, tvN7, tvN8, tvN9, tvN10, tvC1, tvC2, tvC3, tvC4, tvC5, tvC6, tvC7, tvC8, tvC9, tvC10;
    private RatingBar rb1, rb2, rb3, rb4, rb5, rb6, rb7, rb8, rb9, rb10;
    private Button btAposta, btCancelar, bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9, bt10;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private EditText etAposta;
    private Slider slAposta;
    private RelativeLayout rvCavalo1, rvCavalo2, rvCavalo3, rvCavalo4, rvCavalo5, rvCavalo6, rvCavalo7, rvCavalo8, rvCavalo9, rvCavalo10;


    ArrayList<Cavalos> cavalo;
    DecimalFormat df = new DecimalFormat("0.00");
    Random r = new Random();
    DBHelper db;
    int cavaloSelc = 0;
    int[] ordem;
    int cavalos, cavaloGanhador;
    int cod = 0;
    double dinheiroAtual;
    double dinheiroGanho = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_de_jogo);
        iniciarComponentes();
        cavalo = new ArrayList<Cavalos>();
        db = new DBHelper(this);
        System.out.println(db.getUpgrade1());
        if (cavalo.isEmpty()) {
            criarCavalos();
        }
        checarUpgrade1();
        comecarNovoJogo();
        botaoAposta();


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

    public void onStart() {
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
        rvCavalo1 = findViewById(R.id.rlCavalo1);
        rvCavalo2 = findViewById(R.id.rlCavalo2);
        rvCavalo3 = findViewById(R.id.rlCavalo3);
        rvCavalo4 = findViewById(R.id.rlCavalo4);
        rvCavalo5 = findViewById(R.id.rlCavalo5);
        rvCavalo6 = findViewById(R.id.rlCavalo6);
        rvCavalo7 = findViewById(R.id.rlCavalo7);
        rvCavalo8 = findViewById(R.id.rlCavalo8);
        rvCavalo9 = findViewById(R.id.rlCavalo9);
        rvCavalo10 = findViewById(R.id.rlCavalo10);
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
        int[] num = new int[cavalos];
        int find, c;

        for (int i = 0; i < num.length; i++) {
            find = r.nextInt(19) + 1;
            if (i == 0) {
                num[i] = find;
            } else {
                c = 0;
                while (c < i) {
                    if (num[c] == find) {
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

    public void comecarNovoJogo() {

        ordem = sortOrd();
        dinheiroAtual = 500;
        for (int i = 0; i < ordem.length; i++) {
            setQualCav(ordem[i], getQual());
            setCavalos(i + 1, ordem[i]);

        }
    }

    public void comecarJogo(){
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

    public void setQualCav(int id, int quali) {
        cavalo.get(id).setQualidade(quali);
    }

    public void criarDialog() {
        dialogBuilder = new AlertDialog.Builder(this);
        final View contactPopup = getLayoutInflater().inflate(R.layout.activity_popup, null);
        final View popupGanhou = getLayoutInflater().inflate(R.layout.activity_ganhou, null);
        final View popupPerdeu = getLayoutInflater().inflate(R.layout.activity_perdeu, null);

        dialogBuilder.setView(contactPopup);
        dialog = dialogBuilder.create();
        dialog.show();

        txtDinheiroAtual = contactPopup.findViewById(R.id.txtDinheiroAtual);
        etAposta = contactPopup.findViewById(R.id.etAposta);
        slAposta = contactPopup.findViewById(R.id.sliderAposta);
        btAposta = contactPopup.findViewById(R.id.btApostar);
        btCancelar = contactPopup.findViewById(R.id.btCancelar);

        etAposta.addTextChangedListener(tw);

        txtDinheiroAtual.setText("Dinheiro atual: " + dinheiroAtual);
        slAposta.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                double x = dinheiroAtual * slAposta.getValue();
                etAposta.setText(df.format(x));
            }
        });

        btAposta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Double.parseDouble(etAposta.getText().toString().replace(",",".")) != 0){
                    double aposta = Double.parseDouble(etAposta.getText().toString().replace(",","."));
                    gerarVencedor();
                    if (cavaloSelc == cavaloGanhador){
                        dialog.dismiss();
                        dialogBuilder.setView(popupGanhou);
                        dialog = dialogBuilder.create();
                        dialog.show();
                        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                dialog.dismiss();
                            }
                        },3000);
                        dinheiroAtual = Double.parseDouble(df.format(dinheiroAtual + aposta).replace(",","."));

                        dinheiroGanho += aposta;
                    }else{
                        dialog.dismiss();
                        dialogBuilder.setView(popupPerdeu);
                        dialog = dialogBuilder.create();
                        dialog.show();
                        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                dialog.dismiss();
                            }
                        },3000);
                        dinheiroAtual = Double.parseDouble(df.format(dinheiroAtual - aposta).replace(",","."));
                    }
                    if (dinheiroAtual <= 0){
                        encerrarJogo();
                    }else{
                        comecarJogo();
                    }
                }else{
                    Toast.makeText(TelaDeJogo.this, "Aposte algo, por favor", Toast.LENGTH_LONG).show();
                }
            }
        });
        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    public void botaoAposta() {
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cavaloSelc = 1;
                criarDialog();
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cavaloSelc = 2;
                criarDialog();
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cavaloSelc = 3;
                criarDialog();
            }
        });
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cavaloSelc =4;
                criarDialog();
            }
        });
        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cavaloSelc = 5;
                criarDialog();
            }
        });
        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cavaloSelc = 6;
                criarDialog();
            }
        });
        bt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cavaloSelc = 7;
                criarDialog();
            }
        });
        bt8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cavaloSelc = 8;
                criarDialog();
            }
        });
        bt9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cavaloSelc = 9;
                criarDialog();
            }
        });
        bt10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cavaloSelc = 10;
                criarDialog();
            }
        });
    }

    public void checarUpgrade1() {
        cavalos = 10 - db.getUpgrade1();

        switch (cavalos) {
            case 5:
                rvCavalo6.setVisibility(View.GONE);
                rvCavalo7.setVisibility(View.GONE);
                rvCavalo8.setVisibility(View.GONE);
                rvCavalo9.setVisibility(View.GONE);
                rvCavalo10.setVisibility(View.GONE);
                break;
            case 6:
                rvCavalo7.setVisibility(View.GONE);
                rvCavalo8.setVisibility(View.GONE);
                rvCavalo9.setVisibility(View.GONE);
                rvCavalo10.setVisibility(View.GONE);
                break;
            case 7:
                rvCavalo8.setVisibility(View.GONE);
                rvCavalo9.setVisibility(View.GONE);
                rvCavalo10.setVisibility(View.GONE);
                break;
            case 8:
                rvCavalo9.setVisibility(View.GONE);
                rvCavalo10.setVisibility(View.GONE);
                break;
            case 9:
                rvCavalo10.setVisibility(View.GONE);
                break;
        }
    }

    public void gerarVencedor() {
        int ganhador, total, cavalo1, cavalo2, cavalo3, cavalo4, cavalo5, cavalo6, cavalo7, cavalo8, cavalo9, cavalo10;

        switch (cavalos) {
            case 5:
                cavalo1 = cavalo.get(ordem[0]).getDescanso() * cavalo.get(ordem[0]).getQualidade();
                cavalo2 = cavalo.get(ordem[1]).getDescanso() * cavalo.get(ordem[1]).getQualidade();
                cavalo3 = cavalo.get(ordem[2]).getDescanso() * cavalo.get(ordem[2]).getQualidade();
                cavalo4 = cavalo.get(ordem[3]).getDescanso() * cavalo.get(ordem[3]).getQualidade();
                cavalo5 = cavalo.get(ordem[4]).getDescanso() * cavalo.get(ordem[4]).getQualidade();
                total = cavalo1 + cavalo2 + cavalo3 + cavalo4 + cavalo5;
                break;
            case 6:
                cavalo1 = cavalo.get(ordem[0]).getDescanso() * cavalo.get(ordem[0]).getQualidade();
                cavalo2 = cavalo.get(ordem[1]).getDescanso() * cavalo.get(ordem[1]).getQualidade();
                cavalo3 = cavalo.get(ordem[2]).getDescanso() * cavalo.get(ordem[2]).getQualidade();
                cavalo4 = cavalo.get(ordem[3]).getDescanso() * cavalo.get(ordem[3]).getQualidade();
                cavalo5 = cavalo.get(ordem[4]).getDescanso() * cavalo.get(ordem[4]).getQualidade();
                cavalo6 = cavalo.get(ordem[5]).getDescanso() * cavalo.get(ordem[5]).getQualidade();
                total = cavalo1 + cavalo2 + cavalo3 + cavalo4 + cavalo5 + cavalo6;
                break;
            case 7:
                cavalo1 = cavalo.get(ordem[0]).getDescanso() * cavalo.get(ordem[0]).getQualidade();
                cavalo2 = cavalo.get(ordem[1]).getDescanso() * cavalo.get(ordem[1]).getQualidade();
                cavalo3 = cavalo.get(ordem[2]).getDescanso() * cavalo.get(ordem[2]).getQualidade();
                cavalo4 = cavalo.get(ordem[3]).getDescanso() * cavalo.get(ordem[3]).getQualidade();
                cavalo5 = cavalo.get(ordem[4]).getDescanso() * cavalo.get(ordem[4]).getQualidade();
                cavalo6 = cavalo.get(ordem[5]).getDescanso() * cavalo.get(ordem[5]).getQualidade();
                cavalo7 = cavalo.get(ordem[6]).getDescanso() * cavalo.get(ordem[6]).getQualidade();
                total = cavalo1 + cavalo2 + cavalo3 + cavalo4 + cavalo5 + cavalo6 + cavalo7;
                break;
            case 8:
                cavalo1 = cavalo.get(ordem[0]).getDescanso() * cavalo.get(ordem[0]).getQualidade();
                cavalo2 = cavalo.get(ordem[1]).getDescanso() * cavalo.get(ordem[1]).getQualidade();
                cavalo3 = cavalo.get(ordem[2]).getDescanso() * cavalo.get(ordem[2]).getQualidade();
                cavalo4 = cavalo.get(ordem[3]).getDescanso() * cavalo.get(ordem[3]).getQualidade();
                cavalo5 = cavalo.get(ordem[4]).getDescanso() * cavalo.get(ordem[4]).getQualidade();
                cavalo6 = cavalo.get(ordem[5]).getDescanso() * cavalo.get(ordem[5]).getQualidade();
                cavalo7 = cavalo.get(ordem[6]).getDescanso() * cavalo.get(ordem[6]).getQualidade();
                cavalo8 = cavalo.get(ordem[7]).getDescanso() * cavalo.get(ordem[7]).getQualidade();
                total = cavalo1 + cavalo2 + cavalo3 + cavalo4 + cavalo5 + cavalo6 + cavalo7 + cavalo8;
                break;
            case 9:
                cavalo1 = cavalo.get(ordem[0]).getDescanso() * cavalo.get(ordem[0]).getQualidade();
                cavalo2 = cavalo.get(ordem[1]).getDescanso() * cavalo.get(ordem[1]).getQualidade();
                cavalo3 = cavalo.get(ordem[2]).getDescanso() * cavalo.get(ordem[2]).getQualidade();
                cavalo4 = cavalo.get(ordem[3]).getDescanso() * cavalo.get(ordem[3]).getQualidade();
                cavalo5 = cavalo.get(ordem[4]).getDescanso() * cavalo.get(ordem[4]).getQualidade();
                cavalo6 = cavalo.get(ordem[5]).getDescanso() * cavalo.get(ordem[5]).getQualidade();
                cavalo7 = cavalo.get(ordem[6]).getDescanso() * cavalo.get(ordem[6]).getQualidade();
                cavalo8 = cavalo.get(ordem[7]).getDescanso() * cavalo.get(ordem[7]).getQualidade();
                cavalo9 = cavalo.get(ordem[8]).getDescanso() * cavalo.get(ordem[8]).getQualidade();
                total = cavalo1 + cavalo2 + cavalo3 + cavalo4 + cavalo5 + cavalo6 + cavalo7 + cavalo8 + cavalo9;
                break;
            default:
                cavalo1 = cavalo.get(ordem[0]).getDescanso() * cavalo.get(ordem[0]).getQualidade();
                cavalo2 = cavalo.get(ordem[1]).getDescanso() * cavalo.get(ordem[1]).getQualidade();
                cavalo3 = cavalo.get(ordem[2]).getDescanso() * cavalo.get(ordem[2]).getQualidade();
                cavalo4 = cavalo.get(ordem[3]).getDescanso() * cavalo.get(ordem[3]).getQualidade();
                cavalo5 = cavalo.get(ordem[4]).getDescanso() * cavalo.get(ordem[4]).getQualidade();
                cavalo6 = cavalo.get(ordem[5]).getDescanso() * cavalo.get(ordem[5]).getQualidade();
                cavalo7 = cavalo.get(ordem[6]).getDescanso() * cavalo.get(ordem[6]).getQualidade();
                cavalo8 = cavalo.get(ordem[7]).getDescanso() * cavalo.get(ordem[7]).getQualidade();
                cavalo9 = cavalo.get(ordem[8]).getDescanso() * cavalo.get(ordem[8]).getQualidade();
                cavalo10 = cavalo.get(ordem[9]).getDescanso() * cavalo.get(ordem[9]).getQualidade();
                total = cavalo1 + cavalo2 + cavalo3 + cavalo4 + cavalo5 + cavalo6 + cavalo7 + cavalo8 + cavalo9 + cavalo10;
                break;
        }

        cavalo6 = 0;
        cavalo7 = 0;
        cavalo8 = 0;
        cavalo9 = 0;
        cavalo2 += cavalo1;
        cavalo3 += cavalo2;
        cavalo4 += cavalo3;
        cavalo5 += cavalo4;
        cavalo6 += cavalo5;
        cavalo7 += cavalo6;
        cavalo8 += cavalo7;
        cavalo9 += cavalo8;

        ganhador = r.nextInt(total - 1) + 1;

        if (ganhador <= cavalo1) {
            cavaloGanhador = 1;
        } else if (ganhador <= cavalo2) {
            cavaloGanhador = 2;
        } else if (ganhador <= cavalo3) {
            cavaloGanhador = 3;
        } else if (ganhador <= cavalo4) {
            cavaloGanhador = 4;
        } else if (ganhador <= cavalo5) {
            cavaloGanhador = 5;
        } else if (ganhador <= cavalo6) {
            cavaloGanhador = 6;
        } else if (ganhador <= cavalo7) {
            cavaloGanhador = 7;
        } else if (ganhador <= cavalo8) {
            cavaloGanhador = 8;
        } else if (ganhador <= cavalo9) {
            cavaloGanhador = 9;
        } else {
            cavaloGanhador = 10;
        }

    }

    TextWatcher tw = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (!etAposta.getText().toString().equals("")){
                if (Double.parseDouble(etAposta.getText().toString().replace(",", ".")) > dinheiroAtual) {
                    etAposta.setText("" + dinheiroAtual);
                }
            }

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    public void encerrarJogo(){
        dialogBuilder = new AlertDialog.Builder(this);
        final View popupFaliu = getLayoutInflater().inflate(R.layout.activity_faliu, null);
        DecimalFormat dfP = new DecimalFormat("0");
        dialog.dismiss();
        dialogBuilder.setView(popupFaliu);
        dialog = dialogBuilder.create();
        dialog.show();
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
                Intent i = new Intent(TelaDeJogo.this, MainActivity.class);
                startActivity(i);
            }
        },3000);
        int pontos = Integer.parseInt(dfP.format(dinheiroGanho/10));
        db.setPontos(db.getPontos() + pontos);
    }
}