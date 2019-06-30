package com.example.strongcore;

import java.util.ArrayList;

public class PainSection {

    private int index;
    private String description;
    private ArrayList<String> items;

    PainSection(int index, String description){
        setIndex(index);
        setDescription(description);
        items = new ArrayList<>();
        addItem("Nenhum Desconforto/dor");
        addItem("Algum Desconforto/dor");
        addItem("Moderado Desconforto/dor");
        addItem("Bastante Desconforto/dor");
        addItem("Intolerável Desconforto/dor");
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getItems() {
        return items;
    }

    public void addItem(String item){
        items.add(item);
    }
}
