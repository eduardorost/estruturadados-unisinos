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
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONStringer;

/**
 *
 * @author rosted
 */
public class PRInternalNode extends PRNode implements Serializable {

    public PRInternalNode(int width, int height, int[] marginW, int[] marginH, PositionEnum pos, PRNode father) {
        super(pos, father);

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

    public void insert(PRLeafNode node) {
        switch (this.getPositionNode(node)) {
            case NW:
                if (nwChild == null) {
                    nwChild = node;
                } else {
                    if (nwChild instanceof PRInternalNode) {
                        ((PRInternalNode) nwChild).insert(node);
                    } else {
                        //EXEMPLOS ESTÃO TODOS DIVISIVEIS POR 2.
                        int[] w = {this.marginW[0], this.marginW[1] / 2};
                        int[] h = {this.marginH[0], this.marginH[1] / 2};

                        PRInternalNode internalNode = new PRInternalNode(this.width / 2, this.height / 2, w, h, PositionEnum.NW, this);
                        internalNode.insert((PRLeafNode) nwChild);
                        internalNode.insert(node);
                        nwChild = internalNode;
                    }
                }
                break;
            case NE:
                if (neChild == null) {
                    neChild = node;
                } else {
                    if (neChild instanceof PRInternalNode) {
                        ((PRInternalNode) neChild).insert(node);
                    } else {
                        //EXEMPLOS ESTÃO TODOS DIVISIVEIS POR 2.
                        int[] w = {this.marginW[1] / 2, this.marginW[1]};
                        int[] h = {this.marginH[0], this.marginH[1] / 2};

                        PRInternalNode internalNode = new PRInternalNode(this.width / 2, this.height / 2, w, h, PositionEnum.NE, this);
                        internalNode.insert((PRLeafNode) neChild);
                        internalNode.insert(node);
                        neChild = internalNode;
                    }
                }
                break;
            case SW:
                if (swChild == null) {
                    swChild = node;
                } else {
                    if (swChild instanceof PRInternalNode) {
                        ((PRInternalNode) swChild).insert(node);
                    } else {
                        //EXEMPLOS ESTÃO TODOS DIVISIVEIS POR 2.
                        int[] w = {this.marginW[0], this.marginW[1] / 2};
                        int[] h = {this.marginH[1] / 2, this.marginH[1]};

                        PRInternalNode internalNode = new PRInternalNode(this.width / 2, this.height / 2, w, h, PositionEnum.SW, this);
                        internalNode.insert((PRLeafNode) swChild);
                        internalNode.insert(node);
                        swChild = internalNode;
                    }
                }
                break;
            case SE:
                if (seChild == null) {
                    seChild = node;
                } else {
                    if (seChild instanceof PRInternalNode) {
                        ((PRInternalNode) seChild).insert(node);
                    } else {
                        //EXEMPLOS ESTÃO TODOS DIVISIVEIS POR 2.
                        int[] w = {this.marginW[1] / 2, this.marginW[1]};
                        int[] h = {this.marginH[1] / 2, this.marginH[1]};

                        PRInternalNode internalNode = new PRInternalNode(this.width / 2, this.height / 2, w, h, PositionEnum.SE, this);
                        internalNode.insert((PRLeafNode) seChild);
                        internalNode.insert(node);
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
