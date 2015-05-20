/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estruturasdados.trabalhoGB.Domain;

/**
 *
 * @author rosted
 */
public enum PositionEnum {

    NW(1), NE(2), SW(3), SE(4);
    
    private final int valor;

    PositionEnum(int valorOpcao) {
        valor = valorOpcao;
    }

    public int getValor() {
        return valor;
    }

}
