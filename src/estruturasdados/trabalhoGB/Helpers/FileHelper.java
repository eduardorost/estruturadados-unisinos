/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estruturasdados.trabalhoGB.Helpers;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 *
 * @author rosted
 */
public class FileHelper {
    
    public File getFile() {
        Scanner in = new Scanner(System.in);
        System.out.print("Digite o endereço do arquivo: ");
        String path = in.next();

        boolean exists;
        do {
            if (path.equals("E")) {
                System.exit(0);
            }

            exists = Files.exists(Paths.get(path));
            if (!exists) {
                System.out.print("Caminho incorreto, digite o endereço do arquivo novamente ou 'E' para sair: ");
                path = in.next();
            }
        } while (!exists);
        return new File(path);
    } 
    
}
