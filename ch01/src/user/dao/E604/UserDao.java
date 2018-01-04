package user.dao.E604;

/**
 * 싱글톤 레지스트리 : 스프링은 직접 싱글톤 형태의 오브젝트를 관리하고 기능을 제공한다.
 * 장점 : static 메소드와 private 생성자를 사용해야하는 비정상적인 클래스가 아니라,
 * 평범한 자바 클래스를 싱글톤으로 활용하게 해준다.
 * 싱글톤 방식으로 사용된 애플리케이션 클래스라도 public 생성자를 가질 수 있다.
 */

/**
 * 의존관계 DI
 * = 예시) A가 B에 의존하고 있다.
 * -> B에 기능이 추가되거나 변경되거나 형식이 바뀌거나 하면, 그 영향이 A로 전달된다.
 * 
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import user.domain.User;

public class UserDao {
	// 인스턴스 변수의 사용
	// connectionMaker는 읽기 전용의 정보이기 때문에 인스턴스 변수로 정의해서 사용해도 상관없다.
	
	// DI [ UserDao가 ConnectionMaker에 의존하고 있는 형태]
	// -> UserDao는 인터페이스 ConnectionMaker에만 의존하고 있다.
	// -> 인터페이스가 변한다면 그 영향을 UserDao가 직접적으로 받게되지만,
	//    ConnectionMaker 인터페이스를 구현한 클래스, 즉 DConnetionMaker 등이 다른것으로 바뀌거나
	//    그 내부에서 사용하는 메소드에 변화가 생겨도 UserDao에 영향을 주지 않는다.
	private ConnectionMaker connectionMaker;
	
	public UserDao(ConnectionMaker simpleConnectionMaker) {
		this.connectionMaker = simpleConnectionMaker;
	}

	public void add(User user) throws ClassNotFoundException, SQLException {
		Connection c = this.connectionMaker.makeConnection();

		PreparedStatement ps = c.prepareStatement(
			"insert into users(id, name, password) values(?,?,?)");
		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());

		ps.executeUpdate();

		ps.close();
		c.close();
	}

	public User get(String id) throws ClassNotFoundException, SQLException {
		Connection c = this.connectionMaker.makeConnection();
		PreparedStatement ps = c
				.prepareStatement("select * from users where id = ?");
		ps.setString(1, id);

		ResultSet rs = ps.executeQuery();
		rs.next();
		User user = new User();
		user.setId(rs.getString("id"));
		user.setName(rs.getString("name"));
		user.setPassword(rs.getString("password"));

		rs.close();
		ps.close();
		c.close();

		return user;
	}

	

}
