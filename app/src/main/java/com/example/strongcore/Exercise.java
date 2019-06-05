package com.example.strongcore;

public class Exercise {

    private Long idExercise;
    private String exercise;

    Exercise(String exercise){
        setExercise(exercise);
    }

    Exercise(Long idExercise, String exerciseName) {
        this(exerciseName);
        setIdExercise(idExercise);
    }

    public String getExercise(){return exercise;}

    public Long getIdExercise() {
        return idExercise;
    }

    public void setIdExercise(Long idExercise) {
        this.idExercise = idExercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }
}
