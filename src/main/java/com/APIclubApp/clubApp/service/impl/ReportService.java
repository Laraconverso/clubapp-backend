package com.APIclubApp.clubApp.service.impl;


import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Objects;

@Service
public class ReportService {
    public byte[] generatePdfReport(List<Object[]> players) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

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
                document.add(new Paragraph(/*"ID: " + playerId +*/ "Nombre: " + userName + ", Apellido: " + userLastName));
            }
        } catch (Exception e) {
            e.printStackTrace(); // Manejo básico de excepciones
        }

        return outputStream.toByteArray();
    }
}
