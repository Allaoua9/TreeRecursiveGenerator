package tree;

/**
 * BooXchange Project
 * Created by Al on 21-Nov-16.
 */
public class Node implements Comparable<Node> {

    private int value;
    private int weight;
    private Node left = null;
    private Node right = null;
    private Node parent = null;

    public Node() {
    }

    public Node(int weight) {
        this.weight = weight;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
        if (left != null) left.setParent(this);
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
        if (right != null) right.setParent(this);
    }

    @Override
    public String toString() {
        return "<<" + this.weight + ">>";
    }

    @Override
    public int compareTo(Node node) {
        return  compare(this, node);
    }

    private static int compare(Node n1, Node n2) {

        if (n1 == null && n2 == null) {
            return 0;
        }
        else if (n1 == null) {
            return -1;
        }
        else if (n2 == null) {
            return 1;
        }
        else {
            if (n1.getWeight() > n2.getWeight()) {
                return 1;
            }
            else if (n1.getWeight() < n2.getWeight()) {
                return -1;
            }
            else {
                int comparison = compare(n1.getLeft(), n2.getLeft());
                if (comparison == 0) {
                    return compare(n1.getRight(), n2.getRight());
                }
                else {
                    return comparison;
                }
            }
        }
    }
}
