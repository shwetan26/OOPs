package tree.strategy;
/**
 * @author shwetan
 * This strategy will order elements lexicographically
 */

import tree.interfaces.TreeOrderStrategy;

public class OrderedTreeStrategy implements TreeOrderStrategy {

    @Override
    public int compare(String firstValue, String secondValue) {
        return firstValue.compareTo(secondValue);
    }
}
