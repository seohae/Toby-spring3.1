/*
 * 1.2.1 관심사의 분리
 * = 최소한의 작업만으로 기능을 수정할 수 있다
 * = 변화가 발생 -> 집중된 한가지의 관심 -> 하지만 수정할 파일은 여러가지다.
 * = 이 변화를 미리 예방 -> "관심이 같은 것 끼리 모으고, 관심이 다른것은 따로 떨어져있게 한다"
 * 
 * 즉, 관심사의 분리란?
 * = 관심이 같은 것끼리는 하나의 객체 안으로 또는 친한 객체를 모이게 하고, 
 *   관심이 다른 것은 가능한 한 따로 떨어져서 서로 영향을 주지 않도록 분리한다
 * = 여러 종류의 관심사를 적절하게 구분하고 따로 분리하는 작업을 해줘야한다
 */
package user.dao.E202;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import user.domain.User;

public class UserDao {
	public void add(User user) throws ClassNotFoundException, SQLException {
		// DB 연결을 위한 Connection 오브젝트를 가져오는 부분
		// 각 DAO 메소드에서는 이렇게 분리한 getConnection() 메소드를 호출해서 DB 커넥션을 가져온다
		Connection c = getConnection();

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
		// 각 DAO 메소드에서는 이렇게 분리한 getConnection() 메소드를 호출해서 DB 커넥션을 가져온다
		Connection c = getConnection();
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

	// 중복 코드의 메소드 추출
	// 커넥션을 가져오는 중복된 코드를 분리한다! 
	// => 중복된 코드를 족립적인 메소드로 만들어서 중복을 제거하였다.
	private Connection getConnection() throws ClassNotFoundException,
			SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection c = DriverManager.getConnection("jdbc:mysql://localhost/springbook?characterEncoding=UTF-8", "spring",
				"book");
		return c;
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		UserDao dao = new UserDao();

		User user = new User();
		user.setId("whiteship");
		user.setName("백기선");
		user.setPassword("married");

		dao.add(user);
			
		System.out.println(user.getId() + " 등록 성공");
		
		User user2 = dao.get(user.getId());
		System.out.println(user2.getName());
		System.out.println(user2.getPassword());
			
		System.out.println(user2.getId() + " 조회 성공");
	}

}

/*
 * 리팩토링 하였다.
 * 리팩토링이란?
 * = 초난감 DAO 코드를 개선해나가는 과정에서 기능을 추가하고 변경하지 않았다.
 *   즉, 겉으로 드러나는 기능(결과)은 그대로이지만, 
 *   코드 구조와 구현 방법(메소드 추출)을 바꿈으로써 더나은 DAO를 만들었다.
 * = 기존의 코드를 외부의 동작방식에는 변화없이 내부 구조를 변경해서 재구성하는 작업 또는 기술.
 */
