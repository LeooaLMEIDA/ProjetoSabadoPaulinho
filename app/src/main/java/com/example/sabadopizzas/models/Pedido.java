package com.example.sabadopizzas.models;

import java.util.List;

public class Pedido {
    private String tamanho;
    private String sabor;
    private boolean borda;
    private boolean refrigerante;
    private List<String> sabores;

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getSabor() {
        return sabor;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
    }

    public boolean isBorda() {
        return borda;
    }

    public void setBorda(boolean borda) {
        this.borda = borda;
    }

    public boolean isRefrigerante() {
        return refrigerante;
    }

    public void setRefrigerante(boolean refrigerante) {
        this.refrigerante = refrigerante;
    }

    public List<String> getSabores() {
        return sabores;
    }

    public void setSabores(List<String> sabores) {
        this.sabores = sabores;
    }
}
