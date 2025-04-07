package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PacienteTest {

    @Test
    public void testCompareToDifferentCodes() {
        Paciente pA = new Paciente("Alice", "desc", 'A');
        Paciente pB = new Paciente("Bob", "desc", 'B');
        // 'A' es más urgente que 'B', por lo tanto pA < pB
        assertTrue(pA.compareTo(pB) < 0, "Paciente con código A debe tener mayor prioridad que B");
        assertTrue(pB.compareTo(pA) > 0, "Paciente con código B debe tener menor prioridad que A");
    }

    @Test
    public void testCompareToSameCode() {
        Paciente p1 = new Paciente("Alice", "desc", 'C');
        Paciente p2 = new Paciente("Bob", "desc", 'C');
        // Mismo código, compareTo debe ser 0
        assertEquals(0, p1.compareTo(p2), "Pacientes con mismo código deben compararse iguales");
    }

    @Test
    public void testToString() {
        Paciente p = new Paciente("Carlos", "dolor", 'D');
        assertEquals("Carlos, dolor, D", p.toString(), "toString debe devolver 'nombre, sintoma, codigo'");
    }
}
