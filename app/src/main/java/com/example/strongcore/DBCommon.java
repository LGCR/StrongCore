package com.example.strongcore;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public abstract class DBCommon extends SQLiteOpenHelper {

    public static final String DATABASE = "strongcore";
    public static final int VERSAO = 1;

    public DBCommon(Context context) {
        super(context, DATABASE, null, VERSAO);
    }

    public boolean tableExists(SQLiteDatabase db, String tabela) {
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM sqlite_master WHERE type = ? AND name = ?", new String[]{"table", tabela});
        if (!cursor.moveToFirst()) {
            cursor.close();
            return false;
        }
        int count = cursor.getInt(0);
        cursor.close();
        return count > 0;
    }

    boolean tableIsEmpty(SQLiteDatabase db, String tabela){
        String count = "SELECT count(*) FROM " + tabela;
        Cursor mcursor = db.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);
        mcursor.close();
        return icount == 0;
    }
    boolean noRegisterForUser(SQLiteDatabase db, String tabela){
        String count = "SELECT count(*) FROM " + tabela + " WHERE idUser =?";
        String args[] = {MainActivity.user.getId().toString()};
        Cursor mcursor = db.rawQuery(count, args);
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);
        mcursor.close();
        return icount == 0;
    }
}
