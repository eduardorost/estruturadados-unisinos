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
public class PRLeafNode extends PRNode {

    public PRLeafNode(int x, int y, int rgb) {

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
