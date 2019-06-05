package com.example.strongcore;

import java.io.Serializable;
import java.util.Date;

public class Training implements Serializable {

    private Long idTraining;
    private Long idUser;
    private int week;
    private Date dateCompleted;
    private int nTrainingsAvailable;
    private int nTrainingsCompleted;

    public Training(Long idTraining, Long idUser, int week, Date dateCompleted, int nTrainingsAvailable, int nTrainingsCompleted) {
        setIdTraining(idTraining);
        setIdUser(idUser);
        setWeek(week);
        setDateCompleted(dateCompleted);
        setnTrainingsAvailable(nTrainingsAvailable);
        setnTrainingsCompleted(nTrainingsCompleted);
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdTraining() {
        return idTraining;
    }

    public void setIdTraining(Long idTraining) {
        this.idTraining = idTraining;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public Date getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(Date dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    public int getnTrainingsAvailable() {
        return nTrainingsAvailable;
    }

    public void setnTrainingsAvailable(int nTrainingsAvailable) {
        this.nTrainingsAvailable = nTrainingsAvailable;
    }

    public int getnTrainingsCompleted() {
        return nTrainingsCompleted;
    }

    public void setnTrainingsCompleted(int nTrainingsCompleted) {
        this.nTrainingsCompleted = nTrainingsCompleted;
    }
}
