/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import model.Tweet;

/**
 *
 * @author alo11
 */
public class TweetLoader {

    public Supplier<List<Tweet>> crearLectorTweets(String rutaArchivo) {

        return () -> {

            List<Tweet> tweets = new ArrayList<>();

            try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
                String linea;

                while ((linea = br.readLine()) != null) {

                    String[] datos = linea.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", 4);
                    
                    if (datos.length < 4) {
                        System.out.println("Línea inválida: " + linea);
                        continue; 
                    }

                    int id = Integer.parseInt(datos[0]);
                    String entidad = datos[1];
                    String sentimiento = datos[2];
                    String contenido = datos[3].trim();

                    tweets.add(new Tweet(id, entidad, sentimiento, contenido));
                }

            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }

            return tweets; 
        };

    }
}