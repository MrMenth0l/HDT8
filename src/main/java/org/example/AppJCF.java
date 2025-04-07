package org.example;

import java.io.IOException;
import java.util.List;

/**
 * Versión del programa principal usando java.util.PriorityQueue vía adaptador.
 */
public class AppJCF {
    public static void main(String[] args) {
        String filename = "src/pacientes.txt";
        try {
            // Leer lista de pacientes desde el archivo
            List<Paciente> listaPacientes = PacienteReader.readFromFile(filename);

            // Usar el adaptador JCF en lugar de VectorHeap
            IPriorityQueue<Paciente> cola = new JCFPriorityQueue<>();

            // Encolar todos los pacientes
            for (Paciente p : listaPacientes) {
                cola.add(p);
            }

            // Procesar la cola en orden de urgencia
            System.out.println("Atención de pacientes (JCF) en orden de prioridad:");
            while (!cola.isEmpty()) {
                System.out.println(cola.remove());
            }

        } catch (IOException e) {
            System.err.println("Error leyendo el archivo de pacientes: " + e.getMessage());
        }
    }
}
