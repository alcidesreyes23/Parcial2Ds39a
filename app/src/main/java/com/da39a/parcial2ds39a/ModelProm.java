package com.da39a.parcial2ds39a;

public class ModelProm {
    String tipoCombustible, km, monto;

    public ModelProm(String tipoCombustible, String km, String monto) {
        this.tipoCombustible = tipoCombustible;
        this.km = km;
        this.monto = monto;
    }

    public String getTipoCombustible() {
        return tipoCombustible;
    }

    public String getKm() {
        return km;
    }

    public String getMonto() {
        return monto;
    }
}
