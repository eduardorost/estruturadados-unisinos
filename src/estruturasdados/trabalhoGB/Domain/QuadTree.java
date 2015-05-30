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
import javax.swing.JOptionPane;

/**
 *
 * @author rosted
 */
public class QuadTree {

    public QuadTree(int[] pixels, int width, int height, int compressionRate) {
        this.width = Math.max(width, height);
        this.height = Math.max(width, height);

        if (pixels.length == 0) {
            this.root = new PRLeafNode(0, 0, pixels[0]);
            return;
        }

        int[] w = {0, this.width - 1};
        int[] h = {0, this.height - 1};
        this.root = new PRInternalNode(w, h, null);

        for (int i = 0; i < pixels.length; i++) {
            //coluna
            int x = i % width;
            //linha
            int y = Math.floorDiv(i, height);

            ((PRInternalNode) root).insert(new PRLeafNode(x, y, pixels[i]));
        }

        this.compressedRoot = this.root instanceof PRLeafNode ? this.root : ((PRInternalNode) root).CompressChilds(compressionRate);

        try {
            File directory = new File(Paths.get(System.getProperty("user.dir"), "json").toString());

            if (!directory.exists()) {
                directory.mkdir();
            } else {
                for (File file : directory.listFiles()) {
                    file.delete();
                }
            }
            File file = new File(Paths.get(directory.getAbsolutePath(), "quadtree.json").toString());
            File compressedFile = new File(Paths.get(directory.getAbsolutePath(), "quadtree_compressed.json").toString());

            if (!file.exists()) {
                file.createNewFile();
            }
            if (!compressedFile.exists()) {
                file.createNewFile();
            }
            String json = this.toJson();
            String compressedJson = this.compressedRoot.toJson();
            FileWriter writer = new FileWriter(file);
            FileWriter compressedWriter = new FileWriter(compressedFile);
            writer.write(json);
            compressedWriter.write(compressedJson);
            
            //TODO: Fazer um to string, inviavel apresentar o JSON
            //JOptionPane.showMessageDialog(null, json);
            //JOptionPane.showMessageDialog(null, compressedJson);
            writer.close();
            compressedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
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
