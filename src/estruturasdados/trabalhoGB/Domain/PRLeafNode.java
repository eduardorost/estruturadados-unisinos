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
import java.text.DateFormat;
import org.json.JSONException;
import org.json.JSONStringer;

/**
 *
 * @author rosted
 */
public class PRLeafNode extends PRNode {

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
    public JsonElement toJson() {
        JsonParser parser = new JsonParser();
        return parser.parse(getJSONStringer());
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

    private String getJSONStringer() throws JSONException {
        String json = new JSONStringer()
                .object()
                .key("position").value(position)
                .key("level").value(level)
                .key("x").value(x)
                .key("y").value(y)
                .key("rgb").value(rgb)
                .endObject()
                .toString();
        return json;
    }

}
