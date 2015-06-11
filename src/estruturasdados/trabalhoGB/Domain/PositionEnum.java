// Nós (Eduardo Rost, Fabiano Menegussi, Renan Santos), garantimos que:
//
// - Não utilizamos código fonte obtidos de outros estudantes,
// ou fonte não autorizada, seja modificado ou cópia literal.
// - Todo código usado em nosso trabalho ´e resultado do nosso
// trabalho original, ou foi derivado de um
// código publicado nos livros texto desta disciplina.
// - Temos total ciência das consequências em caso de violarmos estes termos.
package estruturasdados.trabalhoGB.Domain;

public enum PositionEnum {

    ROOT(0), NW(1), NE(2), SW(3), SE(4);
    
    private final int value;

    PositionEnum(int valueOption) {
        value = valueOption;
    }

    public int getValue() {
        return value;
    }

}
