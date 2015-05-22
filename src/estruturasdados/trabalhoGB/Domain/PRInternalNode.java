/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estruturasdados.trabalhoGB.Domain;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.json.JSONException;
import org.json.JSONStringer;

/**
 *
 * @author rosted
 */
public class PRInternalNode extends PRNode {

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

    //de onde até onde vai a largura deste internal node
    private int[] marginW;
    //de onde até onde vai a altura deste internal node
    private int[] marginH;
    private int height;
    private int width;

    //@Override
    //public void insert(PRNode node) {
    //@Override
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
        //DIVIDIR EM QUADRANTES
        //int compW = this.position == PositionEnum.NW || this.position == PositionEnum.SW ? this.marginW[0]

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
    public String toJsonString() {        
        JsonParser parser = new JsonParser();
        Gson gson = new GsonBuilder()
                .enableComplexMapKeySerialization()
                .serializeNulls()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        JsonElement el = parser.parse(getJSONStringer());
        return gson.toJson(el);
    }
    
    @Override
    public JsonElement toJson() {
        JsonParser parser = new JsonParser();
        return parser.parse(getJSONStringer());
    }

    private String getJSONStringer() throws JSONException {
        String json = new JSONStringer()
                .object()
                .key("position").value(position)
                .key("level").value(level)
                .key("marginH").value("["+ marginW[0] + ", " + marginW[1] +"]")
                .key("marginW").value("["+ marginH[0] + ", " + marginH[1] +"]")
                .key("height").value(height)
                .key("width").value(width)
                .key("nwChild").value(nwChild == null ? null : nwChild.toJson())
                .key("neChild").value(neChild == null ? null : neChild.toJson())
                .key("swChild").value(swChild == null ? null : swChild.toJson())
                .key("seChild").value(seChild == null ? null : seChild.toJson())
                .endObject()
                .toString();
        return json;
    }

}
