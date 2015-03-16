/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.estruturasdedados.BST;

import br.com.estruturasdedados.ADT.BinNode;

/**
 *
 * @author Fabiano
 */
public class BinaryTree {
    
    public BinaryTree(){   
    }
    
    /** @param rt raiz da subtree */
    public void preOrder(BinNode rt){
        if ( rt != null){            
            visit(rt);
            preOrder(rt.left());
            preOrder(rt.right());
        }
    }
    
    /** @param rt raiz da subtree */
    public void inOrder(BinNode rt){
        if (rt != null){
            inOrder(rt.left());
            visit(rt);
            inOrder(rt.right());
        }
    }
    
    /** @param rt raiz da subtree */
    public void postOrder(BinNode rt){
        if  (rt != null){
            postOrder(rt.left());
            postOrder(rt.right());
            visit(rt);
        }
    }
    
    /** @param rt raiz da subtree */
    public void visit(BinNode rt){
        System.out.println("Nodo " + rt.toString() + " visitado!!");
    }
}