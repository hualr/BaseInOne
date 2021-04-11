package com.hualr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * Function: redis<br>
 * Creating Time: 2021/2/10 <br>
 * Version: 1.0.0
 *
 * @author 宗旗
 */
public class RedisJava1 {
    private Jedis jedis;

    @Before
    public void setup() {
        //连接redis服务器，192.168.0.100:6379
        jedis = new Jedis("192.168.142.128");
        jedis.auth("123456");
    }

    @After
    public void destory(){
        if (jedis!=null){
            jedis.close();
        }
    }

    /**
     * redis存储字符串
     */
    @Test
    public void testString() {
        //add
        jedis.set("name", "zongqi");
        System.out.println(jedis.get("name"));

        System.out.println("jedis.select(0) = " + jedis.select(0));
        //append
        jedis.append("name", " is my student");
        System.out.println(jedis.get("name"));

        //del
        jedis.del("name");
        System.out.println(jedis.get("name"));

        //设置多个键值对
        jedis.mset("name", "zongqi", "age", "24", "qq", "303121121");
        //进行加1操作
        jedis.incr("age");
        System.out.println(jedis.get("name") + "-" + jedis.get("age") + "-" + jedis.get("qq"));

        Set<String> col=jedis.keys("*");
        System.out.println(col);
    }

    /**
     * redis操作Map
     */
    @Test
    public void testMap() {
        //-----添加数据----------
        Map<String, String> map = new HashMap<>();
        map.put("name", "zongqi");
        map.put("age", "24");
        map.put("qq", "123456");
        jedis.hmset("user", map);
        //取出user中的name，执行结果:[minxr]-->注意结果是一个泛型的List

        //第一个参数是存入redis中map对象的key，后面跟的是放入map中的对象的key，后面的key可以跟多个，是可变参数
        List<String> rsmap = jedis.hmget("user", "name", "age", "qq");
        System.out.println(rsmap);

        //删除map中的某个键值
        jedis.hdel("user", "age");
        System.out.println(jedis.hmget("user", "age")); //因为删除了，所以返回的是null
        System.out.println(jedis.hlen("user")); //返回key为user的键中存放的值的个数2
        System.out.println(jedis.exists("user"));//是否存在key为user的记录 返回true
        System.out.println(jedis.hkeys("user"));//返回map对象中的所有key
        System.out.println(jedis.hvals("user"));//返回map对象中的所有value

        for (final String key : jedis.hkeys("user")) {
            System.out.println(key + ":" + jedis.hmget("user", key));
        }
    }

    /**
     * jedis操作List
     */
    @Test
    public void testList() {
        // 开始前，先移除所有的内容
        jedis.del("number");
        System.out.println(jedis.lrange("number", 0, -1));
        //先向key java framework中存放三条数据
        jedis.lpush("number", "1");
        jedis.lpush("number", "2");
        jedis.lpush("number", "3");

        // 再取出所有数据jedis.lrange是按范围取出，
        // 第一个是key，第二个是起始位置，第三个是结束位置，
        // jedis.llen获取长度 -1表示取得所有
        System.out.println(jedis.lrange("number", 0, -1));

        jedis.del("number");
        jedis.rpush("number", "1");
        jedis.rpush("number", "2");
        jedis.rpush("number", "3");
        System.out.println(jedis.lrange("number", 0, -1));
    }

    /**
     * jedis操作Set
     */
    @Test
    public void testSet() {
        //添加
        jedis.sadd("ADMIN", "zongqi");
        jedis.sadd("ADMIN", "who");

        //移除noname
        jedis.srem("ADMIN", "who");

        //获取所有加入的value
        System.out.println(jedis.smembers("ADMIN"));

        //判断 who 是否是user集合的元素
        System.out.println(jedis.sismember("ADMIN", "who"));
        System.out.println(jedis.srandmember("ADMIN"));

        //返回集合的元素个数
        System.out.println(jedis.scard("ADMIN"));
    }

    @Test
    public void test() {
        //jedis 排序
        //注意，此处的rpush和lpush是List的操作。是一个双向链表（但从表现来看的）
        jedis.del("a");//先清除数据，再加入数据进行测试
        jedis.rpush("a", "1");
        jedis.lpush("a", "6");
        jedis.lpush("a", "3");
        jedis.lpush("a", "9");
        System.out.println(jedis.lrange("a", 0, -1));// [9, 3, 6, 1]
        System.out.println(jedis.sort("a")); //[1, 3, 6, 9]  //输入排序后结果
        System.out.println(jedis.lrange("a", 0, -1));
    }

    @Test
    public void testRedisPool() {
        Objects.requireNonNull(RedisUtil.getJedis()).set("newname", "中文测试");
        System.out.println(RedisUtil.getJedis().get("newname"));
    }
}