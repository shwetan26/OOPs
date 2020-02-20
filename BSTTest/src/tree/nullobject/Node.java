package tree.nullobject;

import tree.interfaces.TreeOrderStrategy;
import tree.visitor.Visitor;

/**
 * @author shwetan
 * This is the abstract class for node
 * which will be extended by null and nonnull node
 */

public abstract class Node {
    protected String value;
    protected Node left;
    protected Node right;

    public String getValue() {
        return value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public abstract Node insert(TreeOrderStrategy orderStrategy, Node root, String value);

    public abstract String accept(Visitor visitor);

    public abstract String toString(Node root, Visitor visitor);
}

