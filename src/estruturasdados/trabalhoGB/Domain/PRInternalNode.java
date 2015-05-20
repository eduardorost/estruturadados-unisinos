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
public class PRInternalNode extends Node {

    public PRInternalNode(int width, int height, PositionEnum pos) {
        this.width = width;
        this.height = height;
        this.position = pos;
    }

    //TODO: ESSES FILHOS PODEM SER INTERNOS TAMBÉM.
    private PRLeafNode nwChild;
    private PRLeafNode neChild;
    private PRLeafNode swChild;
    private PRLeafNode seChild;
    
    private int width;
    private int height;

    public void insert(PRLeafNode node) {

        //TODO: Fazer a lógica de inserção de novo nodo.
    }

    private PositionEnum getPositionNode() {
        
        //TODO: Fazer a lógica para identificar qual posição inserir.
        PositionEnum pos = null;
        return pos;
    }
}
