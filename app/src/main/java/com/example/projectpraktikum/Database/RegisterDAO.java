package com.example.projectpraktikum.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface RegisterDAO {
    @Insert
    Long insertData(Register register);

    @Query("Select * from daftar_akun where Username =:username")
    Register[] getDataUser(String username);

    @Query("Select * from daftar_akun where Username =:user and Password=:pass")
    Register[] getDataLogin(String user, String pass);

    @Update
    int updateData(Register item);

    @Delete
    void deleteData(Register item);
}
