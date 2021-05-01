package com.example.contactapp;

import android.app.Application;

import java.util.ArrayList;

public class FavArray extends Application {
    public static ArrayList<FavPerson> Favs;

    @Override
    public void onCreate() {
        super.onCreate();
        Favs=new ArrayList<>();
        Favs.add(new FavPerson("Duaa", "03415361549", "duaaazhar03@gmail.com", "Johar town","13-3-1999"));
        Favs.add(new FavPerson("Mishal", "03415361549", "mishal44@gmail.com", "Faisal town","14-1-1998"));
        Favs.add(new FavPerson("Anum", "03415361549", "anum99@gmail.com", "Iqbal town","13-3-1999"));
        Favs.add(new FavPerson("Zunaira", "03415361549", "zunaira56@gmail.com", "PCSIR Society","13-3-1999"));
        Favs.add(new FavPerson("Ayesha", "03415361549", "ayesha12@gmail.com", "Al Kabir Town","13-3-1999"));
        Favs.add(new FavPerson("Munaza", "03415361549", "munaza01@gmail.com", "Wapda town","13-3-1999"));
        Favs.add(new FavPerson("Raheema", "03415361549", "raheema04@gmail.com", "Johar town","13-3-1999"));

    }
}
