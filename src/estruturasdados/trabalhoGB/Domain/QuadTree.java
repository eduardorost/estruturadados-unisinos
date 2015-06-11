// Nós (Eduardo Rost, Fabiano Menegussi, Renan Santos), garantimos que:
//
// - Não utilizamos código fonte obtidos de outros estudantes,
// ou fonte não autorizada, seja modificado ou cópia literal.
// - Todo código usado em nosso trabalho ´e resultado do nosso
// trabalho original, ou foi derivado de um
// código publicado nos livros texto desta disciplina.
// - Temos total ciência das consequências em caso de violarmos estes termos.
package estruturasdados.trabalhoGB.Domain;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;


public class QuadTree {

    public QuadTree(int[] pixels, int width, int height, int compressionRate) {
        this.width = Math.max(width, height);
        this.height = Math.max(width, height);
        this.originalWidth = width;
        this.originalHeight = height;

        if (pixels.length == 0) {
            this.root = new PRLeafNode(0, 0, pixels[0]);
            return;
        }

        int[] w = {0, this.width - 1};
        int[] h = {0, this.height - 1};
        
        System.out.println(new SimpleDateFormat("dd/MM/yyyy hh:mm ").format(new Date()) + " Montando QUADTREE");
        
        this.root = new PRInternalNode(w, h, null);

        for (int i = 0; i < pixels.length; i++) {
            //coluna
            int x = i % originalWidth;
            //linha
            int y = Math.floorDiv(i, originalWidth);

            ((PRInternalNode) root).insert(new PRLeafNode(x, y, pixels[i]));
        }

        System.out.println(new SimpleDateFormat("dd/MM/yyyy hh:mm ").format(new Date()) + " Gravando JSON Imagem Original");
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
            
            String json = this.toJson();
            FileWriter writer = new FileWriter(file);
            writer.write(json);
            
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        compressImage(compressionRate);

    }

    private int width;
    private int height;
    private int originalWidth;
    private int originalHeight;

    //ARVORE DA IMAGEM ORIGINAL
    private PRNode root;

    //ARVORE PARA A IMAGEM COMPRENSADA
    private PRNode compressedRoot;

    public String toJson() {
        return root.toJson();
    }

    public void compressImage(int compressionRate) {
        System.out.println(new SimpleDateFormat("dd/MM/yyyy hh:mm ").format(new Date()) + " Comprimindo QUADTREE");
        this.compressedRoot = this.root instanceof PRLeafNode ? this.root : ((PRInternalNode) root).CompressChilds(compressionRate);
        System.out.println(new SimpleDateFormat("dd/MM/yyyy hh:mm ").format(new Date()) + " Gravando JSON Imagem Comprimida");
        try {
            File directory = new File(Paths.get(System.getProperty("user.dir"), "json").toString());

            if (!directory.exists()) {
                directory.mkdir();
            }
            File compressedFile = new File(Paths.get(directory.getAbsolutePath(), "quadtree_compressed.json").toString());
            
            String compressedJson = this.compressedRoot.toJson();
            FileWriter compressedWriter = new FileWriter(compressedFile);
            compressedWriter.write(compressedJson);
            compressedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int[] getCompressedArray() {
        if (compressedRoot instanceof PRInternalNode) {
            PRInternalNode node = (PRInternalNode) compressedRoot;
            return node.getCompressedArray(originalWidth, originalHeight);
        } else {
            return new int[]{((PRLeafNode) compressedRoot).getRgb()};
        }
    }

    public int getOriginalWidth() {
        return originalWidth;
    }

    public int getOriginalHeight() {
        return originalHeight;
    }
    
}
