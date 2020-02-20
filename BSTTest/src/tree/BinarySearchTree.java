package tree;
/**
 * @author shwetan
 * This class implements BST interface
 * It constructs tree with gibed order strategy
 * It contains implementation to insert values in tree
 * And to give string representation of tree
 */

import tree.interfaces.Tree;
import tree.nullobject.Node;
import tree.interfaces.TreeOrderStrategy;
import tree.nullobject.NullNode;
import tree.visitor.Visitor;

public class BinarySearchTree implements Tree {
    private Node root;
    private TreeOrderStrategy orderStrategy;

    public BinarySearchTree(TreeOrderStrategy orderStrategy) {
        this.root = new NullNode();
        this.orderStrategy = orderStrategy;
    }

    /**
     * @param value Add value in tree with given order strategy
     * Strategy will be given at the time of tree construction
     */
    @Override
    public void insert(String value) {
        root = root.insert(orderStrategy, root, value);
    }

    /**
     * @param visitor
     * @return String representing tree with open and closed parentheses
     * Gives string representation of tree with open and closed parentheses
     * It will call respective toString method of null or nonnull node depending on the type of node
     */
    @Override
    public String toString(Visitor visitor) {
        return root.toString(root, visitor);
    }

    @Override
    public Node getRoot() {
        return root;
    }
}
