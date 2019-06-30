package com.example.strongcore;

import android.content.Context;
import android.speech.tts.TextToSpeech;

import java.util.Locale;

public class Speak extends Thread implements TextToSpeech.OnInitListener {

    private final Context context;
    private TextToSpeech textToSpeech;
    private String description;
    private boolean speakingEnd;

    Speak(String descricao, Context context) {
        this.context = context;
        description = descricao;
        createTTS();
    }

    @Override
    public void run() {
        super.run();
        textToSpeech.speak(description, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS){
            textToSpeech.setLanguage(Locale.getDefault());
            start();
        }
    }

    public boolean isSpeaking() {
        return speakingEnd;
    }

    private void createTTS() {
        textToSpeech = new TextToSpeech(context, this);
    }
}
