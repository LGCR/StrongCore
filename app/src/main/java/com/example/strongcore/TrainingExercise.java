package com.example.strongcore;

import java.io.Serializable;

public class TrainingExercise implements Serializable {

    private Long idTrainingExercises;
    private Long idTraining;
    private Long idExercise;
    private int exerciseDuration;
    private int rest;
    private int exerciseSets;

    TrainingExercise(Long idTrainingExercises, Long idTraining, Long idExercise, int exerciseDuration, int rest, int exerciseSets){
        setIdTrainingExercises(idTrainingExercises);
        setIdTraining(idTraining);
        setIdExercise(idExercise);
        setExerciseDuration(exerciseDuration);
        setRest(rest);
        setExerciseSets(exerciseSets);
    }

    public Long getIdTrainingExercises() {
        return idTrainingExercises;
    }

    public void setIdTrainingExercises(Long idTrainingExercises) {
        this.idTrainingExercises = idTrainingExercises;
    }

    public Long getIdTraining() {
        return idTraining;
    }

    public void setIdTraining(Long idTraining) {
        this.idTraining = idTraining;
    }

    public Long getIdExercise() {
        return idExercise;
    }

    public void setIdExercise(Long idExercise) {
        this.idExercise = idExercise;
    }

    public int getExerciseDuration() {
        return exerciseDuration;
    }

    public void setExerciseDuration(int exerciseDuration) {
        this.exerciseDuration = exerciseDuration;
    }

    public int getRest() {
        return rest;
    }

    public void setRest(int rest) {
        this.rest = rest;
    }

    public int getExerciseSets() {
        return exerciseSets;
    }

    public void setExerciseSets(int exerciseSets) {
        this.exerciseSets = exerciseSets;
    }
}
