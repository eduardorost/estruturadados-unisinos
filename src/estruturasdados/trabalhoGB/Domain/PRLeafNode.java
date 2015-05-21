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
    public PRLeafNode(int x, int y, int rgb, PositionEnum pos, PRNode father) {
        super(pos, father);
        
        this.x = x;
        this.y = y;
        this.rgb = rgb;
    }

    private int x;
    private int y;
    private int rgb;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
