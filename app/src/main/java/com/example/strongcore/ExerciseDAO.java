package com.example.strongcore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ExerciseDAO extends DBCommon {
    private static final String TABELA = "exercise";
    private ArrayList<Exercise> exercises;
    public static TrainingDAO trainingDAO;

    ExerciseDAO(Context context) {
        super(context);
        exerciseArrayInit();
        if (!tableExists(getReadableDatabase(), TABELA)) {
            onCreate(getWritableDatabase());
        } else if (tableIsEmpty(getReadableDatabase(), TABELA)) {
            initialInsert();
        }
    }

    private void exerciseArrayInit() {
        exercises = new ArrayList<>();
        exercises.add(new Exercise("Prancha Ventral"));
        exercises.add(new Exercise("Ponte Dorsal"));
        exercises.add(new Exercise("Prancha Lateral"));
        exercises.add(new Exercise("Prancha Ventral com elevação da perna"));
        exercises.add(new Exercise("Prancha dorsal com elevação da perna"));
        exercises.add(new Exercise("Prancha Lateral com elevação do braço"));
        exercises.add(new Exercise("Prancha Superman"));
        exercises.add(new Exercise("Elevação dorsal paraquedista"));
        exercises.add(new Exercise("Prancha isométrica canoa"));
        exercises.add(new Exercise("Prancha ventral com instabilidade"));
        exercises.add(new Exercise("Ponte dorsal com elevação do calcanhar"));
        exercises.add(new Exercise("Prancha Lateral com instabilidade"));
        exercises.add(new Exercise("Prancha Ventral com Elevação do braço e perna"));
        exercises.add(new Exercise("Prancha Lateral com elevação do braço e perna"));
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABELA + " ("
                + "idExercise LONG PRIMARY KEY,"
                + "exerciseName TEXT NOT NULL)";
                /*+ "region INTEGER NOT NULL)"; IMPLEMENTAR POSTERIORMENTE CASO HAJA TEMPO
                * REGIÃO QUE O EXERCICIO TRABALHA PARA GERAÇÃO AUTOMATICA DE TREINOS*/
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABELA + ";";
        db.execSQL(sql);
        onCreate(db);
    }
    private void initialInsert() {
        ContentValues cv = new ContentValues();
        for (int i = 0; i < exercises.size(); i++) {
            cv.put("exerciseName", exercises.get(i).getExercise());
            getWritableDatabase().insert(TABELA, null, cv);
            cv.clear();
        }
    }

    public void insert(Exercise exercise) {
        ContentValues cv = new ContentValues();
        cv.put("exerciseName", exercise.getExercise());
        getWritableDatabase().insert(TABELA, null, cv);
    }

    public List<Exercise> getList() {
        List<Exercise> exercises = new ArrayList<>();
        String sql = "SELECT * FROM " + TABELA + ";";
        Cursor c = getReadableDatabase().rawQuery(sql, null);
        Long idExercise;
        String exerciseName;
        while (c.moveToNext()) {
            idExercise = c.getLong(c.getColumnIndex("idExercise"));
            exerciseName = c.getString(c.getColumnIndex("exerciseName"));
            Exercise exercise = new Exercise(idExercise, exerciseName);
            exercises.add(exercise);
        }
        c.close();
        return exercises;
    }

    public void delete(Exercise exercise) {
        String[] args = {Long.toString(exercise.getIdExercise())};
        getWritableDatabase().delete(TABELA, "idExercise=?", args);
    }

    public void update(Exercise exercise) {
        ContentValues cv = new ContentValues();
        cv.put("exerciseName", exercise.getExercise());
        String[] args = {Long.toString(exercise.getIdExercise())};
        getWritableDatabase().update(TABELA, cv, "idExercise=?", args);
    }
}