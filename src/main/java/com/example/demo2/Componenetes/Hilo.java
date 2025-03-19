package com.example.demo2.Componenetes;

import javafx.scene.control.ProgressBar;

import java.util.Random;

public class Hilo extends Thread{

    private ProgressBar ruta;


    public Hilo(String nombre,ProgressBar ruta){

        super(nombre);
        this.ruta = ruta;
    }

    @Override
    public void run() {
        super.run();
        double avance=0.0;
        while(avance<1){
            avance += (Math.random()*0.01);
            this.ruta.setProgress(avance);
            try {
                sleep((long)(Math.random()*150));
            } catch (InterruptedException e) {}
        }
    }

    public void interrumpir() {
        super.interrupt();
    }
}
