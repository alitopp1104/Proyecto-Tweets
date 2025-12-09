/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import model.Tweet;

/**
 *
 * @author alo11
 */
public class TextTransformService {

    // TRANSFORMAR
    public Function<Tweet, Tweet> aMayusculas = t
            -> new Tweet(t.getId(), t.getEntidad(), t.getSentimiento(),
                    t.getContenido().toUpperCase());

public Function<Tweet, Tweet> sinMenciones = t -> {

    String limpio = t.getContenido()
        .replaceAll("^\"|\"$", "") 
        .replace("\"\"", "\"")  
        .replaceAll("@\\s*[^\\s,]+", "")
        .replaceAll("\\s+", " ")
        .trim();

    return new Tweet(t.getId(), t.getEntidad(), t.getSentimiento(), limpio);
};

    public Function<Tweet, Tweet> sinHashtags = t -> {
        String nuevo = t.getContenido().replaceAll("#\\w+", "");
        return new Tweet(t.getId(), t.getEntidad(), t.getSentimiento(), nuevo.trim());
    };
    public Function<Tweet, Tweet> sinEspaciosExtra = t -> {
        String nuevo = t.getContenido().replaceAll("\\s+", " ").trim();
        return new Tweet(t.getId(), t.getEntidad(), t.getSentimiento(), nuevo);
    };

    public List<Tweet> transformarTweets(List<Tweet> tweets, Function<Tweet, Tweet> transformacion) {
        return tweets.stream()
                .map(transformacion)
                .toList();
    }

    //PROCESAR
    public Consumer<Tweet> accionFinal = t -> System.out.println(t);

    public void procesarTweets(List<Tweet> tweets, Function<Tweet, Tweet> transformacion, Consumer<Tweet> accionFinal) {

        tweets.stream()
                .map(transformacion) 
                .forEach(accionFinal); 
    }

}
