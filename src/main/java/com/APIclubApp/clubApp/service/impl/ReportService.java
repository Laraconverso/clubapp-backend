package com.APIclubApp.clubApp.service.impl;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Objects;

@Service
public class ReportService {
    public byte[] generatePdfReport(List<Object[]> players) {
        /*ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try (PdfWriter writer = new PdfWriter(outputStream);
             PdfDocument pdf = new PdfDocument(writer);
             Document document = new Document(pdf)) {

            // Agrega un título al documento
            document.add(new Paragraph("Reporte de Jugadores con Cuota Pagada del mes"));

            // Agrega los datos de los jugadores al documento
            for (Object[] player : players) {
                String playerId = Objects.toString(player[0], "");
                String userName = Objects.toString(player[1], "");
                String userLastName = Objects.toString(player[2], "");
                document.add(new Paragraph("ID: " + playerId + ", Nombre: " + userName + ", Apellido: " + userLastName));
            }
        } catch (Exception e) {
            e.printStackTrace(); // Manejo básico de excepciones
        }

        return outputStream.toByteArray();
    }*/

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, outputStream);
            document.open();

            // Agrega un título al documento
            document.add(new Paragraph("Reporte de Jugadores con Cuota Pagada del mes"));

            // Agrega los datos de los jugadores al documento
            for (Object[] player : players) {
                String playerId = Objects.toString(player[0], "");
                String userName = Objects.toString(player[1], "");
                String userLastName = Objects.toString(player[2], "");
                document.add(new Paragraph("Nombre: " + userName + ", Apellido: " + userLastName));
            }

            document.close();
        } catch (DocumentException e) {
            e.printStackTrace(); // Manejo básico de excepciones
        }

        return outputStream.toByteArray();
    }
}
