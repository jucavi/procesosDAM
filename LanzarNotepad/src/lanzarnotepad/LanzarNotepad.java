/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lanzarnotepad;

import java.io.IOException;

/**
 *
 * @author Juan Carlos Vilaruubia
 */
public class LanzarNotepad {

    /**
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        if (args.length == 2) {

            String command = args[0];
            String filename = args[1];
            ProcessBuilder pb = new ProcessBuilder(command, filename);

            try {
                pb.start();
            } catch (IOException ex) {
                System.out.println("No es posible ejecutar el programa:" + command);
            }
        } else {
            System.out.println("Usage: java -jar LanzarNotepad.jar <editor> <filename>");
        }

        lanzarProcesos();
    }

    public static void lanzarProcesos() {

        ProcessBuilder notepadPB = new ProcessBuilder("notepad.exe");
        ProcessBuilder writePB = new ProcessBuilder("write.exe");

        try {
            Process notepadProc = notepadPB.start();
            /* Descomentar la siguiente linea si queremos lanzar WordPad 10s
               despues de cerrar Notepad
             */
            // notepadProc.waitFor();
            Thread.sleep(10000);
        } catch (IOException ex) {
            System.out.println("No es posible ejecutar el programa:" + notepadPB);
        } catch (InterruptedException ex) {
            System.out.println("Hilo de programa interrumpido por otro hilo mientras esperaba.");
        }

        try {
            writePB.start();
        } catch (IOException ex) {
            System.out.println("No es posible ejecutar el programa:" + notepadPB);
        }

    }
}
