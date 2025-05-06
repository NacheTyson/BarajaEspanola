package modelo;

import java.io.Serializable;
import java.util.Objects;

// Implementa Serializable para poder guardarla
public class Carta implements Serializable {
    private static final long serialVersionUID = 1L; // Buena práctica para Serializable

    private int numero;
    private String palo;

    public Carta(int numero, String palo) {
        // Validación básica
        if (numero <= 0 || palo == null || palo.trim().isEmpty()) {
            throw new IllegalArgumentException("Número debe ser positivo y palo no puede ser vacío.");
        }
        this.numero = numero;
        this.palo = palo;
    }

    // --- Getters ---
    public int getNumero() {
        return numero;
    }

    public String getPalo() {
        return palo;
    }

    // --- equals() y hashCode() ---
    // Importante si se usan colecciones como Set o se busca
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carta carta = (Carta) o;
        return numero == carta.numero && Objects.equals(palo, carta.palo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero, palo);
    }

    // --- toString() Básico ---
    // La representación formateada (As, Rey, Sota...) la hará la Baraja
    @Override
    public String toString() {
        return numero + " de " + palo;
    }
}