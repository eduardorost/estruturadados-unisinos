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
public class PRNode {

    private PRNode nwChild;

    public PRNode(int x, int y, int rgb) {
        this.x = x;
        this.y = y;
        this.rgb = rgb;
    }

    private PRNode neChild;
    private PRNode swChild;
    private PRNode seChild;
    private PositionEnum position;
    private int x;
    private int y;
    private int rgb;

    public void insert(PRNode node) {

    }
}
