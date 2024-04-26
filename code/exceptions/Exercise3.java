import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Locale;

class NumberOutOfRange extends Exception {
    public NumberOutOfRange(String msg) {
        super(((msg != null) ? msg : "incorrect value"));
    }
}
public class Exercise3 {

    public static void main(String[] args) {
        Scanner kbd = new Scanner(System.in).useLocale(Locale.US);
        boolean stop;
        do {
            try {
                stop = true;
                int opcion = menu(kbd);
                switch (opcion) {
                    case 0:
                        System.out.println("Has elegido salir");
                        break;
                    case 1:
                        System.out.println("Has elegido la opción " + opcion);
                        break;
                    case 2:
                        System.out.println("Has elegido la opción " + opcion);
                        break;
                    case 3:
                        System.out.println("Has elegido la opción " + opcion);
                        break;
                    case 4:
                        System.out.println("Has elegido la opción " + opcion);
                        break;
                    case 5:
                        System.out.println("Has elegido la opción " + opcion);
                        break;
                }
            } catch (NumberOutOfRange e) {
                e.printStackTrace(System.err);
                stop = false;
            } catch (InputMismatchException e) {
                e.printStackTrace(System.err);
                stop = false;
                kbd.nextLine();
            }
        } while (!stop);
    }
    private static int menu(Scanner teclado) throws NumberOutOfRange {
        System.out.println(" Menú de Agenda ");
        System.out.println("--------------------------");
        System.out.println("1.- Cargar Fichero Agenda");
        System.out.println("2.- Guardar Fichero Agenda");
        System.out.println("3.- Buscar Nombre");
        System.out.println("4.- Insertar Nuevo Nombre");
        System.out.println("5.- Eliminar Nombre");
        System.out.println("0.- Salir");
        System.out.print("Seleccione [0..5]: ");
        int opcion = teclado.nextInt();
        if (opcion < 0 || opcion > 5)
            throw new NumberOutOfRange(opcion + " is not valid!");
        return opcion;
    }
}
