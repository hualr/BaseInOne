package pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*功能
在一串string字符中找到所有符合正则表达式的单元
然后将这些单元遍历获取*/
public class PatternTest {
    public static void main(String[] args) {
        //这个正则表达式表示 三个字符的单词
        String regex = "\\b\\w{3}\\b";
        String s = "da jia ting wo shuo,jin tian yao xia yu,bu shang wan zi xi,gao xing bu?";


        // 1.将规则定义为对象
        Pattern p = Pattern.compile(regex);
        // 2. 通过模式对象匹配所有是的规则
        Matcher m = p.matcher(s);
        // 调用匹配器对象的功能   通过find方法就是查找有没有满足条件的子串
        //public boolean find()
        boolean flag = m.find();
        System.out.println("匹配到则true:" + flag);


        // 3. 获取当前位置的最先出现的字符串
        System.out.println(m.group());

        // 再来一次:每次使用find 往后迭代  注意：一定要先find()，然后才能group()
        flag = m.find();
        System.out.println(flag);
        System.out.println(m.group());


        while (m.find()) {
            System.out.println(m.group());
        }


    }

}
