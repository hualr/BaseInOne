import com.google.common.io.Files;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.junit.Test;

/**
 * Author: zongqi
 * Function:
 * Creating Time：2020/10/31 18:32
 * Version: 1.0.0
 */
public class OutputStreamTest {
    final File sourceFile = new File("D:/Demo/baseInOne/06-io/src/main/resources/1.txt");
    final File targetFile = new File("D:/Demo/baseInOne/06-io/src/main/resources/2.txt");

    @Test
    public void test() {
        try (OutputStream out = new FileOutputStream(sourceFile, true);) {
            // 输出流只能写字节码,因此需要用hello
            out.write("hello".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //其实优势并不明显
    @Test
    public void guavaTest(){
        try {
            Files.write("test".getBytes(), targetFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //完整版
    /**
     * 1. 没有优化try
     * 2. 字节一个一个写入 并非一口气写入
     */
    @Test
    public void writeTest(){
        FileOutputStream fos = null;
        try {
            /*大家注意下第二个参数  此构造方法为重载方法
             有带一个参数的 如:fos = new FileOutputStream(file)这种,
             也有像下面这种带两个参数的, 其实如果不写实际上就是 第二个参数默认为false;
             第二个参数表示是否在源文件已有内容的后面进行追加写入新的内容,
                    即true表示在原有内容的基础上进行追加写入写内容;
                    false表示覆盖擦除原有内容, 写入后只包含新写入内容,
             */
            fos = new FileOutputStream(targetFile,true);
            String words = "2020年10月31日新追加内容!";
            byte[] bytes = words.getBytes();
            for(int i =0; i<bytes.length; i++){
                fos.write(bytes[i]);
            }
            System.out.println("追加内容成功");
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                assert fos != null;
                //close的时候会自动flush!所以不需要这么搞
                fos.flush(); //强制刷新输出流
                fos.close(); //强制关闭输出流
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
