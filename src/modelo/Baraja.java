package modelo;

import java.io.PrintWriter; // Para formatear salida
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

// Debe ser Serializable para guardar el estado
public abstract class Baraja implements Serializable {
    private static final long serialVersionUID = 1L;

    // Protected para que las subclases puedan acceder y modificarla directamente
    protected List<Carta> cartas;

    public Baraja() {
        this.cartas = new ArrayList<>();
        // La creación real se delega al método abstracto
    }

    /**
     * Método abstracto que deben implementar las subclases para
     * llenar la lista 'cartas' según el tipo de baraja.
     */
    public abstract void crearBaraja();

    /**
     * Método abstracto para obtener la representación textual formateada
     * de una carta específica según las reglas de esta baraja.
     * @param carta La carta a formatear.
     * @return String formateado de la carta (e.j. "As de OROS", "Reina de PICAS").
     */
    public abstract String formatarCarta(Carta carta);

    /**
     * Baraja (mezcla) las cartas en la lista usando Collections.shuffle.
     */
    public void barajar() {
        if (this.cartas != null) {
            Collections.shuffle(this.cartas);
        } else {
            // Esto no debería ocurrir si el constructor y crearBaraja funcionan
            System.err.println("Advertencia: Intentando barajar una lista de cartas nula.");
            this.cartas = new ArrayList<>(); // Recuperarse creando una lista vacía
        }
    }

    /**
     * Devuelve la lista de cartas actual. Se devuelve una copia
     * para evitar modificaciones externas no deseadas si no se quisiera.
     * O se devuelve la referencia directa si las subclases la necesitan.
     * Por simplicidad aquí, devolvemos la referencia directa.
     */
    public List<Carta> getCartas() {
        return cartas;
    }

    /**
     * Muestra la baraja completa en la consola, usando el formato específico.
     */
    public void mostrarBarajaConsola() {
        if (cartas == null || cartas.isEmpty()) {
            System.out.println("La baraja está vacía.");
            return;
        }
        System.out.println("--- Baraja Actual (" + this.getClass().getSimpleName() + ") ---");
        for (Carta carta : cartas) {
            System.out.println(formatarCarta(carta));
        }
        System.out.println("------------------------------");
    }

    /**
     * Imprime la baraja completa a un PrintWriter (útil para ficheros),
     * usando el formato específico.
     * @param writer El PrintWriter donde escribir la baraja.
     */
    public void imprimirBaraja(PrintWriter writer) {
        Objects.requireNonNull(writer, "El PrintWriter no puede ser nulo");
        if (cartas == null || cartas.isEmpty()) {
            writer.println("La baraja está vacía.");
            return;
        }
        writer.println("--- Baraja Actual (" + this.getClass().getSimpleName() + ") ---");
        for (Carta carta : cartas) {
            writer.println(formatarCarta(carta));
        }
        writer.println("------------------------------");
    }


    @Override
    public String toString() {
        // Un toString básico para la baraja en sí
        return "Baraja de tipo " + this.getClass().getSimpleName() + " con " + (cartas != null ? cartas.size() : 0) + " cartas.";
    }
}