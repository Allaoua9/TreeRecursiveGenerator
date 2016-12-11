package treegenerator;

import tree.Node;

import java.util.ArrayList;
import java.util.Random;

/**
 * BooXchange Project
 * Created by Al on 11-Dec-16.
 */
public class TreeRecursiveGenerator {

    private ArrayList<Integer> coefficients;

    public TreeRecursiveGenerator() {
        this.coefficients = new ArrayList<>();
        this.coefficients.add(0);
        this.coefficients.add(1);
    }

    public Node generateTree(int size) {

        int order = TreeRecursiveGenerator.randInt(size);
        this.processCoefficients(size);

        return null;
    }


    public Node generateTree(int size, int order) {

        System.out.println("INFO: Generating a tree of size (" + size +") and order (" + order + ")...");

        if (size == 1) {
            if (order >= this.coefficients.get(size)) {
                System.out.println("ERROR : order must be less than (" + this.coefficients.get(size) + ")");
                return null;
            }
            Node n = new Node(1);
            n.setRight(null);
            n.setLeft(null);
            return n;
        }
        else {

            this.processCoefficients(size);

            if (order >= this.coefficients.get(size)) {
                System.out.println("ERROR : order must be less than (" + this.coefficients.get(size) + ")");
                return null;
            }
            else {
                Node n = new Node(size);

                int bn1 = this.coefficients.get(size - 1);

                if (order < bn1) {
                    System.out.println("INFO : (order = " + order + ") < " + bn1);
                    n.setLeft(this.generateTree(size - 1, order));
                }
                else if (order < (2 * bn1)) {
                    System.out.println("INFO : (order = " + order + ") < " + (2 * bn1));
                    n.setRight(this.generateTree(size - 1, order % bn1));
                }
                else {
                    order = order - (2 * bn1);
                    int coef;
                    int i = 0;
                    boolean orderPositive = true;
                    while (orderPositive) {
                        coef = this.coefficients.get(i) * this.coefficients.get(size - 1 - i);
                        orderPositive = (order - coef) >= 0;
                        if (orderPositive)  {
                            i++;
                            order = order - coef;
                        }
                    }
                    System.out.println("INFO : order = " + order + ", at i = " + i);

                    int order1 = order % this.coefficients.get(i);
                    n.setLeft(this.generateTree(i, order1));

                    int order2 = ( order / this.coefficients.get(i) ) % this.coefficients.get(size - 1 - i);
                    n.setRight(this.generateTree(size - 1 - i, order2));
                }

                return n;
            }
        }
    }

    private void processCoefficients(int size) {

        int sum = 0;
        int i = this.coefficients.size() - 1;

        if (i < size) {
            System.out.println("INFO : Processing Coefficients...");
            while (i < size) {
                for (int j = 0; j <= i; j++) {
                    sum = sum + this.coefficients.get(j)*this.coefficients.get(i-j);
                }
                this.coefficients.add(2*this.coefficients.get(i) + sum);

                System.out.print("//" + (i+1) + ":" + this.coefficients.get(i+1));
                sum = 0;
                i++;
            }
            System.out.println("//");
            System.out.println("INFO : Coefficients Processed.");
        }
        else {
            System.out.println("INFO : Coefficients Cached...");
        }
    }

    public ArrayList<Integer> getCoefficients() {
        return coefficients;
    }

    private static int randInt(int p) {
        return new Random().nextInt(p);
    }
}
