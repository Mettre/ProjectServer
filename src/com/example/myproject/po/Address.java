package	com.example.myproject.po;
public class Address {
	private long id;
	private String name;
	private String phone;
	private String address;
	private boolean default;
	public void setId(long id){
		this.id = id;
	}
	public long getId(){
		return id;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void setPhone(String phone){
		this.phone = phone;
	}
	public String getPhone(){
		return phone;
	}
	public void setAddress(String address){
		this.address = address;
	}
	public String getAddress(){
		return address;
	}
	public void setDefault(boolean default){
		this.default = default;
	}
	public boolean getDefault(){
		return default;
	}
}
