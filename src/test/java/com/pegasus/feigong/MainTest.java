package com.pegasus.feigong;

import com.pegasus.feigong.pojo.Cat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.nio.charset.Charset;

@SpringBootTest
public class MainTest {


    //定义序列化方法
    @Test
   void serialize() throws IOException {
        FileOutputStream fos = new FileOutputStream("test.txt");
        ObjectOutputStream objOut = new ObjectOutputStream(fos);
        Cat cat = new Cat();
        cat.setName("feng");
        objOut.writeObject(cat);//将序列化对象存储在本地
        objOut.close();
        System.out.println("序列化成功");

    }

    //定义反序列化方法
    @Test
   void deserialize() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("test.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Cat cat = (Cat) ois.readObject();
        System.out.println("Cat反序列化成功");
        System.out.println(cat.getName());
        ois.close();
    }


    //Java中的命令执行方法
    @Test
    void exec() throws IOException, InterruptedException {
        Process p = Runtime.getRuntime().exec("calc.exe");
        InputStream is = p.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("GBK"))); //设置读取的时候的编码为GBK
        p.waitFor();
        if (p.exitValue() != 0) {
            System.out.println("命令执行失败");
        } else {
            String s = null;
            while ((s = reader.readLine()) != null) {
                System.out.println(s);
            }
        }
    }

}
