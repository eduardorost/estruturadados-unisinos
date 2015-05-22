/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estruturasdados.trabalhoGB.Domain;

import estruturasdados.trabalhoGB.Helpers.FatherExlusionStrategy;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 *
 * @author rosted
 */
public class PRInternalNode extends PRNode {

    public PRInternalNode(int width, int height, int[] marginW, int[] marginH) {
        this.marginW = marginW;
        this.marginH = marginH;
        this.width = width;
        this.height = height;
    }

    //LEMBRAR QUE ESSES FILHOS PODEM SER INTERNOS OU FOLHAS, FAZER O TYPEOF PARA IDENTIFICAR QUAL TIPO ELE É.
    private PRNode nwChild;
    private PRNode neChild;
    private PRNode swChild;
    private PRNode seChild;

    //Margens do quadrante
    private int[] marginW;
    private int[] marginH;
    
    private int height;
    private int width;

    public void insert(PRLeafNode node, PRNode father) {
        switch (this.getPositionNode(node)) {
            case NW:
                if (nwChild == null) {
                    nwChild = node;
                    node.position = PositionEnum.NW;
                    node.father = father;
                    node.level = father == null ? 0 : father.getLevel() + 1;
                } else {
                    if (nwChild instanceof PRInternalNode) {
                        ((PRInternalNode) nwChild).insert(node, this);
                    } else {
                        //EXEMPLOS ESTÃO TODOS DIVISIVEIS POR 2.
                        int[] w = {this.marginW[0], Math.floorDiv(this.marginW[1], 2)};
                        int[] h = {this.marginH[0], Math.floorDiv(this.marginH[1], 2)};

                        PRInternalNode internalNode = new PRInternalNode(this.width / 2, this.height / 2, w, h);
                        internalNode.insert((PRLeafNode) nwChild, this);
                        internalNode.insert(node, this);
                        nwChild = internalNode;
                    }
                }
                break;
            case NE:
                if (neChild == null) {
                    neChild = node;
                    node.position = PositionEnum.NE;
                    node.father = father;
                    node.level = father == null ? 0 : father.getLevel() + 1;
                } else {
                    if (neChild instanceof PRInternalNode) {
                        ((PRInternalNode) neChild).insert(node, this);
                    } else {
                        //EXEMPLOS ESTÃO TODOS DIVISIVEIS POR 2.
                        int[] w = {Math.floorDiv(this.marginW[1], 2) + 1, this.marginW[1]};
                        int[] h = {this.marginH[0], Math.floorDiv(this.marginH[1], 2)};

                        PRInternalNode internalNode = new PRInternalNode(this.width / 2, this.height / 2, w, h);
                        internalNode.insert((PRLeafNode) neChild, this);
                        internalNode.insert(node, this);
                        neChild = internalNode;
                    }
                }
                break;
            case SW:
                if (swChild == null) {
                    swChild = node;
                    node.position = PositionEnum.SW;
                    node.father = father;
                    node.level = father == null ? 0 : father.getLevel() + 1;
                } else {
                    if (swChild instanceof PRInternalNode) {
                        ((PRInternalNode) swChild).insert(node, this);
                    } else {
                        //EXEMPLOS ESTÃO TODOS DIVISIVEIS POR 2.
                        int[] w = {this.marginW[0], Math.floorDiv(this.marginW[1], 2)};
                        int[] h = {Math.floorDiv(this.marginH[1], 2) + 1, this.marginH[1]};

                        PRInternalNode internalNode = new PRInternalNode(this.width / 2, this.height / 2, w, h);
                        internalNode.insert((PRLeafNode) swChild, this);
                        internalNode.insert(node, this);
                        swChild = internalNode;
                    }
                }
                break;
            case SE:
                if (seChild == null) {
                    seChild = node;
                    node.position = PositionEnum.SE;
                    node.father = father;
                    node.level = father == null ? 0 : father.getLevel() + 1;
                } else {
                    if (seChild instanceof PRInternalNode) {
                        ((PRInternalNode) seChild).insert(node, this);
                    } else {
                        //EXEMPLOS ESTÃO TODOS DIVISIVEIS POR 2.
                        int[] w = {Math.floorDiv(this.marginW[1], 2) + 1, this.marginW[1]};
                        int[] h = {Math.floorDiv(this.marginH[1], 2) + 1, this.marginH[1]};

                        PRInternalNode internalNode = new PRInternalNode(this.width / 2, this.height / 2, w, h);
                        internalNode.insert((PRLeafNode) seChild, this);
                        internalNode.insert(node, this);
                        seChild = internalNode;
                    }
                }
                break;
        }
    }

    private PositionEnum getPositionNode(PRLeafNode node) {
        if (node.getX() >= (this.marginW[0] + this.marginW[1]) / 2) {
            if (node.getY() >= (this.marginH[0] + this.marginH[1]) / 2) {
                return PositionEnum.SE;
            } else {
                return PositionEnum.NE;
            }
        } else {
            if (node.getY() >= (this.marginH[0] + this.marginH[1]) / 2) {
                return PositionEnum.SW;
            } else {
                return PositionEnum.NW;
            }
        }
    }

    @Override
    public String toJson() {        
        Gson gson = new GsonBuilder()
                .enableComplexMapKeySerialization()
                .serializeNulls()
                .setExclusionStrategies(new FatherExlusionStrategy())
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        return gson.toJson(this);
    }

}
