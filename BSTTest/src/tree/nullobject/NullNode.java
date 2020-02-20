package tree.nullobject;
/**
 * @author shwetan
 * This class represents null node
 */

import tree.interfaces.TreeOrderStrategy;
import tree.visitor.Visitor;

public class NullNode extends Node {

    public NullNode() {
    }

    /**
     * @param orderStrategy
     * @param root
     * @param value
     * @return non null node
     * Create new non null node for given value
     */
    public Node insert(TreeOrderStrategy orderStrategy, Node root, String value) {
        return new NonNullNode(value);
    }

    /**
     * @param visitor
     * @return string returned by visitor
     */
    @Override
    public String accept(Visitor visitor) {
        return visitor.visitNullNode(this);
    }

    /**
     * @param root
     * @param visitor
     * @return empty node value
     */
    @Override
    public String toString(Node root, Visitor visitor) {
        return accept(visitor);
    }
}
