package com.da39a.parcial2ds39a;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ModeloForm {
    //Agregar los campos de la tabla
    @PrimaryKey(autoGenerate = true)
    int numFactura;
    @ColumnInfo(name = "fechaCompra")
    String fechaCompra;
    @ColumnInfo(name = "tipoCombustible")
    String tipoCombustible;
    @ColumnInfo(name = "km")
    String km;
    @ColumnInfo(name = "monto")
    double monto;

    public ModeloForm(int numFactura, String fechaCompra, String tipoCombustible, String km, double monto) {
        this.numFactura = numFactura;
        this.fechaCompra = fechaCompra;
        this.tipoCombustible = tipoCombustible;
        this.km = km;
        this.monto = monto;
    }

    public int getNumFactura() {
        return numFactura;
    }

    public void setNumFactura(int numFactura) {
        this.numFactura = numFactura;
    }

    public String getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(String fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public String getTipoCombustible() {
        return tipoCombustible;
    }

    public void setTipoCombustible(String tipoCombustible) {
        this.tipoCombustible = tipoCombustible;
    }

    public String getKm() {
        return km;
    }

    public void setKm(String km) {
        this.km = km;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
}
