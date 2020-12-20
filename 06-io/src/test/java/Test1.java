import com.google.common.io.Resources;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import org.junit.Test;

/**
 * Author: zongqi
 * Function:
 * Creating Time：2020/11/24 19:07
 * Version: 1.0.0
 */
public class Test1 {
    private final String CFG_PATH = System.getProperty("user.dir") + "\\specialRule.json";

    @Test
    public void test1() {
       /* Resources.asCharSource(),
                StandardCharsets.UTF_8).read();*/
        System.out.println(Test1.class.getResource("/"));
    }

    @Test
    public void test2() throws IOException {
        System.out.println(CFG_PATH);
        String s = loadData(CFG_PATH);
        System.out.println(s);
    }

    String loadData(String path) throws IOException {
        File file = new File(path);
        return Resources.asCharSource(file.toURL(), StandardCharsets.UTF_8).read();
    }


    @Test
    public void test3() {
        File configFile = new File(CFG_PATH);

        String result = null;
        try (InputStream input = new FileInputStream(configFile);
             ByteArrayOutputStream output = new ByteArrayOutputStream();) {
            byte[] buffer = new byte[1024];
            int length = 0;
            while ((length = input.read(buffer)) != -1) {
                output.write(buffer, 0, length);
            }
            output.close();
            input.close();
            System.out.println(output.toString());
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
