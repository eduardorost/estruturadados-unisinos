/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.estruturasdedados.ADT;
/**
 *
 * @author Fabiano
 */
/** ADT for binary tree nodes
 * @param <E> */
public interface BinNode<E> {
    /**get e set
     * @return  */
    public E getElement();
    public void setElement(E v);
    
    /** Retorna o filho da esquerda
     * @return  */
    public BinNode<E> left();
    
    /** Retorna o filho da direita
     * @return  */
    public BinNode<E> right();
    
    /** Retorna true se é um folha
     * @return  */
    public boolean isLeaf();    
}