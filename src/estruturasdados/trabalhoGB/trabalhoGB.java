/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estruturasdados.trabalhoGB;

import estruturasdados.trabalhoGB.Helpers.FileHelper;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rosted
 */
public class trabalhoGB {

    public static void main(String[] args) {
        FileHelper fh = new FileHelper();

        File input = fh.getFile();
        List<String> lines = null;
        boolean validFile = false;

        do {
            try {
                lines = Files.readAllLines(Paths.get(input.getAbsolutePath()));
            } catch (IOException ex) {
                Logger.getLogger(trabalhoGB.class.getName()).log(Level.SEVERE, null, ex);
            }

            if ("P3".equals(lines.remove(0).toUpperCase())) {
                validFile = true;
            } else {
                System.out.println("Arquivo incorreto! Tente novamente.");
                input = fh.getFile();
            }
        } while (!validFile);

        for (String line : lines) {
            System.out.println(line);
        }
    }
}
