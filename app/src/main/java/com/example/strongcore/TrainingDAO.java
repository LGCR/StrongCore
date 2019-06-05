package com.example.strongcore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TrainingDAO extends DBCommon {

    private static final String TABELA = "training";
    public static final int N_TRAININGS = 9;
    public static TrainingExercisesDAO trainingExercisesDAO;

    TrainingDAO(Context context) {
        super(context);
        if (!tableExists(getReadableDatabase(), TABELA)) {
            onCreate(getWritableDatabase());
        }else if (tableIsEmpty(getReadableDatabase(), TABELA)
                || noRegisterForUser(getReadableDatabase(), TABELA)) {
            initialInsert();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABELA + " ("
                + "idTraining LONG PRIMARY KEY,"
                + "idUser LONG NOT NULL,"
                + "week INTEGER NOT NULL,"
                + "dateCompleted DATE,"
                + "nTrainingsAvailable INTEGER NOT NULL,"
                + "nTrainingsCompleted INTEGER NOT NULL,"
                + "FOREIGN KEY(idUser) REFERENCES users(id))";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABELA + ";";
        db.execSQL(sql);
        onCreate(db);
    }

    private void initialInsert() {
        for (int i = 0; i < N_TRAININGS; i++) {
            ContentValues cv = new ContentValues();
            cv.put("idUser", MainActivity.user.getId());
            cv.put("week", Integer.toString(i+1));
            cv.put("nTrainingsAvailable", "3");
            cv.put("nTrainingsCompleted", "0");
            getWritableDatabase().insert(TABELA, null, cv);
        }
    }

    public void insert(Training training) {
        ContentValues cv = new ContentValues();
        cv.put("idUser", MainActivity.user.getId());
        cv.put("week", training.getWeek());
        cv.put("dateCompleted", training.getDateCompleted().toString());
        cv.put("nTrainingsAvailable", training.getnTrainingsAvailable());
        cv.put("nTrainingsCompleted", training.getnTrainingsCompleted());
        getWritableDatabase().insert(TABELA, null, cv);
    }

    public List<Training> getList() {
        List<Training> trainings = new ArrayList<>();
        String args[] = {Long.toString(MainActivity.user.getId())};
        String sql = "SELECT * FROM " + TABELA + " WHERE idUser=?;";
        Cursor c = getReadableDatabase().rawQuery(sql, args);
        Long idTraining, idUser;
        int week, nTrainingsAvailable, nTrainingsCompleted;
        Date dateCompleted;
        while (c.moveToNext()) {
            idTraining = c.getLong(c.getColumnIndex("idTraining"));
            idUser = c.getLong(c.getColumnIndex("idUser"));
            week = c.getInt(c.getColumnIndex("week"));
            dateCompleted = new Date(c.getLong(c.getColumnIndex("dateCompleted"))*1000);
            nTrainingsAvailable = c.getInt(c.getColumnIndex("nTrainingsAvailable"));
            nTrainingsCompleted = c.getInt(c.getColumnIndex("nTrainingsCompleted"));
            Training training = new Training(idTraining, idUser, week, dateCompleted, nTrainingsAvailable, nTrainingsCompleted);
            trainings.add(training);
        }
        c.close();
        return trainings;
    }

    public void delete(Training training) {
        String[] args = {Long.toString(training.getIdTraining())};
        getWritableDatabase().delete(TABELA, "idTraining=?", args);
    }

    public void update(Training training) {
        ContentValues cv = new ContentValues();
        cv.put("idUser", MainActivity.user.getId());
        cv.put("week", training.getWeek());
        cv.put("dateCompleted", training.getDateCompleted().toString());
        cv.put("nTrainingsAvailable", training.getnTrainingsAvailable());
        cv.put("nTrainingsCompleted", training.getnTrainingsCompleted());
        String[] args = {Long.toString(training.getIdTraining())};
        getWritableDatabase().update(TABELA, cv, "idTraining=?", args);
    }
}
