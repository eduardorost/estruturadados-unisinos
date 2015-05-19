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
        
        try {
            lines = Files.readAllLines(Paths.get(input.getAbsolutePath()));
        } catch (IOException ex) {
            Logger.getLogger(trabalhoGB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (String line : lines) {
            System.out.println(line);            
        }
    }
}
