/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estruturasdados.trabalhoGB;

import br.unisinos.imagepanel.ImagePanel;
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
        ImagePanel imagePanel = new ImagePanel();

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

        String[] size = lines.remove(0).split(" ");
        int width = Integer.valueOf(size[0]);
        int height = Integer.valueOf(size[1]);

        int[] pixels = new int[width * height];

        int i = 0;
        for (String line : lines) {
            for (String rgb : line.split(" ")) {
                pixels[i] = Integer.valueOf(rgb);
            }
        }
        
        imagePanel.update(pixels, width, height);
        //TODO: FAZER PRINTAR O PANEL!
    }
}
