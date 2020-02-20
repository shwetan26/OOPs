package tree.interfaces;

/**
 * @author shwetan
 * This is the strategy interface to implement different ordering
 * This interface will be implemented by concrete classes
 * for lexicographic ordering and reverse lexicographic ordering
 */
public interface TreeOrderStrategy {
    //method to order elements lexicographically or reverse lexicographically
    int compare(String firstValue, String secondValue);
}
