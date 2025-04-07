package org.example;

import java.io.IOException;
import java.util.List;


public class App {
    public static void main(String[] args) {
        String filename = "src/pacientes.txt";
        try {

            List<Paciente> listaPacientes = PacienteReader.readFromFile(filename);

            IPriorityQueue<Paciente> cola = new VectorHeap<>();

            for (Paciente p : listaPacientes) {
                cola.add(p);
            }

            System.out.println("Atención de pacientes en orden de prioridad:");
            while (!cola.isEmpty()) {
                Paciente siguiente = cola.remove();
                System.out.println(siguiente);
            }

        } catch (IOException e) {
            System.err.println("Error leyendo el archivo de pacientes: " + e.getMessage());
        }
    }
}
