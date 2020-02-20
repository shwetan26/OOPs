package tree.visitor;
/**
 * @author shwetan
 * Abstract class for visitor
 */

import tree.nullobject.NonNullNode;
import tree.nullobject.NullNode;

public abstract class Visitor {
    public abstract String visitNullNode(NullNode node);

    public abstract String visitNonNullNode(NonNullNode node);
}
