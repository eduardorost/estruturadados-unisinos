/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estruturasdados.trabalhoGB.Domain;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import estruturasdados.trabalhoGB.Helpers.PRInternalNodeTypeAdapter;
import estruturasdados.trabalhoGB.Helpers.PRLeafNodeTypeAdapter;

/**
 *
 * @author rosted
 */
public class PRInternalNode extends PRNode {

    public PRInternalNode(int[] marginW, int[] marginH, PRNode father) {
        super();

        this.father = father;
        this.level = father == null ? 0 : father.getLevel() + 1;
        this.marginW = marginW;
        this.marginH = marginH;
        this.width = (marginW[1] - marginW[0]) + 1;
        this.height = (marginH[1] - marginH[0]) + 1;
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

    public void insert(PRLeafNode node) {
        switch (this.getPositionNode(node)) {
            case NW:
                if (nwChild == null) {
                    nwChild = node;
                    node.position = PositionEnum.NW;
                    node.father = this;
                    node.level = this.level + 1;
                } else {
                    if (nwChild instanceof PRInternalNode) {
                        ((PRInternalNode) nwChild).insert(node);
                    } else {
                        int[] w = {this.marginW[0], (this.marginW[0] + (this.width / 2) - 1)};
                        int[] h = {this.marginH[0], (this.marginH[0] + (this.height / 2) - 1)};

                        PRInternalNode internalNode = new PRInternalNode(w, h, this);
                        internalNode.insert((PRLeafNode) nwChild);
                        internalNode.insert(node);
                        nwChild = internalNode;
                    }
                }
                break;
            case NE:
                if (neChild == null) {
                    neChild = node;
                    node.position = PositionEnum.NE;
                    node.father = this;
                    node.level = this.level + 1;
                } else {
                    if (neChild instanceof PRInternalNode) {
                        ((PRInternalNode) neChild).insert(node);
                    } else {
                        int[] w = {this.marginW[1] - ((this.width / 2) - 1), this.marginW[1]};
                        int[] h = {this.marginH[0], (this.marginH[0] + (this.height / 2) - 1)};

                        PRInternalNode internalNode = new PRInternalNode(w, h, this);
                        internalNode.insert((PRLeafNode) neChild);
                        internalNode.insert(node);
                        neChild = internalNode;
                    }
                }
                break;
            case SW:
                if (swChild == null) {
                    swChild = node;
                    node.position = PositionEnum.SW;
                    node.father = this;
                    node.level = this.level + 1;
                } else {
                    if (swChild instanceof PRInternalNode) {
                        ((PRInternalNode) swChild).insert(node);
                    } else {
                        int[] w = {this.marginW[0], (this.marginW[0] + (this.width / 2) - 1)};
                        int[] h = {this.marginH[1] - ((this.height / 2) - 1), this.marginH[1]};

                        PRInternalNode internalNode = new PRInternalNode(w, h, this);
                        internalNode.insert((PRLeafNode) swChild);
                        internalNode.insert(node);
                        swChild = internalNode;
                    }
                }
                break;
            case SE:
                if (seChild == null) {
                    seChild = node;
                    node.position = PositionEnum.SE;
                    node.father = this;
                    node.level = this.level + 1;
                } else {
                    if (seChild instanceof PRInternalNode) {
                        ((PRInternalNode) seChild).insert(node);
                    } else {
                        int[] w = {this.marginW[1] - ((this.width / 2) - 1), this.marginW[1]};
                        int[] h = {this.marginH[1] - ((this.height / 2) - 1), this.marginH[1]};

                        PRInternalNode internalNode = new PRInternalNode(w, h, this);
                        internalNode.insert((PRLeafNode) seChild);
                        internalNode.insert(node);
                        seChild = internalNode;
                    }
                }
                break;
        }
    }

    private PositionEnum getPositionNode(PRLeafNode node) {
        if (node.getX() >= (this.marginW[0] + this.marginW[1] + 1) / 2) {
            if (node.getY() >= (this.marginH[0] + this.marginH[1] + 1) / 2) {
                return PositionEnum.SE;
            } else {
                return PositionEnum.NE;
            }
        } else {
            if (node.getY() >= (this.marginH[0] + this.marginH[1] + 1) / 2) {
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
                .registerTypeAdapter(PRLeafNode.class, new PRLeafNodeTypeAdapter())
                .registerTypeAdapter(PRInternalNode.class, new PRInternalNodeTypeAdapter())
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        return gson.toJson(this);
    }
    

    public PRInternalNode CompressChilds(int compressionRate) {
        throw new UnsupportedOperationException();
    }

    public PRNode getNwChild() {
        return nwChild;
    }

    public PRNode getNeChild() {
        return neChild;
    }

    public PRNode getSwChild() {
        return swChild;
    }

    public PRNode getSeChild() {
        return seChild;
    }

    public int[] getMarginW() {
        return marginW;
    }

    public int[] getMarginH() {
        return marginH;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

}
