/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import model.Tweet;

/**
 *
 * @author alo11
 */
public class TweetAnalyticsService {
  public double calcularPromedioLongitud(List<Tweet> tweets, String sentimiento) {
    return tweets.stream()
            .filter(t -> t.getSentimiento().equalsIgnoreCase(sentimiento))
            .mapToInt(t -> t.getContenido().length())
            .average()
            .orElse(0.0);
}

  public Map<String, Long> contarTweetsPorSentimiento(List<Tweet> tweets) {

    return tweets.stream()
            .collect(Collectors.groupingBy(
                    t -> t.getSentimiento(),  
                    Collectors.counting()
            ));
}

            
}
