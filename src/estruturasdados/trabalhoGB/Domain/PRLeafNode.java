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
import com.google.gson.JsonSerializationContext;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.text.DateFormat;
import org.json.JSONException;
import org.json.JSONStringer;

/**
 *
 * @author rosted
 */
public class PRLeafNode extends PRNode implements Serializable {

    public PRLeafNode(int x, int y, int rgb, PositionEnum pos, PRNode father) {
        super(pos, father);

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
