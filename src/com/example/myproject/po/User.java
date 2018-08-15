package	com.example.myproject.pojo;
import java.util.Date;
public class User {
	private String user_id;
	private String name;
	private int phone;
	private int age;
	private int sex;
	private String avatar;
	private Date creat_time;
	private Date last_login;
	private int type;
	public void setUser_id(String user_id){
		this.user_id = user_id;
	}
	public String getUser_id(){
		return user_id;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void setPhone(int phone){
		this.phone = phone;
	}
	public int getPhone(){
		return phone;
	}
	public void setAge(int age){
		this.age = age;
	}
	public int getAge(){
		return age;
	}
	public void setSex(int sex){
		this.sex = sex;
	}
	public int getSex(){
		return sex;
	}
	public void setAvatar(String avatar){
		this.avatar = avatar;
	}
	public String getAvatar(){
		return avatar;
	}
	public void setCreat_time(Date creat_time){
		this.creat_time = creat_time;
	}
	public Date getCreat_time(){
		return creat_time;
	}
	public void setLast_login(Date last_login){
		this.last_login = last_login;
	}
	public Date getLast_login(){
		return last_login;
	}
	public void setType(int type){
		this.type = type;
	}
	public int getType(){
		return type;
	}
}
