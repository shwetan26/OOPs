package tree.interfaces;

import tree.nullobject.Node;
import tree.visitor.Visitor;

/**
 * @author shweta
 * Binary search tree interface
 * It has method to insert value and toString method to
 * give representation of tree
 */
public interface Tree {
    void insert(String value);

    String toString(Visitor visitor);

    Node getRoot();
}
