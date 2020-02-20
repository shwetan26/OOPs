package implementor;

import tree.BinarySearchTree;
import tree.interfaces.TreeOrderStrategy;
import tree.strategy.OrderedTreeStrategy;
import tree.strategy.ReverseOrderedTreeStrategy;
import tree.visitor.PreOrderVisitor;
import tree.visitor.Visitor;

public class Driver {
    public static void  main(String[] args){
        TreeOrderStrategy strategy = new OrderedTreeStrategy();
        TreeOrderStrategy reverseStrategy = new ReverseOrderedTreeStrategy();
        BinarySearchTree tree = new BinarySearchTree(reverseStrategy);
        /*tree.insert("ae");
        tree.insert("ade");
        tree.insert("fa");
        tree.insert("gb");*/
        tree.insert("ae");
        tree.insert("ade");
        tree.insert("fa");
        tree.insert("gb");
        Visitor treeVisitor = new PreOrderVisitor();
        String result = tree.toString(treeVisitor);
        System.out.println(result);
    }
}
