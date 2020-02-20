package tree.strategy;
/**
 * @author shwetan
 * This strategy will first reverse the string
 * and will compare it lexicographically with
 * parent value to insert element in tree
 */

import tree.interfaces.TreeOrderStrategy;

public class ReverseOrderedTreeStrategy implements TreeOrderStrategy {
    @Override
    public int compare(String firstValue, String secondValue) {
        StringBuilder input = new StringBuilder(secondValue);
        //compare reverses string lexicographically
        String str = input.reverse().toString();
        return firstValue.compareTo(str);
    }
}
