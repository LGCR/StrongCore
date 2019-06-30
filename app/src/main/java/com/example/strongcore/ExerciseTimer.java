package com.example.strongcore;

import android.os.CountDownTimer;

public class ExerciseTimer extends CountDownTimer {

    static final int GET_READY_WARNING = 0;
    static final int REST_WARNING = 1;
    static final int EXECUTING = 2;
    static final int REST = 3;

    private TrainingThread trainingThread;
    private int timerType;
    private int totalSets;
    private TrainingExercise currentExercise;
    private int currentSet;

    public ExerciseTimer(TrainingThread trainingThread
            , int timerType, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.trainingThread = trainingThread;
        this.timerType = timerType;

        currentExercise = this.trainingThread.getTrainingActivity()
                .getTrainingExercises().get(trainingThread
                        .getTrainingActivity().getCurrentExercise());

        totalSets = currentExercise.getExerciseSets();

        currentSet = trainingThread.getCurrentSet();

        if (this.timerType == GET_READY_WARNING){
            /*new Speak("Prepare-se para iniciar o exercício. deite em um colchonete com a barriga virada para baixo, apoiando os cotovelos alinhadamente com seu ombro\n" +
                    "Depois levante o quadril, apoiando-se apenas pela ponta dos pés e pelos cotovelos.\n" +
                    "Contraia bem o  abdômen.", trainingThread.getTrainingActivity());*/
        } else if (this.timerType == REST_WARNING){
            new Speak("Descanse por "
                    + currentExercise.getRest()
                    + " segundos.", trainingThread.getTrainingActivity());
        }

        start();
    }

    @Override
    public void onTick(long millisUntilFinished) {
        long seconds = millisUntilFinished/1000;

        if (timerType == EXECUTING
                && (seconds == (currentExercise.getExerciseDuration()/2))){
            new Speak("Faltam " + seconds + " segundos para terminar", trainingThread.getTrainingActivity());
        } else if (timerType == REST
                && seconds == (currentExercise.getRest()/2)) {
            new Speak("Faltam " + seconds + " segundos para começar", trainingThread.getTrainingActivity());
        }

        if (timerType == EXECUTING || timerType == REST) {
            if (seconds >= 10) {
                trainingThread.getTrainingActivity().getTimer().setText("00:" + seconds);
            } else {
                trainingThread.getTrainingActivity().getTimer().setText("00:0" + seconds);
            }
        }
    }

    @Override
    public void onFinish() {
        if (timerType == EXECUTING) {
            if (currentSet < totalSets) {
                trainingThread.getTrainingActivity().getSets().setText(++currentSet + " de " + totalSets);
                trainingThread.setCurrentSet(currentSet);
            }
            if (currentSet == totalSets){
                trainingThread.setCurrentSet(0);
                trainingThread.nextExercise();
                this.cancel();
            }
            setWarning(REST_WARNING);
        }
        if (timerType == REST){
            setWarning(GET_READY_WARNING);
        }
        if (timerType == GET_READY_WARNING){
            trainingThread.getTrainingActivity().getTimer()
                    .setText("00:" + currentExercise.getExerciseDuration());
            setExecuting();
        }
        if (timerType == REST_WARNING){
            trainingThread.getTrainingActivity().getTimer().setText("00:" + currentExercise.getRest());
            setRest();
        }
    }

    private void setExecuting() {
        new ExerciseTimer(trainingThread, EXECUTING,
                currentExercise.getExerciseDuration()*1000, 1000);
    }

    private void setWarning(int type) {
        new ExerciseTimer(trainingThread, type,  1000, 1000);
    }

    private void setRest(){
        new ExerciseTimer(trainingThread, REST, currentExercise.getRest()*1000, 1000);
    }
}
