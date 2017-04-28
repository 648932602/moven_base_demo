package com.modemo.javase.base;  
  
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;  
  
public class SVDemo {  
    public static void main(String[] args) throws Exception {  
        // 序列化User对象  
        SerializeUser();  
        // 反序列User对象  
        User user = DeserializeUser();  
        System.out.println(user);  
    }  
  
    /** 
     * 序列化User对象 
     */  
    private static void SerializeUser() throws FileNotFoundException,  
            IOException {  
        // 创建一个小明  
        User user = new User(1,"小明");  
        // ObjectOutputStream 对象输出流  
        ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(  
                new File("D:/User.txt")));  
        // 序列化输出User对象  
        oo.writeObject(user);  
        System.out.println("序列化成功！");  
        oo.close();  
    }  
  
    /** 
     * 反序列User对象 
     */  
    private static User DeserializeUser() throws Exception, IOException {  
        // ObjectInputStream 对象读取流  
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(  
                new File("D:/User.txt")));  
        // 反序列化User对象  
        User user = (User) ois.readObject();  
        System.out.println("反序列化成功！");  
        ois.close();  
        return user;  
    }  
}  
class User implements Serializable{  
    
	private static final long serialVersionUID = 6243611509008487233L;
	// ID  
    private int id;  
    // 姓名  
    private String name;  
    
    private Object data = "";
      
    User(int id, String name) {  
        this.id = id;  
        this.name = name;  
    }  
  
    public int getId() {  
        return id;  
    }  
    public void setId(int id) {  
        this.id = id;  
    }  
    public String getName() {  
        return name;  
    }  
    public void setName(String name) {  
        this.name = name;  
    }
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}  
    
}  