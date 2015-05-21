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
public abstract class PRNode {

    public PRNode(PositionEnum position, PRNode father) {
        this.level = father == null ? 0 : father.getLevel() + 1;
        this.position = position;
        this.father = father;
    }
    
    protected int level;
    protected PositionEnum position;
    protected PRNode father;

    public int getLevel() {
        return level;
    }
 
}