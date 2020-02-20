package tree;
/**
 * @author shwetan
 * This is test class which contains unit tests for testing
 * functionalities of BinarySearchTree
 */

import org.junit.Before;
import org.junit.Test;
import tree.interfaces.TreeOrderStrategy;
import tree.strategy.OrderedTreeStrategy;
import tree.strategy.ReverseOrderedTreeStrategy;
import tree.visitor.PreOrderVisitor;
import tree.visitor.Visitor;

import static org.junit.Assert.assertEquals;

public class BinarySearchTreeTest {
    private TreeOrderStrategy orderedStrategy;
    private TreeOrderStrategy reverseOrderStrategy;
    private BinarySearchTree orderedTree;
    private BinarySearchTree reverseOrderedTree;
    private Visitor treeVisitor;

    @Before
    public void setUp() {
        orderedStrategy = new OrderedTreeStrategy();
        reverseOrderStrategy = new ReverseOrderedTreeStrategy();
        orderedTree = new BinarySearchTree(orderedStrategy);
        reverseOrderedTree = new BinarySearchTree(reverseOrderStrategy);
        treeVisitor = new PreOrderVisitor();
        orderedTree.insert("ae");
        orderedTree.insert("ade");
        orderedTree.insert("fa");
        orderedTree.insert("gb");
        reverseOrderedTree.insert("ae");
        reverseOrderedTree.insert("ade");
        reverseOrderedTree.insert("fa");
        reverseOrderedTree.insert("gb");
    }

    /**
     * Test for insert functionalities
     * It checks for root of created binary search tree
     */
    @Test
    public void insert() {
        assertEquals("ae",orderedTree.getRoot().getValue());
    }

    /**
     * This is a test for string representation of tree
     * with lexicographic order and reverse lexicographic order
     */
    @Test
    public void testToString() {
        String orderedResult = "(ae(ade()())(fa()(gb()())))";
        String result = orderedTree.toString(treeVisitor);
        assertEquals(orderedResult,result);
        String reverseOrderedResult = "(ae()(ade()(fa(gb()())())))";
        assertEquals(reverseOrderedResult,reverseOrderedTree.toString(treeVisitor));
    }

    /**
     * Test for empty tree
     */
    @Test
    public void emptyTreeTest(){
        BinarySearchTree emptyTree = new BinarySearchTree(orderedStrategy);
        String expectedString = "()";
        assertEquals(expectedString,emptyTree.toString(treeVisitor));
    }
}