// Nós (Eduardo Rost, Fabiano Menegussi, Renan Santos), garantimos que:
//
// - Não utilizamos código fonte obtidos de outros estudantes,
// ou fonte não autorizada, seja modificado ou cópia literal.
// - Todo código usado em nosso trabalho ´e resultado do nosso
// trabalho original, ou foi derivado de um
// código publicado nos livros texto desta disciplina.
// - Temos total ciência das consequências em caso de violarmos estes termos.
package estruturasdados.trabalhoGB.Domain;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import estruturasdados.trabalhoGB.Helpers.PRLeafNodeTypeAdapter;
import java.awt.Color;

public class PRLeafNode extends PRNode {

    public PRLeafNode(int x, int y, int rgb) {
        super();

        this.x = x;
        this.y = y;
        this.rgb = rgb;
        this.color = new Color(rgb);
    }

    public PRLeafNode(int x, int y, int rgb, PositionEnum position) {
        super();

        this.x = x;
        this.y = y;
        this.rgb = rgb;
        this.color = new Color(rgb);
        this.position = position;
    }

    private int x;
    private int y;
    private int rgb;
    private Color color;

    public int distanceColor(Color c) {
        int r = (int) Math.pow(color.getRed() - c.getRed(), 2);
        int g = (int) Math.pow(color.getGreen() - c.getGreen(), 2);
        int b = (int) Math.pow(color.getBlue() - c.getBlue(), 2);

        return (int) Math.sqrt(r + g + b);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRgb() {
        return rgb;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toJson() {
        Gson gson = new GsonBuilder()
                .enableComplexMapKeySerialization()
                .serializeNulls()
                .registerTypeAdapter(PRLeafNode.class, new PRLeafNodeTypeAdapter())
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        return gson.toJson(this);
    }

    @Override
    public PRLeafNode clone() {
        return new PRLeafNode(x, y, rgb, position);
    }

}
