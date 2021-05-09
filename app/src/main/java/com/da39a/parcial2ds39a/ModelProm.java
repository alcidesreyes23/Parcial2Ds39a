package com.da39a.parcial2ds39a;

public class ModelProm {
    String tipoCombustible;
    int km;
    String monto;

    public ModelProm(String tipoCombustible, int km, String monto) {
        this.tipoCombustible = tipoCombustible;
        this.km = km;
        this.monto = monto;
    }

    public String getTipoCombustible() {
        return tipoCombustible;
    }

    public void setTipoCombustible(String tipoCombustible) {
        this.tipoCombustible = tipoCombustible;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }
}
