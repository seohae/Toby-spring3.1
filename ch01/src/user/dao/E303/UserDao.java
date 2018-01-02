package user.dao.E303;

/**
 * E301 : ConnectionMaker 인터페이스를 추가하였다
 * 인터페이스를 통해 원하는 기능만 사용하면 된다
 * 인터페이스에서 어떤 일을 하겠다는 기능을 정의 - 구현방법은 써있지않음
 * 인터페이스를 구현하는 클래스에서 구현한다!
 * 
 * 
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import user.domain.User;

public class UserDao {
	// 인터페이스 ConnectionMaker
	// 인터페이스를 통해 오브젝트에 접근하므로 구체적인 클래스 정보를 알 필요가 없다!
	private ConnectionMaker connectionMaker;
	
	public UserDao(ConnectionMaker simpleConnectionMaker) {
		/**
		 * 원래는,
		 * connectionMaker = new DConnectionMaker(); -> 클래스 이름이 나와버린다. (문제점 발생)
		 * [관계 설정 책임의 분리]
		 * 
		 * 어떤 ConnectionMaker 구현 클래스를 사용할지 결정하는 new DConnectionMaker()
		 * 여기서 UserDao와 DConnectionMaker 클래스 사이에 직접적인 관계가 생겨버린다!! -> 해결해야한다.
		 * 인터페이스 자체에는 기능이 없으니, 특정 클래스의 오브젝트와 관계를 맺을 수 밖에 없다.
		 * 하지만 클래스 사이의 관계가 아닌, 오브젝트 사이의 관계이다!
		 * 특정 클래스를 전혀 알지 못하더라도, 해당 클래스가 구현한 인터페이스를 사용했다면?
		 * -> 그 클래스들의 오브젝트를 인터페이스 타입으로 받아서 사용할 수 있다 (객체지향의 다형성)
		 * 
		 * "생성자를 변경했다"
		 */
		this.connectionMaker = simpleConnectionMaker;
		
		/**
		 * 수정된 코드 : 클라이언트(main 메소드:지금은 UserDaoTest.java)가 미리 만들어둔
		 * ConnectionMaker의 오브젝트를 전달받을 수 있도록 파라미터를 추가했다.
		 * 
		 * -> DConnectionMaker가 사라졌다! -> 문제점을 해결하였다.
		 */
	}

	public void add(User user) throws ClassNotFoundException, SQLException {
		// 인터페이스에 정의된 메소드를 사용하므로, 클래스가 바뀐다고 해도,
		// 메소드 이름이 변경될 걱정은 없다.
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
