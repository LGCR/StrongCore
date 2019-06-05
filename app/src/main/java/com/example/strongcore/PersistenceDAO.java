package com.example.strongcore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class PersistenceDAO extends DBCommon {

    public static final String TABELA = "persistence";

    PersistenceDAO(Context context){
        super(context);
        if (!tableExists(getReadableDatabase(), TABELA)){
            onCreate(getWritableDatabase());
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABELA + " ("
                + "email TEXT PRIMARY KEY UNIQUE NOT NULL) ";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABELA +";";
        db.execSQL(sql);
        onCreate(db);
    }

    public void insert(Persistence user) {
        ContentValues cv = new ContentValues();
        cv.put("email", user.getEmail());
        getWritableDatabase().insert(TABELA, null, cv);
    }

    public List<Persistence> getList() {
        List<Persistence> users = new ArrayList<>();
        String sql = "SELECT * FROM " + TABELA + ";";
        Cursor c = getReadableDatabase().rawQuery(sql, null);
        String email;
        while (c.moveToNext()) {
            email = c.getString(c.getColumnIndex("email"));
            Persistence user = new Persistence(email);
            users.add(user);
        }
        c.close();
        return users;
    }

    public void delete(Persistence user) {
        String[] args = {user.getEmail()};
        getWritableDatabase().delete(TABELA, "email=?", args);
    }

    public void update(Persistence user) {
        ContentValues cv = new ContentValues();;
        cv.put("email", user.getEmail());
        String[] args = {user.getEmail()};
        getWritableDatabase().update(TABELA, cv, "email=?", args);
    }
}
