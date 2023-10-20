/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejercicio4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 *
 * @author jcarv
 */
public class Ejercicio4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String strPath = getInput("Introduzca la ruta donde desea crear la nueva carpeta: ");
        Path parentPath = absPath(strPath);
        if (Files.exists(parentPath) && Files.isDirectory(parentPath)) {
            listDir(parentPath);

            String strNewFolder = getInput("Introdusca el nombre de la carpeta que desea crear: ");
            Path folderPath = Paths.get(parentPath.toString(), strNewFolder);

            if (!Files.exists(folderPath)) {
                mkdir(folderPath);
            } else {
                System.out.println("La carpeta ya existe o el nombre no es válido.");
            }

        } else {
            System.out.println("La ruta no existe o no es un directorio: " + parentPath.toString());
        }
    }

    public static String getInput(String msg) {
        Scanner sc = new Scanner(System.in, "ISO-8859-1");

        System.out.print(msg);
        String in = sc.nextLine();

        if (in.endsWith(":")) {
            in = in.concat("\\");
        }
        return in;
    }

    public static Path absPath(String path) {
        Path absPath = Paths.get(path);

        if (!absPath.isAbsolute()) {
            return Paths.get(System.getProperty("user.dir"), absPath.toString());
        }

        return absPath;
    }

    public static void listDir(Path path) {

        String osName = System.getProperty("os.name");
        String[] listCmd;

        if (osName.toUpperCase().contains("WIN")) {
            listCmd = new String[]{"cmd.exe", "/c", "dir", path.toString()};
        } else {
            listCmd = new String[]{"bash", "-c", "ls -la " + path.toString()};
        }

        try {
            ProcessBuilder ps = new ProcessBuilder(listCmd);
            Process proc = ps.start();

            BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void mkdir(Path path) {
        try {
            Files.createDirectory(path);

            if (Files.exists(path)) {
                System.out.println("La carpeta ha sido creada correctamente.");
            }
        } catch (IOException ex) {
            System.out.println("No ha sido posible crear la carpeta: " + path.toString());
        }
    }
}
