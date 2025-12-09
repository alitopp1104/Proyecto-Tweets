/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import model.Tweet;
import report.ReportGenerator;
import service.TextTransformService;
import service.TweetAnalyticsService;
import service.TweetLoader;

/**
 *
 * @author alo11
 */
public class Main {

    public static void main(String args[]) {

        // SUPPLIER desde TweetLoader 
        TweetLoader loader = new TweetLoader();
        Supplier<List<Tweet>> lectorTweets = loader.crearLectorTweets("data/twitters.csv");

        // Servicios
        TextTransformService transformService = new TextTransformService();
        TweetAnalyticsService analyticsService = new TweetAnalyticsService();
        ReportGenerator reportGenerator = new ReportGenerator();

        // Transformación 
        Function<Tweet, Tweet> transformacionCompleta
                = transformService.sinMenciones
                        .andThen(transformService.sinHashtags)
                        .andThen(transformService.sinEspaciosExtra)
                        .andThen(transformService.aMayusculas);

        // Consumer 
        Consumer<Tweet> accionFinal = transformService.accionFinal;

        // RUNNABLE PRINCIPAL
        Runnable pipelinePrincipal = () -> {

            //️Cargar tweets
            List<Tweet> tweets = lectorTweets.get();

            // Transformar tweets 
            List<Tweet> tweetsLimpios = transformService.transformarTweets(tweets, transformacionCompleta);

            //  Procesar tweets 
            transformService.procesarTweets(tweets, transformacionCompleta, accionFinal);

            //  Análisis estadístico
            Map<String, Long> conteo = analyticsService.contarTweetsPorSentimiento(tweetsLimpios);

            double promedioPositivos = analyticsService.calcularPromedioLongitud(tweetsLimpios, "positive");

            //  Crear resumen
            String resumen = "CONTEO POR SENTIMIENTO:\n"
                    + conteo
                    + "\n\nPROMEDIO DE LONGITUD (positive):\n"
                    + promedioPositivos;

            //  Guardar archivos
            
            reportGenerator.guardarTweetsLimpios(tweetsLimpios, "output/tweets_limpios.csv");

            reportGenerator.guardarResumenEstadisticas(resumen,"output/resumen_estadisticas.txt");

            System.out.println("PROCESAMIENTO FINALIZADO");
        };

        // EJECUCIÓN DEL RUNNABLE
        pipelinePrincipal.run();
    }
}
