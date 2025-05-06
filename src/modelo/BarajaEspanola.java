package modelo;

// Hereda Serializable de Baraja
public class BarajaEspanola extends Baraja {
    private static final long serialVersionUID = 1L;

    public static final String[] PALOS = {"OROS", "COPAS", "ESPADAS", "BASTOS"};
    private boolean incluir8y9;

    /**
     * Crea una baraja española.
     * @param incluir8y9 true para 48 cartas, false para 40 cartas (sin 8s y 9s).
     */
    public BarajaEspanola(boolean incluir8y9) {
        super(); // Llama al constructor de Baraja (que inicializa la lista)
        this.incluir8y9 = incluir8y9;
        crearBaraja(); // Llena la baraja después de configurar los atributos
    }

    @Override
    public void crearBaraja() {
        cartas.clear(); // Limpiar por si se reutiliza el objeto
        for (String palo : PALOS) {
            for (int numero = 1; numero <= 12; numero++) {
                if (!incluir8y9 && (numero == 8 || numero == 9)) {
                    continue; // Saltar 8 y 9 si no se incluyen
                }
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
            case 10: nombreNumero = "Sota"; break;
            case 11: nombreNumero = "Caballo"; break;
            case 12: nombreNumero = "Rey"; break;
            default: nombreNumero = String.valueOf(num); // Para 2-7 (y 8, 9 si existen)
        }
        return nombreNumero + " de " + carta.getPalo();
    }

    // Getter opcional para saber la configuración
    public boolean isIncluir8y9() {
        return incluir8y9;
    }
}