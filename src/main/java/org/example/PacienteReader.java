package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class PacienteReader {


    public static List<Paciente> readFromFile(String filename) throws IOException {
        List<Paciente> pacientes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\s*,\\s*");
                if (parts.length == 3) {
                    String nombre = parts[0];
                    String sintoma = parts[1];
                    char codigo = parts[2].charAt(0);
                    pacientes.add(new Paciente(nombre, sintoma, codigo));
                }
            }
        }
        return pacientes;
    }
}
