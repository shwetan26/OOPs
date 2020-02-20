import texteditor.FlyweightTextEditor;
import texteditor.NormalTextEditor;
import sizeutil.SizeOfUtil;
import org.junit.Test;

/**
 * @author shwetan
 */
public class SizeOfUtilTest {

    private double usedSizeWithFlyweight;
    private double usedSizeWithoutFlyweight;
    /**
     * This is the test method to find out the memory usage
     * with flyweight pattern
     */
    @Test
    public void testFlyweightTextEditor(){
        double usedSize = new SizeOfUtil() {

            @Override
            protected int create() {
                FlyweightTextEditor editor = new FlyweightTextEditor();
                editor.create();
                return  1;
            }

        }.averageBytes();
        usedSizeWithFlyweight = usedSize/(1024L*1024L);
        System.out.printf("The average size using flyweight pattern is %.1f megabytes",usedSizeWithFlyweight);
    }

    /**
     * This is the test method to find out the memory usage
     * without flyweight pattern
     */
    @Test
    public void testNormalTextEditor(){
        double usedSize = new SizeOfUtil(){
            @Override
            protected int create(){
                NormalTextEditor editor = new NormalTextEditor();
                editor.create();
                return 1;
            }
        }.averageBytes();
        usedSizeWithoutFlyweight = usedSize/(1024L*1024L);
        System.out.printf("The average size without flyweight pattern is %.1f megabytes",usedSizeWithoutFlyweight);
    }
}
