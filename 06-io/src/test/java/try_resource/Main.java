package try_resource;


import service.AutoClose;

public class Main {
    public static void main(String[] args) throws Exception {
        /*AutoClose autoClose=new AutoClose();
        autoClose.test();
        try{
            autoClose.close();
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        //在语法糖中的内容执行后,自己调用autoClose方法

        try (
                //1. 需要释放的资源
                AutoClose autoClose1 = new AutoClose();
                AutoClose autoClose2 = new AutoClose();

        ) {
            //对资源的使用
            autoClose1.test();
            //autoClose2.test();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //

       /*语法糖的实际含义
       根据实际的class文件即可清楚获取
       AutoClose autoClose3=new AutoClose();
        try{
            autoClose3.test();
        }finally {
            if (null!=autoClose3){
                autoClose3.close();
            }
        }*/
    }
}
