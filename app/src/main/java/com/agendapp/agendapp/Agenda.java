package com.agendapp.agendapp;

/**
 * Created by Alex on 29/05/2016.
 */
public class Agenda {
    private int id;
    private String data;
    private String local;
    private String evento;

    public void setId(int id){this.id = id;}
    public void setData(String data){this.data = data;}
    public void setLocal(String local){this.local = local;}
    public void setEvento(String evento){this.evento = evento;}

    public int getId(){return this.id;}
    public String getData(){return this.data;}
    public String getLocal(){return this.local;}
    public String getEvento(){return this.evento;}
}
