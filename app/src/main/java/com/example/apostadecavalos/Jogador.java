package com.example.apostadecavalos;

public class Jogador {

    private int id, pontos;
    private double dinheiro;

    public Jogador(int id, int pontos, double dinheiro) {
        this.id = id;
        this.pontos = pontos;
        this.dinheiro = dinheiro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public double getDinheiro() {
        return dinheiro;
    }

    public void setDinheiro(double dinheiro) {
        this.dinheiro = dinheiro;
    }
}
