/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estruturasdados.trabalhoGB.Helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import estruturasdados.trabalhoGB.Domain.PRInternalNode;
import estruturasdados.trabalhoGB.Domain.PRLeafNode;
import java.io.IOException;

/**
 *
 * @author rosted
 */
public class PRInternalNodeTypeAdapter extends TypeAdapter<PRInternalNode> {

    @Override
    public void write(JsonWriter writer, PRInternalNode node) throws IOException {
        Gson gson = new GsonBuilder()
        .registerTypeAdapter(PRLeafNode.class, new PRLeafNodeTypeAdapter())
        .registerTypeAdapter(PRInternalNode.class, new PRInternalNodeTypeAdapter())
        .create();
        
        
        writer.beginObject();
            writer.name("id").value(node.getId());
            writer.name("level").value(node.getLevel());
            writer.name("position").value(node.getPosition() == null ? null : node.getPosition().toString());
            writer.name("father").value(node.getFather() == null ? null : node.getFather().getId());

            writer.name("nwChild");
            if(node.getNwChild() != null)
                gson.toJson(node.getNwChild(), node.getNwChild().getClass(), writer);
            else
                writer.value("");
            
            writer.name("neChild");
            if(node.getNeChild() != null)
                gson.toJson(node.getNeChild(), node.getNeChild().getClass(), writer);
            else
                writer.value("");
            
            writer.name("swChild");
            if(node.getSwChild() != null)
                gson.toJson(node.getSwChild(), node.getSwChild().getClass(), writer);
            else
                writer.value("");
            
            writer.name("seChild");
            if(node.getSeChild() != null)
                gson.toJson(node.getSeChild(), node.getSeChild().getClass(), writer);
            else
                writer.value("");
            
            writer.name("marginW").value("[ " + node.getMarginW()[0] + ", " + node.getMarginW()[1] + " ]");
            writer.name("marginH").value("[ " + node.getMarginH()[0] + ", " + node.getMarginH()[1] + " ]");
            writer.name("width").value(node.getWidth());
            writer.name("height").value(node.getHeight());
        writer.endObject();

    }

    @Override
    public PRInternalNode read(JsonReader reader) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
