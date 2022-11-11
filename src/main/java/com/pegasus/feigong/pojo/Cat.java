package com.pegasus.feigong.pojo;

import java.io.*;

public class Cat implements Serializable {


    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private  void readObject(ObjectInputStream in) throws InterruptedException, IOException, ClassNotFoundException {
        //先调用默认的readObject()方法
        in.defaultReadObject();

        //重写，执行系统命令calc.exe
        Process p = Runtime.getRuntime().exec("calc.exe");
        InputStream is = p.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        p.waitFor();
        if(p.exitValue()!=0){
            //说明执行系统命令失败
        }
        String s = null;
        while((s=reader.readLine())!=null){
            System.out.println(s);
        }
    }
}
