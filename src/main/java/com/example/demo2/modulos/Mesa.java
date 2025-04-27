package com.example.demo2.modulos;

public class Mesa {
    public int id_mesa;
    public int capacidad;
    public String tipo;
    private int cliente_asifnado;
    private boolean is_ocupada;
    private String name;

    public Mesa(int id_mesa,int capacidad,String tipo){
        this.id_mesa=id_mesa;
        this.capacidad=capacidad;
        this.tipo=tipo;
        this.is_ocupada=false;
    }

    public void set_ocupada(boolean ocupada){
        this.is_ocupada=ocupada;
    }

    public boolean get_ocupada(){
        return this.is_ocupada;
    }

    public void set_cliente(int cliente_asifnado){
        this.cliente_asifnado=cliente_asifnado;
    }

    public void set_name_client(String name){
        this.name=name;
    }

    public String get_cliente_asignado_nombre(){
        return this.name;
    }

    public int get_cliente_asignado(){
        return this.cliente_asifnado;
    }
}
