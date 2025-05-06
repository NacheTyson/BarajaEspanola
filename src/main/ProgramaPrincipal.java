package main;

import modelo.Baraja; // Necesario para instanceof o para getBarajaActual
import servicios.GestorBaraja;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ProgramaPrincipal {

    private static GestorBaraja gestor = new GestorBaraja(); // Gestor que maneja la baraja y persistencia
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion = 0;

        do {
            mostrarMenu();
            try {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea restante

                switch (opcion) {
                    case 1:
                        gestionarNuevaBaraja();
                        break;
                    case 2:
                        gestor.barajar(); // El gestor ya comprueba si hay baraja
                        break;
                    case 3:
                        gestionarMostrarBaraja();
                        break;
                    case 4:
                        gestionarImprimirBaraja();
                        break;
                    case 5:
                        System.out.println("Guardando estado y saliendo...");
                        gestor.guardarBaraja(); // Guardar antes de salir
                        System.out.println("¡Adiós!");
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, introduce un número entre 1 y 5.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.err.println("Error: Debes introducir un número entero para la opción.");
                scanner.nextLine(); // Limpiar el buffer del scanner
                opcion = 0; // Resetear opción para que el bucle continúe
            } catch (Exception e) { // Captura general para otros posibles errores inesperados
                System.err.println("Ha ocurrido un error inesperado: " + e.getMessage());
                e.printStackTrace(); // Útil para depurar
                opcion = 0; // Resetear
            }
            System.out.println(); // Línea en blanco para separar iteraciones del menú

        } while (opcion != 5);

        scanner.close(); // Cerrar el scanner al finalizar
    }

    private static void mostrarMenu() {
        System.out.println("--- MENÚ BARAJAS ---");
        System.out.println("1. Nueva baraja (reemplaza la actual si existe)");
        System.out.println("2. Barajar");
        System.out.println("3. Mostrar baraja actual");
        System.out.println("4. Imprimir baraja actual a fichero");
        System.out.println("5. Salir");
        System.out.print("Elige una opción: ");
    }

    private static void gestionarNuevaBaraja() {
        int tipo = 0;
        while (tipo != 1 && tipo != 2) {
            System.out.println("Elige el tipo de baraja:");
            System.out.println("  1. Española");
            System.out.println("  2. Francesa");
            System.out.print("Tipo: ");
            try {
                tipo = scanner.nextInt();
                scanner.nextLine(); // Consumir newline
                if (tipo != 1 && tipo != 2) {
                    System.err.println("Por favor, introduce 1 o 2.");
                }
            } catch (InputMismatchException e) {
                System.err.println("Entrada inválida. Introduce 1 o 2.");
                scanner.nextLine(); // Limpiar buffer
                tipo = 0; // Resetear para volver a pedir
            }
        }

        boolean incluir8y9 = false; // Valor por defecto
        if (tipo == 1) { // Solo preguntar para la española
            incluir8y9 = leerBoolean("¿Incluir 8s y 9s en la baraja española? (s/n): ");
        }

        // Crear la baraja usando el gestor
        gestor.crearNuevaBaraja(tipo, incluir8y9);
        // El gestor ya imprime mensaje de confirmación
    }

    private static void gestionarMostrarBaraja() {
        if (!gestor.hayBaraja()) {
            System.out.println("No hay ninguna baraja creada para mostrar.");
            return;
        }
        // La baraja sabe cómo mostrarse
        gestor.getBarajaActual().mostrarBarajaConsola();
    }

    private static void gestionarImprimirBaraja() {
        if (!gestor.hayBaraja()) {
            System.out.println("No hay ninguna baraja creada para imprimir.");
            return;
        }

        String nombreArchivo = "";
        while (nombreArchivo.trim().isEmpty()) {
            System.out.print("Introduce el nombre del fichero donde guardar la baraja (ej: mi_baraja.txt): ");
            nombreArchivo = scanner.nextLine();
            if (nombreArchivo.trim().isEmpty()) {
                System.err.println("El nombre del archivo no puede estar vacío.");
            }
            // Podríamos añadir más validaciones al nombre del archivo aquí
        }


        // Usar try-with-resources para asegurar que el PrintWriter se cierre
        try (PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo))) {
            System.out.println("Imprimiendo baraja en el fichero: " + nombreArchivo);
            // La baraja sabe cómo imprimirse en un writer
            gestor.getBarajaActual().imprimirBaraja(writer);
            System.out.println("Baraja impresa correctamente.");
        } catch (IOException e) {
            System.err.println("Error al escribir en el fichero '" + nombreArchivo + "': " + e.getMessage());
            e.printStackTrace();
        } catch (SecurityException e) {
            System.err.println("Error de seguridad al intentar escribir el fichero '" + nombreArchivo + "'. Verifica los permisos.");
        }
    }

    // --- Método auxiliar para leer S/N ---
    private static boolean leerBoolean(String mensaje) {
        String respuesta = "";
        while (!respuesta.equalsIgnoreCase("s") && !respuesta.equalsIgnoreCase("n")) {
            System.out.print(mensaje + " ");
            respuesta = scanner.nextLine();
            if (!respuesta.equalsIgnoreCase("s") && !respuesta.equalsIgnoreCase("n")) {
                System.err.println("Respuesta inválida. Por favor, responde 's' para sí o 'n' para no.");
            }
        }
        return respuesta.equalsIgnoreCase("s");
    }
}