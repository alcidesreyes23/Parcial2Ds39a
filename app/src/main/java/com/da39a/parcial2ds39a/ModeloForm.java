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
    int km;
    @ColumnInfo(name = "monto")
    double monto;

    public ModeloForm(int numFactura, String fechaCompra, String tipoCombustible, int km, double monto) {
        this.numFactura = numFactura;
        this.fechaCompra = fechaCompra;
        this.tipoCombustible = tipoCombustible;
        this.km = km;
        this.monto = monto;
    }

    public int getNumFactura() {
        return numFactura;
    }

    public String getFechaCompra() {
        return fechaCompra;
    }

    public String getTipoCombustible() {
        return tipoCombustible;
    }

    public int getKm() {
        return km;
    }

    public double getMonto() {
        return monto;
    }
}
