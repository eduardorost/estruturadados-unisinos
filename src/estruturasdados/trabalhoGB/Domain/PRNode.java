// Nós (Eduardo Rost, Fabiano Menegussi, Renan Santos), garantimos que:
//
// - Não utilizamos código fonte obtidos de outros estudantes,
// ou fonte não autorizada, seja modificado ou cópia literal.
// - Todo código usado em nosso trabalho ´e resultado do nosso
// trabalho original, ou foi derivado de um
// código publicado nos livros texto desta disciplina.
// - Temos total ciência das consequências em caso de violarmos estes termos.
package estruturasdados.trabalhoGB.Domain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rosted
 */
public abstract class PRNode {

    public PRNode() {
        Properties prop = new Properties();
        String path = Paths.get(System.getProperty("user.dir"), "config.properties").toString();
        try {
            prop.load(new FileInputStream(path));
            try {
                this.id = Integer.valueOf(prop.getProperty("nodeId", "1"));
            } catch (Exception e) {
                this.id = 1;
            }
            prop.setProperty("nodeId", String.valueOf(this.id + 1));

        } catch (IOException e) {
            try {
                File file = new File(path);
                file.createNewFile();
                prop.setProperty("nodeId", "1");
            } catch (IOException ex) {
                Logger.getLogger(PRNode.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        try {
            prop.store(new FileOutputStream(path), null);
        } catch (Exception ex) {
            Logger.getLogger(PRNode.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected int id;
    protected int level;
    protected PositionEnum position;
    protected PRNode father;

    public int getLevel() {
        return level;
    }

    public int getId() {
        return id;
    }

    public PositionEnum getPosition() {
        return position;
    }

    public PRNode getFather() {
        return father;
    }

    public abstract String toJson();

    public abstract PRNode clone();

}
