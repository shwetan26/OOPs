package tree.visitor;
/**
 * @author shwetan
 * This visitor will give pre-ordered represenattion of tree
 * with open and closed parentheses
 */

import tree.nullobject.NonNullNode;
import tree.nullobject.NullNode;

public class PreOrderVisitor extends Visitor {
    private final String EMPTY_NODE = "()";

    /**
     * @param node
     * @return empty node value
     */
    @Override
    public String visitNullNode(NullNode node) {
        return EMPTY_NODE;
    }

    /**
     * @param node
     * @return node value
     */
    @Override
    public String visitNonNullNode(NonNullNode node) {
        return node.getValue();
    }
}
