import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.Charsets;
import com.google.common.io.ByteStreams;
import com.google.common.io.Files;
import com.google.common.io.LineProcessor;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.junit.Test;

/**
 * Author: zongqi
 * Function:
 * Creating Time：2020/10/31 10:36
 * Version: 1.0.0
 */
public class GuavaIOTest {
    final File sourceFile = new File("D:/Demo/baseInOne/06-io/src/main/resources/1.txt");
    final File targetFile = new File("D:/Demo/baseInOne/06-io/src/main/resources/2.txt");

    @Test
    public void readBigTest() throws IOException {
        demoFileReadAsyn(sourceFile.getAbsolutePath());
    }

    //复制+文本比较
    @Test
    public void cpTest() throws IOException {
        Files.copy(sourceFile, targetFile);
        System.out.println(Files.equal(sourceFile, targetFile));
    }

    public static void demoFileReadAsyn(final String filePath) throws IOException {
        File testFile = new File(filePath);
        Integer rowNum = Files.readLines(testFile, Charsets.UTF_8, new LineProcessor<Integer>() {
            private int rowNum = 0;
            public boolean processLine(String s) throws IOException {
                rowNum++;
                return true;
            }

            public Integer getResult() {
                return rowNum;
            }
        });
        System.out.println(rowNum);
    }

}
