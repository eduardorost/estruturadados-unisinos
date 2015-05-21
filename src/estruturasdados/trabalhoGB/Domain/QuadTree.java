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

    public QuadTree(int[] pixels, int width, int height) {
        this.width = width;
        this.height = height;

        if (pixels.length == 0) {
            this.root = new PRLeafNode(0, 0, pixels[0], PositionEnum.ROOT, null);
            return;
        }

        int[] w = {0, this.width};
        int[] h = {0, this.height};
        this.root = new PRInternalNode(this.width, this.height, w, h, PositionEnum.ROOT, null);

        for (int i = 0; i < pixels.length; i++) {
            //coluna
            int x = i % width;
            //linha
            int y = Math.floorDiv(i, height);

            ((PRInternalNode) root).insert(new PRLeafNode(x, y, pixels[i], PositionEnum.ROOT, null));
        }
    }

    private int width;
    private int height;
    private PRNode root;
}
