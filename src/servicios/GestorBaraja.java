package servicios;

import modelo.Baraja;
import modelo.BarajaEspanola;
import modelo.BarajaFrancesa;

import java.io.*; // Para serialización

public class GestorBaraja {

    private Baraja barajaActual;
    private static final String ARCHIVO_GUARDADO = "baraja_actual.dat"; // Nombre fijo para el archivo

    public GestorBaraja() {
        cargarBaraja(); // Intenta cargar la baraja al iniciar
    }

    /**
     * Crea una nueva baraja del tipo especificado y reemplaza la actual.
     * @param tipo 1 para Española, 2 para Francesa.
     * @param incluir8y9 Solo relevante si tipo es 1 (Española).
     * @return true si se creó con éxito, false si el tipo no es válido.
     */
    public boolean crearNuevaBaraja(int tipo, boolean incluir8y9) {
        switch (tipo) {
            case 1:
                this.barajaActual = new BarajaEspanola(incluir8y9);
                System.out.println("Baraja Española creada (" + (incluir8y9 ? "48" : "40") + " cartas).");
                return true;
            case 2:
                this.barajaActual = new BarajaFrancesa();
                System.out.println("Baraja Francesa creada (52 cartas).");
                return true;
            default:
                System.err.println("Tipo de baraja no reconocido.");
                return false; // Tipo no válido
        }
    }

    /**
     * Devuelve la baraja actualmente en gestión.
     * Puede ser null si no se ha creado o cargado ninguna.
     * @return La instancia de Baraja actual, o null.
     */
    public Baraja getBarajaActual() {
        return barajaActual;
    }

    /**
     * Comprueba si existe una baraja actualmente gestionada.
     * @return true si hay una baraja, false en caso contrario.
     */
    public boolean hayBaraja() {
        return barajaActual != null;
    }

    /**
     * Baraja la baraja actual, si existe.
     */
    public void barajar() {
        if (hayBaraja()) {
            barajaActual.barajar();
            System.out.println("Baraja barajada.");
        } else {
            System.out.println("No hay ninguna baraja creada para barajar.");
        }
    }

    /**
     * Guarda el estado actual de la baraja (si existe) en un fichero binario.
     */
    public void guardarBaraja() {
        if (!hayBaraja()) {
            // Si no hay baraja, podemos opcionalmente borrar el archivo de guardado
            // para que no se cargue una antigua la próxima vez.
            File archivo = new File(ARCHIVO_GUARDADO);
            if (archivo.exists()) {
                archivo.delete();
                // System.out.println("Archivo de guardado anterior eliminado.");
            }
            return; // No hay nada que guardar
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_GUARDADO))) {
            oos.writeObject(this.barajaActual);
            System.out.println("Estado de la baraja guardado en " + ARCHIVO_GUARDADO);
        } catch (IOException e) {
            System.err.println("Error al guardar la baraja: " + e.getMessage());
            e.printStackTrace(); // Mostrar más detalles del error
        }
    }

    /**
     * Carga el estado de la baraja desde el fichero binario.
     * Se llama desde el constructor para recuperar el estado anterior.
     */
    private void cargarBaraja() {
        File archivo = new File(ARCHIVO_GUARDADO);
        if (!archivo.exists()) {
            System.out.println("No se encontró archivo de guardado ("+ ARCHIVO_GUARDADO +"). Iniciando sin baraja.");
            this.barajaActual = null;
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            this.barajaActual = (Baraja) ois.readObject(); // Carga el objeto
            System.out.println("Baraja recuperada desde " + ARCHIVO_GUARDADO);
            if (this.barajaActual != null) {
                System.out.println("Tipo cargado: " + this.barajaActual.getClass().getSimpleName());
            }
        } catch (FileNotFoundException e) {
            // Esto ya se comprueba con archivo.exists(), pero por si acaso
            System.out.println("Archivo de guardado no encontrado. Iniciando sin baraja.");
            this.barajaActual = null;
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            // ClassNotFound si la clase guardada ya no existe o cambió incompatiblemente
            // ClassCastException si el objeto guardado no es una Baraja
            // IOException para otros problemas de lectura
            System.err.println("Error al cargar la baraja desde " + ARCHIVO_GUARDADO + ": " + e.getMessage());
            System.err.println("Iniciando sin baraja debido al error de carga.");
            this.barajaActual = null;
            // Opcional: Borrar el archivo corrupto
            // archivo.delete();
        }
    }
}