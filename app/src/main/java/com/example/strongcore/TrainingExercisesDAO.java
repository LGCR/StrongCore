package com.example.strongcore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class TrainingExercisesDAO extends DBCommon{

    private static final String TABELA = "trainingExercises";
    private ExerciseDAO exerciseDAO;
    private Context context;

    TrainingExercisesDAO(Context context){
        super(context);
        this.context = context;
        if (!tableExists(getReadableDatabase(), TABELA)) {
            onCreate(getWritableDatabase());
        } else if (tableIsEmpty(getReadableDatabase(), TABELA)) {
            initialInsert();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABELA + "("
                + "idTrainingExercise LONG PRIMARY KEY,"
                + "idTraining LONG NOT NULL,"
                + "idExercise LONG NOT NULL,"
                + "exerciseDuration INTEGER NOT NULL,"
                + "exerciseSets INTEGER NOT NULL,"
                + "rest INTEGER NOT NULL,"
                + "FOREIGN KEY(idTraining) REFERENCES training(idTraining),"
                + "FOREIGN KEY(idExercise) REFERENCES exercise(idExercise))";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABELA + ";";
        db.execSQL(sql);
        onCreate(db);
    }
    private void initialInsert() {
        ExerciseDAO exerciseDAO = new ExerciseDAO(context);
        List<Exercise> exercises = exerciseDAO.getList();
        int[] sets = {3,3,3,5,5,5,3,3,3,5,5,5,3,3,3,5,5,5,3,3,3,5,5,5,3,3},
                duration = {40,40,30,40,40,40,40,40,30,40,40,40,40,40,30,40,40,40,
                40,40,30,40,40,40,40,40},
            idE = {0,1,2,0,1,2,3,4,5,3,4,5,6,7,8,6,7,8,9,10,11,9,10,11,
            12,13};
        for (int i = 0; i < (exercises.size()*2)-2; i++) {
            ContentValues cv = new ContentValues();
            int idT = i;
            if (i != 0){
                idT /= 3;
            }
            cv.put("idTraining", Integer.toString(idT));
            cv.put("idExercise", Integer.toString(idE[i]));
            cv.put("exerciseDuration", Integer.toString(duration[i]));
            cv.put("exerciseSets", Integer.toString(sets[i]));
            cv.put("rest", "30");
            getWritableDatabase().insert(TABELA, null, cv);
        }
    }

    public void insert(TrainingExercise trainingExercise) {
        ContentValues cv = new ContentValues();
        cv.put("idTraining", trainingExercise.getIdTraining());
        cv.put("idExercise", trainingExercise.getIdExercise());
        cv.put("exerciseDuration", trainingExercise.getExerciseDuration());
        cv.put("exerciseSets", trainingExercise.getExerciseSets());
        cv.put("rest", trainingExercise.getRest());
        getWritableDatabase().insert(TABELA, null, cv);
    }

    public List<TrainingExercise> getList() {
        List<TrainingExercise> trainingExercises = new ArrayList<>();
//        String args [] = {Long.toString()};
        String sql = "SELECT * FROM " + TABELA + ";";
        Cursor c = getReadableDatabase().rawQuery(sql, null);
        Long idTraining, idExercise, idTrainingExercises;
        int exerciseDuration, rest, exerciseSets;
        while (c.moveToNext()) {
            idTrainingExercises = c.getLong(c.getColumnIndex("idTrainingExercise"));
            idTraining = c.getLong(c.getColumnIndex("idTraining"));
            idExercise = c.getLong(c.getColumnIndex("idExercise"));
            exerciseDuration = c.getInt(c.getColumnIndex("exerciseDuration"));
            rest = c.getInt(c.getColumnIndex("rest"));
            exerciseSets = c.getInt(c.getColumnIndex("exerciseSets"));
            TrainingExercise trainingExercise = new TrainingExercise(idTrainingExercises, idTraining, idExercise, exerciseDuration, rest, exerciseSets);
            trainingExercises.add(trainingExercise);
        }
        c.close();
        return trainingExercises;
    }

    public void delete(TrainingExercise trainingExercise) {
        String[] args = {Long.toString(trainingExercise.getIdTrainingExercises())};
        getWritableDatabase().delete(TABELA, "idTrainingExercise=?", args);
    }

    public void update(TrainingExercise trainingExercise) {
        ContentValues cv = new ContentValues();
        cv.put("idTraining", trainingExercise.getIdTraining());
        cv.put("idExercise", trainingExercise.getIdExercise());
        cv.put("exerciseDuration", trainingExercise.getExerciseDuration());
        cv.put("exerciseSets", trainingExercise.getExerciseSets());
        cv.put("rest", trainingExercise.getRest());
        String[] args = {Long.toString(trainingExercise.getIdTrainingExercises())};
        getWritableDatabase().update(TABELA, cv, "idTrainingExercise=?", args);
    }
}
