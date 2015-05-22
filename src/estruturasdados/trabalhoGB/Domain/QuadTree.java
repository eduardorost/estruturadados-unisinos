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
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.nio.file.Paths;
import javax.swing.text.AbstractDocument;

/**
 *
 * @author rosted
 */
public class QuadTree {

    public QuadTree(int[] pixels, int width, int height) {
        this.width = width;
        this.height = height;

        if (pixels.length == 0) {
            this.root = new PRLeafNode(0, 0, pixels[0], PositionEnum.ROOT, null);
            return;
        }

        int[] w = {0, this.width};
        int[] h = {0, this.height};
        this.root = new PRInternalNode(this.width, this.height, w, h, PositionEnum.ROOT, null);

        //PARA TESTE
        File directory = new File(Paths.get(System.getProperty("user.dir"), "json").toString());

        if (!directory.exists()) {
            directory.mkdir();
        } else {
            for (File file : directory.listFiles()) {
                file.delete();
            }
        }

        for (int i = 0; i < pixels.length; i++) {
            //coluna
            int x = i % width;
            //linha
            int y = Math.floorDiv(i, height);
            System.out.println(i);

            ((PRInternalNode) root).insert(new PRLeafNode(x, y, pixels[i], PositionEnum.ROOT, null));

            //PARA TESTE
            try {
                //write converted json data to a file named "file.json"
                File file = new File(Paths.get(directory.getAbsolutePath(), "etapa" + i + ".json").toString());

                if (!file.exists()) {
                    file.createNewFile();
                }
                
                FileWriter writer = new FileWriter(file);
                writer.write(this.toJson());
                writer.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private int width;
    private int height;
    private PRNode root;

    
    public String toJson() {
        JsonParser parser = new JsonParser();
        Gson gson = new GsonBuilder()
                .enableComplexMapKeySerialization()
                .serializeNulls()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        JsonElement el = null;
        if(root instanceof PRLeafNode)
            el = parser.parse(((PRLeafNode) root).toJsonString());
        else
            el = parser.parse(((PRInternalNode) root).toJsonString());
        return gson.toJson(el);
    }
    
    
}
