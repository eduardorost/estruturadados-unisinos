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
import estruturasdados.trabalhoGB.Helpers.PRInternalNodeTypeAdapter;
import estruturasdados.trabalhoGB.Helpers.PRLeafNodeTypeAdapter;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rosted
 */
public class PRInternalNode extends PRNode {

    public PRInternalNode(int[] marginW, int[] marginH, PRNode father) {
        super();

        this.father = father;
        this.level = father == null ? 0 : father.getLevel() + 1;
        this.marginW = marginW;
        this.marginH = marginH;
        this.width = (marginW[1] - marginW[0]) + 1;
        this.height = (marginH[1] - marginH[0]) + 1;
    }

    private PRInternalNode(PRNode nwChild, PRNode neChild, PRNode swChild, PRNode seChild, PositionEnum position, int[] marginW, int[] marginH, int height, int width, PRNode father) {
        super();

        this.nwChild = nwChild;
        this.neChild = neChild;
        this.swChild = swChild;
        this.seChild = seChild;
        this.marginW = marginW;
        this.marginH = marginH;
        this.height = height;
        this.width = width;
        this.position = position;
        this.father = father;
    }

    //LEMBRAR QUE ESSES FILHOS PODEM SER INTERNOS OU FOLHAS, FAZER O TYPEOF PARA IDENTIFICAR QUAL TIPO ELE É.
    private PRNode nwChild;
    private PRNode neChild;
    private PRNode swChild;
    private PRNode seChild;
    private PRLeafNode compressedChild;

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
                    node.position = PositionEnum.NW;
                    node.father = this;
                    node.level = this.level + 1;
                } else {
                    if (nwChild instanceof PRInternalNode) {
                        ((PRInternalNode) nwChild).insert(node);
                    } else {
                        int[] w = {this.marginW[0], (this.marginW[0] + (this.width / 2) - 1)};
                        int[] h = {this.marginH[0], (this.marginH[0] + (this.height / 2) - 1)};

                        PRInternalNode internalNode = new PRInternalNode(w, h, this);
                        internalNode.insert((PRLeafNode) nwChild);
                        internalNode.insert(node);
                        nwChild = internalNode;
                    }
                }
                break;
            case NE:
                if (neChild == null) {
                    neChild = node;
                    node.position = PositionEnum.NE;
                    node.father = this;
                    node.level = this.level + 1;
                } else {
                    if (neChild instanceof PRInternalNode) {
                        ((PRInternalNode) neChild).insert(node);
                    } else {
                        int[] w = {this.marginW[1] - ((this.width / 2) - 1), this.marginW[1]};
                        int[] h = {this.marginH[0], (this.marginH[0] + (this.height / 2) - 1)};

                        PRInternalNode internalNode = new PRInternalNode(w, h, this);
                        internalNode.insert((PRLeafNode) neChild);
                        internalNode.insert(node);
                        neChild = internalNode;
                    }
                }
                break;
            case SW:
                if (swChild == null) {
                    swChild = node;
                    node.position = PositionEnum.SW;
                    node.father = this;
                    node.level = this.level + 1;
                } else {
                    if (swChild instanceof PRInternalNode) {
                        ((PRInternalNode) swChild).insert(node);
                    } else {
                        int[] w = {this.marginW[0], (this.marginW[0] + (this.width / 2) - 1)};
                        int[] h = {this.marginH[1] - ((this.height / 2) - 1), this.marginH[1]};

                        PRInternalNode internalNode = new PRInternalNode(w, h, this);
                        internalNode.insert((PRLeafNode) swChild);
                        internalNode.insert(node);
                        swChild = internalNode;
                    }
                }
                break;
            case SE:
                if (seChild == null) {
                    seChild = node;
                    node.position = PositionEnum.SE;
                    node.father = this;
                    node.level = this.level + 1;
                } else {
                    if (seChild instanceof PRInternalNode) {
                        ((PRInternalNode) seChild).insert(node);
                    } else {
                        int[] w = {this.marginW[1] - ((this.width / 2) - 1), this.marginW[1]};
                        int[] h = {this.marginH[1] - ((this.height / 2) - 1), this.marginH[1]};

                        PRInternalNode internalNode = new PRInternalNode(w, h, this);
                        internalNode.insert((PRLeafNode) seChild);
                        internalNode.insert(node);
                        seChild = internalNode;
                    }
                }
                break;
        }
    }

    private PositionEnum getPositionNode(PRLeafNode node) {
        if (node.getX() >= (this.marginW[0] + this.marginW[1] + 1) / 2) {
            if (node.getY() >= (this.marginH[0] + this.marginH[1] + 1) / 2) {
                return PositionEnum.SE;
            } else {
                return PositionEnum.NE;
            }
        } else {
            if (node.getY() >= (this.marginH[0] + this.marginH[1] + 1) / 2) {
                return PositionEnum.SW;
            } else {
                return PositionEnum.NW;
            }
        }
    }

    private PositionEnum getPositionNode(PRInternalNode node) {
        if (node.marginW[0] >= (this.marginW[0] + this.marginW[1] + 1) / 2) {
            if (node.marginH[0] >= (this.marginH[0] + this.marginH[1] + 1) / 2) {
                return PositionEnum.SE;
            } else {
                return PositionEnum.NE;
            }
        } else {
            if (node.marginH[0] >= (this.marginH[0] + this.marginH[1] + 1) / 2) {
                return PositionEnum.SW;
            } else {
                return PositionEnum.NW;
            }
        }
    }

    public PRNode CompressChilds(int compressionRate) {
        PRInternalNode node = this.clone();
        List<PRNode> childs = new ArrayList<>();
        List<PRNode> compressed = new ArrayList<>();
        if (nwChild != null) {
            childs.add(nwChild.clone());
        }
        if (neChild != null) {
            childs.add(neChild.clone());
        }
        if (swChild != null) {
            childs.add(swChild.clone());
        }
        if (seChild != null) {
            childs.add(seChild.clone());
        }

        while (!childs.isEmpty()) {
            boolean canCompress = true;
            int r = 0;
            int g = 0;
            int b = 0;
            //distancia de branco até preto
            int maxDistance = (int) Math.sqrt(Math.pow(255, 2) * 3);

            while (!childs.isEmpty()) {
                PRNode child = childs.remove(0);
                if (child instanceof PRLeafNode) {
                    PRLeafNode leafChild = (PRLeafNode) child;
                    while (!childs.isEmpty()) {
                        PRLeafNode c = (PRLeafNode) childs.remove(0);
                        if (c instanceof PRLeafNode) {
                            int distance = leafChild.distanceColor(c.getColor());
                            canCompress = canCompress && ((double) (distance * 100) / maxDistance) <= compressionRate;
                            if (!canCompress) {
                                break;
                            }
                            r += c.getColor().getRed();
                            g += c.getColor().getGreen();
                            b += c.getColor().getBlue();
                        }
                    }
                    if (canCompress) {
                        r += leafChild.getColor().getRed();
                        g += leafChild.getColor().getGreen();
                        b += leafChild.getColor().getBlue();

                        compressed.add(new PRLeafNode(0, 0, new Color(Math.round(r / 4), Math.round(g / 4), Math.round(b / 4)).getRGB()));
                    } else {
                        break;
                    }
                } else {
                    compressed.add(((PRInternalNode) child).CompressChilds(compressionRate));
                }
            }

            if (compressed.isEmpty()) {
                return node;
            } else if (compressed.size() == 1) {
                node.compressedChild = (PRLeafNode) compressed.remove(0);
                node.compressedChild.father = node;
                node.compressedChild.level = node.level + 1;
                node.cleanChilds();
            } else {
                for (PRNode c : compressed) {
                    switch (node.getPositionNode((PRInternalNode) c)) {
                        case NW:
                            node.nwChild = c;
                            break;
                        case NE:
                            node.neChild = c;
                            break;
                        case SW:
                            node.swChild = c;
                            break;
                        case SE:
                            node.seChild = c;
                            break;
                    }
                }
            }

            if (node.nwChild != null && node.nwChild instanceof PRInternalNode && node.neChild != null && node.neChild instanceof PRInternalNode && node.swChild != null && node.swChild instanceof PRInternalNode && node.seChild != null && node.seChild instanceof PRInternalNode) {
                PRInternalNode nw = (PRInternalNode) node.nwChild;
                PRInternalNode ne = (PRInternalNode) node.neChild;
                PRInternalNode sw = (PRInternalNode) node.swChild;
                PRInternalNode se = (PRInternalNode) node.seChild;

                if (nw.compressedChild != null && ne.compressedChild != null && sw.compressedChild != null && se.compressedChild != null) {
                    compressed.clear();
                    childs.add(nw.compressedChild.clone());
                    childs.add(ne.compressedChild.clone());
                    childs.add(sw.compressedChild.clone());
                    childs.add(se.compressedChild.clone());
                }
            }
        }

        return node;
    }

    public int[] getCompressedArray(int width, int height) {
        int[] pixels = new int[width * height];

        if (compressedChild != null) {
            for (int i = marginW[0]; i <= marginW[1]; i++) {
                for (int j = marginH[0]; j <= marginH[1]; j++) {
                    pixels[i + (j * width)] = compressedChild.getRgb();
                }
            }
        } else {
            if (swChild != null) {
                if (swChild instanceof PRLeafNode) {
                    PRLeafNode node = (PRLeafNode) swChild;
                    pixels[node.getX() + (node.getY() * width)] = node.getRgb();
                } else {
                    PRInternalNode node = (PRInternalNode) swChild;
                    int[] array = node.getCompressedArray(width, height);
                    for (int i = 0; i < array.length; i++) {
                        if (array[i] != 0) {
                            pixels[i] = array[i];
                        }
                    }
                }
            }
            if (seChild != null) {
                if (seChild instanceof PRLeafNode) {
                    PRLeafNode node = (PRLeafNode) seChild;
                    pixels[node.getX() + (node.getY() * width)] = node.getRgb();
                } else {
                    PRInternalNode node = (PRInternalNode) seChild;
                    int[] array = node.getCompressedArray(width, height);
                    for (int i = 0; i < array.length; i++) {
                        if (array[i] != 0) {
                            pixels[i] = array[i];
                        }
                    }
                }
            }
            if (nwChild != null) {
                if (nwChild instanceof PRLeafNode) {
                    PRLeafNode node = (PRLeafNode) nwChild;
                    pixels[node.getX() + (node.getY() * width)] = node.getRgb();
                } else {
                    PRInternalNode node = (PRInternalNode) nwChild;
                    int[] array = node.getCompressedArray(width, height);
                    for (int i = 0; i < array.length; i++) {
                        if (array[i] != 0) {
                            pixels[i] = array[i];
                        }
                    }
                }
            }
            if (neChild != null) {
                if (neChild instanceof PRLeafNode) {
                    PRLeafNode node = (PRLeafNode) neChild;
                    pixels[node.getX() + (node.getY() * width)] = node.getRgb();
                } else {
                    PRInternalNode node = (PRInternalNode) neChild;
                    int[] array = node.getCompressedArray(width, height);
                    for (int i = 0; i < array.length; i++) {
                        if (array[i] != 0) {
                            pixels[i] = array[i];
                        }
                    }
                }
            }
        }

        return pixels;
    }

    public PRLeafNode getCompressedChild() {
        return compressedChild;
    }

    public PRNode getNwChild() {
        return nwChild;
    }

    public PRNode getNeChild() {
        return neChild;
    }

    public PRNode getSwChild() {
        return swChild;
    }

    public PRNode getSeChild() {
        return seChild;
    }

    public int[] getMarginW() {
        return marginW;
    }

    public int[] getMarginH() {
        return marginH;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    private void cleanChilds() {
        this.nwChild = null;
        this.neChild = null;
        this.swChild = null;
        this.seChild = null;
    }

    @Override
    public String toJson() {
        Gson gson = new GsonBuilder()
                .enableComplexMapKeySerialization()
                .serializeNulls()
                .registerTypeAdapter(PRLeafNode.class, new PRLeafNodeTypeAdapter())
                .registerTypeAdapter(PRInternalNode.class, new PRInternalNodeTypeAdapter())
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        return gson.toJson(this);
    }

    @Override
    public PRInternalNode clone() {
        return new PRInternalNode(nwChild, neChild, swChild, seChild, position, marginW, marginH, height, width, father);
    }

}
