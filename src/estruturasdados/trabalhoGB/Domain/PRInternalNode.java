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
public class PRInternalNode extends PRNode {

    public PRInternalNode(int width, int height, PositionEnum pos) {
        this.width = width;
        this.height = height;
        this.position = pos;
    }

    //LEMBRAR QUE ESSES FILHOS PODEM SER INTERNOS OU FOLHAS, FAZER O TYPEOF PARA IDENTIFICAR QUAL TIPO ELE É.
    private PRNode nwChild;
    private PRNode neChild;
    private PRNode swChild;
    private PRNode seChild;
    
    private int width;
    private int height;

    //@Override
    //public void insert(PRNode node) {
    //@Override
    public void insert(PRLeafNode node) {
        switch (this.getPositionNode(node))
        {
            case NW:
                if(nwChild == null)
                {
                    nwChild = node;
                }
                else
                {
                    //EXEMPLOS ESTÃO TODOS DIVISIVEIS POR 2.
                    PRInternalNode internalNode = new PRInternalNode(this.width /2, this.height/2, PositionEnum.NW);
                    internalNode.insert((PRLeafNode) nwChild);
                    internalNode.insert(node);
                    nwChild = internalNode;
                }
                break;
            case NE:
                if(neChild == null)
                {
                    neChild = node;
                }
                else
                {
                    //EXEMPLOS ESTÃO TODOS DIVISIVEIS POR 2.
                    PRInternalNode internalNode = new PRInternalNode(this.width /2, this.height/2, PositionEnum.NE);
                    internalNode.insert((PRLeafNode) neChild);
                    internalNode.insert(node);
                    neChild = internalNode;
                }
                break;
            case SW:
                if(swChild == null)
                {
                    swChild = node;
                }
                else
                {
                    //EXEMPLOS ESTÃO TODOS DIVISIVEIS POR 2.
                    PRInternalNode internalNode = new PRInternalNode(this.width /2, this.height/2, PositionEnum.SW);
                    internalNode.insert((PRLeafNode) swChild);
                    internalNode.insert(node);
                    swChild = internalNode;
                }
                break;
            case SE:
                if(seChild == null)
                {
                    seChild = node;
                }
                else
                {
                    //EXEMPLOS ESTÃO TODOS DIVISIVEIS POR 2.
                    PRInternalNode internalNode = new PRInternalNode(this.width /2, this.height/2, PositionEnum.SE);
                    internalNode.insert((PRLeafNode) seChild);
                    internalNode.insert(node);
                    seChild = internalNode;
                }
                break;
        }
    }

    private PositionEnum getPositionNode(PRLeafNode node) {
        //int w = (width / 2);
        //int h = (height / 2);
        
        if(node.getX() >= width)
        {
            if(node.getY() >= height)
                return PositionEnum.SE;
            else
                return PositionEnum.NE;
        }
        else
        {            
            if(node.getY() >= height)
                return PositionEnum.SW;
            else
                return PositionEnum.NW;
        }
    }
}
