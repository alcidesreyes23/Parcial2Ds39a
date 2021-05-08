package com.da39a.parcial2ds39a;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FormDAO {
    @Query("SELECT * FROM MODELOFORM")
    List<ModeloForm> getAll();

    @Insert
    Long insert(ModeloForm modeloForm);
}
