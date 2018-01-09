package user.dao.E303.domain;

public class User {
	String id;
	String name;
	String password;
	
	// 자바빈의 규약에 따르는 클래스에 생성자를 명시적으로 추가할때에는,
	// 파라미터가 없는 디폴트 생성자도 함께 정의해줘야한다.
	public User() {
	}
	
	// 파라피터가 있는 User 클래스 생성자를 추가했다.
	public User(String id, String name, String password) {
		this.id = id;
		this.name = name;
		this.password = password;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
