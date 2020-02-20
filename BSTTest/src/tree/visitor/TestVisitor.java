package tree.visitor;

import tree.nullobject.NonNullNode;
import tree.nullobject.NullNode;

/**
 * This is the dummy visitor.
 * We are not implementing this in this assignment
 */
public class TestVisitor extends Visitor {
    @Override
    public String visitNullNode(NullNode node) {
        return null;
    }

    @Override
    public String visitNonNullNode(NonNullNode node) {
        return null;
    }
}
