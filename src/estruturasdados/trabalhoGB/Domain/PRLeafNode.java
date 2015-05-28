/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estruturasdados.trabalhoGB.Domain;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import estruturasdados.trabalhoGB.Helpers.PRInternalNodeTypeAdapter;
import estruturasdados.trabalhoGB.Helpers.PRLeafNodeTypeAdapter;
import java.io.IOException;

/**
 *
 * @author rosted
 */
public class PRLeafNode extends PRNode {

    public PRLeafNode(int x, int y, int rgb) {
        super();

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

    public int getRgb() {
        return rgb;
    }

    @Override
    public String toJson() {
        Gson gson = new GsonBuilder()
                .enableComplexMapKeySerialization()
                .serializeNulls()                
                .registerTypeAdapter(PRLeafNode.class, new PRLeafNodeTypeAdapter())
                //.registerTypeAdapter(PRInternalNode.class, new PRInternalNodeTypeAdapter())
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        return gson.toJson(this);
    }
    

}
