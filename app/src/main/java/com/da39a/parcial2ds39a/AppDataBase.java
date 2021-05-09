package com.da39a.parcial2ds39a;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {ModeloForm.class}, version = 2)
public abstract class AppDataBase extends RoomDatabase {
    public abstract FormDAO formDAO();
}
