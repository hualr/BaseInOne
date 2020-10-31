import com.google.common.base.Charsets;
import com.google.common.io.ByteStreams;
import com.google.common.io.CharStreams;
import com.google.common.io.Files;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.util.List;
import org.junit.Test;

/**
 * Author: zongqi
 * Function:
 * Creating Time：2020/10/31 17:50
 * Version: 1.0.0
 */
public class InputStreamTest {
    final File sourceFile = new File("D:/Demo/baseInOne/06-io/src/main/resources/1.txt");
    final File targetFile = new File("D:/Demo/baseInOne/06-io/src/main/resources/2.txt");

    /**
     * InputStream是直接输入流 主要将读取到的数据写入到java程序中
     * 这是所有字节输入流的父类,我们用的是其子
     * 如下读取时一个一个读取的
     */
    @Test
    public void InputStreamTest() {
        int i;
        try (InputStream input = new FileInputStream(sourceFile);) {
            while ((i = input.read()) != -1) {
           /*
            条件做了下面的事情
            1. 通过fis输入流调用read方法，读取一个字节。
            2. 把读取到的这个字节赋值给变量i。
            3. 判断i是否不等于-1，如果不是-1表示读取到了内容，就在循环中对读取到的内容进行处理。
            读取到的内容在i里面保存*/
                System.out.println((char) i);
            }
        } catch (IOException e) {
            System.out.println("读取错误");
        }
    }

    /**
     * 先储存 然后发送 作为一个buffer
     * read()返回读取的字节，读到末尾返回-1
     * read(byte[] b)返回的是读到的字节个数，读到的字节放在了bytes字节数组里，读到末尾没数据了返回-1。
     */
    @Test
    public void BufferInputStreamTest() {
        int i;
        try (InputStream input = new FileInputStream(sourceFile);
             InputStream bufferInput = new BufferedInputStream(input);
        ) {   //一般为1024 为了方便效果我改掉了
            byte[] bytes = new byte[10];
            while ((i = bufferInput.read(bytes)) != -1) {
                System.out.print(new String(bytes, 0, i));
            }
        } catch (IOException e) {
            System.out.println("读取错误");
        }
    }

    @Test
    /**
     * 优点 不需要关闭读写流
     * 特点 一行一行读取
     */
    public void guavaTest1() throws IOException {
        List<String> lines = Files.readLines(sourceFile, Charsets.UTF_8);
        for (String line : lines) {
            System.out.println(line);
        }
    }

    @Test
    public void guavaTest2() {
        try (Reader reader = new FileReader(sourceFile);) {
            List<String> list = CharStreams.readLines(reader);
            System.out.println(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 完全没有优化的版本
     * 根据dir读取文件显示出来
     */
    @Test
    public void readTest() {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(sourceFile);
            byte[] buf = new byte[1024]; //数据中转站 临时缓冲区
            int length;
            //循环读取文件内容，输入流中将最多buf.length个字节的数据读入一个buf数组中,返回类型是读取到的字节数。
            //当文件读取到结尾时返回 -1,循环结束。
            while ((length = fis.read(buf)) != -1) {
                System.out.println(new String(buf, 0, length));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                assert fis != null;
                fis.close();//强制关闭输入流
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
