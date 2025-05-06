package modelo;

// Hereda Serializable de Baraja
public class BarajaFrancesa extends Baraja {
    private static final long serialVersionUID = 1L;

    public static final String[] PALOS = {"DIAMANTES", "PICAS", "CORAZONES", "TRÉBOLES"};

    public BarajaFrancesa() {
        super(); // Llama al constructor de Baraja
        crearBaraja(); // Llena la baraja
    }

    @Override
    public void crearBaraja() {
        cartas.clear(); // Limpiar por si acaso
        for (String palo : PALOS) {
            for (int numero = 1; numero <= 13; numero++) {
                cartas.add(new Carta(numero, palo));
            }
        }
    }

    @Override
    public String formatarCarta(Carta carta) {
        String nombreNumero;
        int num = carta.getNumero();

        switch (num) {
            case 1: nombreNumero = "As"; break;
            case 11: nombreNumero = "Jota"; break; // Jack
            case 12: nombreNumero = "Reina"; break; // Queen
            case 13: nombreNumero = "Rey"; break; // King
            default: nombreNumero = String.valueOf(num); // Para 2-10
        }
        return nombreNumero + " de " + carta.getPalo();
    }
}