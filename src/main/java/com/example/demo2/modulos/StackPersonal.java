package com.example.demo2.modulos;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class StackPersonal extends StackPane {
    public Casilla casilla;
    public ImageView img;

    public StackPersonal(Casilla casilla,ImageView img){
        super();
        this.casilla=casilla;
        this.img=img;
        this.getChildren().addAll(this.casilla,this.img);
        this.getStyleClass().add("borde_cuadro");
    }

    public void cambiar_imagen(String imagen_nueva){
        System.out.println(imagen_nueva);
        Image new_i = new Image(getClass().getResource(imagen_nueva).toString());
        this.img.setImage(new_i);
    }

}
