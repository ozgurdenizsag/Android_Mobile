package com.example.listeat;

import java.util.ArrayList;

public class MyList {

    private String nomList;
    private String mesIngredients;

    public MyList(String nomList, String mesIngredients) {
        this.nomList = nomList;
        this.mesIngredients = mesIngredients;
    }

    public String getNomList() {
        return nomList;
    }

    public void setNomList(String nomList) {
        this.nomList = nomList;
    }

    public String getMesIngredients() {
        return mesIngredients;
    }

    public void setMesIngredients(String mesIngredients) {
        this.mesIngredients = mesIngredients;
    }


}
