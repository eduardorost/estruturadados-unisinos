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
public class PRLeafNode extends PRNode {
    public PRLeafNode(int x, int y, int rgb) {
        this.x = x;
        this.y = y;
        this.rgb = rgb;
    }

    private PRInternalNode father;
    private int x;
    private int y;
    private int rgb;

    //@Override
    //public void insert(PRNode nodo) {
    //}

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
