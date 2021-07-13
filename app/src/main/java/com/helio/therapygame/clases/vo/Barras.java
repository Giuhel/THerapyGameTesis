package com.helio.therapygame.clases.vo;

public class Barras {
    private int pbGeneral;
    private int pbAtencion;
    private int pbMemoria;
    private int pbRazonamiento;

    public Barras(int pbGeneral, int pbAtencion, int pbMemoria, int pbRazonamiento) {
        this.pbGeneral = pbGeneral;
        this.pbAtencion = pbAtencion;
        this.pbMemoria = pbMemoria;
        this.pbRazonamiento = pbRazonamiento;
    }

    public Barras() {
    }

    public int getPbGeneral() {
        return pbGeneral;
    }

    public void setPbGeneral(int pbGeneral) {
        this.pbGeneral = pbGeneral;
    }

    public int getPbAtencion() {
        return pbAtencion;
    }

    public void setPbAtencion(int pbAtencion) {
        this.pbAtencion = pbAtencion;
    }

    public int getPbMemoria() {
        return pbMemoria;
    }

    public void setPbMemoria(int pbMemoria) {
        this.pbMemoria = pbMemoria;
    }

    public int getPbRazonamiento() {
        return pbRazonamiento;
    }

    public void setPbRazonamiento(int pbRazonamiento) {
        this.pbRazonamiento = pbRazonamiento;
    }
}
