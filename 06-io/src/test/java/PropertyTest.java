import com.google.common.io.ByteStreams;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.junit.Test;

/**
 * Author: zongqi
 * Function:
 * Creating Time：2020/10/31 12:16
 * Version: 1.0.0
 */
public class PropertyTest {
    final File sourceFile = new File("D:/Demo/baseInOne/06-io/src/main/resources/a.properties");

    @Test
    public void test(){
        Properties properties=new Properties();
        try(InputStream inputStream= new BufferedInputStream(new FileInputStream(sourceFile));){
            properties.load(inputStream);
            String a=properties.getProperty("a");
            System.out.println(a);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
