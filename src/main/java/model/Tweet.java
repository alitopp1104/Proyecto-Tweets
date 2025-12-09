/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author alo11
 */
public class Tweet {
    private int id;
    private String entidad;
    private String sentimiento;
    private String contenido;

    public Tweet(int id, String entidad, String sentimiento, String contenido) {
        this.id = id;
        this.entidad = entidad;
        this.sentimiento = sentimiento;
        this.contenido = contenido;
    }

    
    
    public int getId() {
        return id;
    }

    public String getEntidad() {
        return entidad;
    }

    public String getSentimiento() {
        return sentimiento;
    }

    public String getContenido() {
        return contenido;
    }

    @Override
    public String toString() {
        return "Tweet{" + "id=" + id + ", entidad=" + entidad + ", sentimiento=" + sentimiento + ", contenido=" + contenido + '}';
    }
    
    
    
}
