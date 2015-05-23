/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estruturasdados.trabalhoGB.Domain;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.nio.file.Paths;

/**
 *
 * @author rosted
 */
public class QuadTree {

    public QuadTree(int[] pixels, int width, int height) {
        this.width = width;
        this.height = height;

        if (pixels.length == 0) {
            this.root = new PRLeafNode(0, 0, pixels[0]);
            return;
        }

        int[] w = {0, this.width};
        int[] h = {0, this.height};
        this.root = new PRInternalNode(this.width, this.height, w, h);

        //PARA TESTE
        File directory = new File(Paths.get(System.getProperty("user.dir"), "json").toString());

        if (!directory.exists()) {
            directory.mkdir();
        } else {
            for (File file : directory.listFiles()) {
                file.delete();
            }
        }

        for (int i = 0; i < pixels.length; i++) {
            //coluna
            int x = i % width;
            //linha
            int y = Math.floorDiv(i, height);
            System.out.println(i);

            ((PRInternalNode) root).insert(new PRLeafNode(x, y, pixels[i]), null);

            //PARA TESTE
            try {
                File file = new File(Paths.get(directory.getAbsolutePath(), "etapa" + i + ".json").toString());

                if (!file.exists()) {
                    file.createNewFile();
                }
                
                FileWriter writer = new FileWriter(file);
                writer.write(this.toJson());
                writer.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private int width;
    private int height;
    //ARVORE DA IMAGEM ORIGINAL
    private PRNode root;
    //ARVORE PARA A IMAGEM COMPRENSADA
    private PRNode compressedRoot;

    
    public String toJson() {
        return root.toJson();
    }
    
}
