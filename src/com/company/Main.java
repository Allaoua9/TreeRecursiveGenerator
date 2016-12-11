package com.company;

import tree.Node;
import treegenerator.TreeRecursiveGenerator;

public class Main {

    public static void main(String[] args) {
	// write your code here

        TreeRecursiveGenerator generator = new TreeRecursiveGenerator();

        Node tree = generator.generateTree(4, 12);

        printTree(tree);
    }


    static int tab = 0;
    public static void printTree(Node node) {
        if(node==null) {
            System.out.print("#");
        }
        else {
            System.out.println("<<"+ node +">> {");
            tab++;
            for(int i=0; i<tab; i++) System.out.print("\t");
            printTree(node.getLeft());
            System.out.println(",");
            for(int i=0; i<tab; i++) System.out.print("\t");
            printTree(node.getRight());
            System.out.print("\n");
            tab--;
            for(int i=0; i<tab; i++) System.out.print("\t");
            System.out.print("}");
        }
    }
}
