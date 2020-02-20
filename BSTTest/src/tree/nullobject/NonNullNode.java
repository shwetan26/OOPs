package tree.nullobject;
/**
 * @author shwetan
 * This class represents nonnullnode
 * It has operations to add value to tree,
 * give string representation of tree,
 * method to accept a visitor
 */

import tree.interfaces.TreeOrderStrategy;
import tree.visitor.Visitor;

public class NonNullNode extends Node {
    private final String OPEN_PARENTHESES = "(";
    private final String CLOSED_PARANTHESES = ")";

    public NonNullNode(String value) {
        this.value = value;
        this.left = new NullNode();
        this.right = new NullNode();
    }

    /**
     * @param orderStrategy
     * @param root
     * @param value
     * @return root node of tree
     */
    @Override
    public Node insert(TreeOrderStrategy orderStrategy, Node root, String value) {
        //set right child of node
        if (orderStrategy.compare(root.getValue(), value) < 0) {
            root.setRight(root.getRight().insert(orderStrategy, root.getRight(), value));
        } else
            //set left child of node
            root.setLeft(root.getLeft().insert(orderStrategy, root.getLeft(), value));
        return root;
    }

    /**
     * @param root
     * @param treeVisitor
     * @return String representing tree
     * Create a string by visiting left and right subtree recursively
     */
    @Override
    public String toString(Node root, Visitor treeVisitor) {
        return OPEN_PARENTHESES + root.accept(treeVisitor) + root.getLeft().toString(root.getLeft(), treeVisitor)
                + root.getRight().toString(root.getRight(), treeVisitor) + CLOSED_PARANTHESES;
    }

    /**
     * @param visitor
     * @return value returned by visitor
     */
    @Override
    public String accept(Visitor visitor) {
        return visitor.visitNonNullNode(this);
    }
}
