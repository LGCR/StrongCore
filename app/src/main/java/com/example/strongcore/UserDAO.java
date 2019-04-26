package com.example.strongcore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class UserDAO extends SQLiteOpenHelper {

    private static final String DATABASE = "strongcore";
    private static final int VERSAO = 5;
    private static final String TABELA = "users";

    public UserDAO(Context context) {
        super(context, DATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABELA + " ("
                + "id INTEGER PRIMARY KEY, "
                + "name TEXT NOT NULL, "
                + "email TEXT UNIQUE NOT NULL, "
                + "password TEXT "
                + ");";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABELA +";";
        db.execSQL(sql);
        onCreate(db);
    }

    public void insert(User user) {
        ContentValues cv = new ContentValues();
        cv.put("name", user.getName());
        cv.put("email", user.getEmail());
        cv.put("password", user.getPassword() );
        getWritableDatabase().insert(TABELA, null, cv);
    }

    public List<User> getList() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM " + TABELA + ";";
        Cursor c = getReadableDatabase().rawQuery(sql, null);
        String name, email, password;
        Long id;
        while (c.moveToNext()) {
            id = c.getLong(c.getColumnIndex("id"));
            name = c.getString(c.getColumnIndex("name"));
            email = c.getString(c.getColumnIndex("email"));
            password = c.getString(c.getColumnIndex("password"));
            User user = new User(id, name, email, password);
            users.add(user);
        }
        c.close();
        return users;
    }

    public User getUserByEmail(String mail){
        String sql = "SELECT * FROM " + TABELA + " WHERE email = '" + mail +  "';";
        Cursor c = getReadableDatabase().rawQuery(sql, null);
        String name, email, password;
        Long id;
        c.moveToNext();
        id = c.getLong(c.getColumnIndex("id"));
        name = c.getString(c.getColumnIndex("name"));
        email = c.getString(c.getColumnIndex("email"));
        password = c.getString(c.getColumnIndex("password"));
        c.close();
        return new User(id, name, email, password);
    }

    public void delete(User user) {
        String[] args = {user.getId().toString()};
        getWritableDatabase().delete(TABELA, "id=?", args);
    }

    public void update(User user) {
        ContentValues cv = new ContentValues();
        cv.put("name", user.getName());
        cv.put("email", user.getEmail());
        cv.put("password", user.getPassword());
        String[] args = {user.getId().toString()};
        getWritableDatabase().update(TABELA, cv, "id=?", args);
    }

    public boolean tableExists() {
        Cursor cursor = getReadableDatabase().rawQuery("SELECT COUNT(*) FROM sqlite_master WHERE type = ? AND name = ?", new String[] {"table", TABELA});
        if (!cursor.moveToFirst())
        {
            cursor.close();
            return false;
        }
        int count = cursor.getInt(0);
        cursor.close();
        return count > 0;
    }

}
