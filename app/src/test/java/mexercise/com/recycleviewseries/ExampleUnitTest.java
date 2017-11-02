package mexercise.com.recycleviewseries;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void createDimens() throws Exception {
        for (int i = 300; i >= 70; i--) {
            System.out.println("<dimen name=\"space_" + i +"\">"+ i +".0dip</dimen>");
        }
    }


}