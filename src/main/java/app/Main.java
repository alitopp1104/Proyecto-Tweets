/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import java.util.List;
import java.util.Map;
import java.util.Optional;
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
        Supplier<List<Tweet>> lectorTweets =
                loader.crearLectorTweets("data/twitters.csv");

        // Servicios
        TextTransformService transformService = new TextTransformService();
        TweetAnalyticsService analyticsService = new TweetAnalyticsService();
        ReportGenerator reportGenerator = new ReportGenerator();

        // TRANSFORMACIÓN (Function)
        Function<Tweet, Tweet> transformacionCompleta
                = transformService.sinMenciones
                        .andThen(transformService.sinHashtags)
                        .andThen(transformService.sinEspaciosExtra)
                        .andThen(transformService.aMayusculas);

      

        // RUNNABLE PRINCIPAL
        Runnable pipelinePrincipal = () -> {

            //  Cargar tweets (Supplier)
            List<Tweet> tweets = lectorTweets.get();

            //  Transformar tweets (Function)
            List<Tweet> tweetsLimpios =
                    transformService.transformarTweets(tweets, transformacionCompleta);

            // Procesar tweets (Consumer)
            transformService.procesarTweets(tweets, transformacionCompleta);

            //  UBinaryOperator
            Optional<Tweet> tweetUnido = tweetsLimpios.stream()
                    .filter(t -> t.getSentimiento().equalsIgnoreCase("positive"))
                    .reduce(transformService.unirTweets);

            tweetUnido.ifPresent(t -> {
                System.out.println("\nTWEET RESULTANTE DEL BinaryOperator:");
                System.out.println(t);
                
                reportGenerator.guardarTweetCombinado(
            t, "output/tweet_combinado.csv"
    );
            });

            //  Análisis estadístico
            Map<String, Long> conteo =
                    analyticsService.contarTweetsPorSentimiento(tweetsLimpios);

            double promedioPositivos =
                    analyticsService.calcularPromedioLongitud(tweetsLimpios, "positive");

            //  Crear resumen
            String resumen = "CONTEO POR SENTIMIENTO:\n"
                    + conteo
                    + "\n\nPROMEDIO DE LONGITUD (positive):\n"
                    + promedioPositivos;

            //  Guardar archivos
            reportGenerator.guardarTweetsLimpios(
                    tweetsLimpios, "output/tweets_limpios.csv");

            reportGenerator.guardarResumenEstadisticas(
                    resumen, "output/resumen_estadisticas.txt");

            System.out.println("\nPROCESAMIENTO FINALIZADO");
        };

        // EJECUCIÓN DEL RUNNABLE
        pipelinePrincipal.run();
    }
}
