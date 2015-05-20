/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estruturasdados.trabalhoGB.Domain;

/**
 *
 * @author rosted
 */
public class QuadTree {

    public QuadTree(int[] pixels, int w, int h) {
        this.width = w;
        this.height = h;
        this.root = new PRInternalNode();

        for (int i = 0; i < pixels.length; i++) {
            //coluna
            int x = i % w;
            //linha
            int y = Math.floorDiv(i, h);

            root.insert(new PRLeafNode(x, y, pixels[i]));
        }
    }

    private int width;
    private int height;
    private PRInternalNode root;
}