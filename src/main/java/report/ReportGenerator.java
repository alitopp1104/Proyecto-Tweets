/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package report;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import model.Tweet;

/**
 *
 * @author alo11
 */
public class ReportGenerator {
    private String escaparCSV(String texto) {
    if (texto.contains(",") || texto.contains("\"") || texto.contains("\n")) {
        texto = texto.replace("\"", "\"\"");
        return "\"" + texto + "\"";
    }
    return texto;
}


    public void guardarTweetsLimpios(List<Tweet> tweets, String rutaSalida) {

    try (PrintWriter writer = new PrintWriter(new FileWriter(rutaSalida))) {

        tweets.forEach(t -> {

            String linea = t.getId() + "," +
                           t.getEntidad() + "," +
                           t.getSentimiento() + "," +
                           escaparCSV(t.getContenido());

            writer.println(linea);
        });

    } catch (IOException e) {
        throw new UncheckedIOException(e);
    }
}

    public void guardarResumenEstadisticas(String resumen, String rutaSalida) {

        try {
            Files.writeString(Path.of(rutaSalida), resumen);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}
