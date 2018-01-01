/*
 * 하나의 문제점이 생겼다
 * E202.UserDao의 예제 소스를 N사, D사에서 구매하겠다고 주문이 들어왔을때,
 * N사와 D사는 서로 다른 종류의 DB를 사용하고있고,
 * DB 커넥션을 가져오는데에 있어 독자적으로 만든 방법을 적용하고 싶어한다.
 * 더욱 큰 문제는, UserDao를 구매한 이후에도 DB 커넥션을 가져오는 방법이 종종 변경될 수가 있다는 것이다.
 * 
 * [상속을 통한 확장]
 * = E202.UserDao를 한단계 더 분리하면 된다.
 * = 추상메소드를 만든다! -> UserDao는 추상클래스가 되었다.
 * = 이 추상클래스를 상속하는 각 N사, D사의 UserDao를 만들어보자.
 */
package user.dao.E203;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import user.domain.User;

public abstract class UserDao {
	public void add(User user) throws ClassNotFoundException, SQLException {
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
	
	// 추상메소드 선언!
	// 이렇게하면, UserDao의 소스코드를 제공해서 수정해 쓰도록 하지 않아도,
	// getConnection() 메소드를 원하는 방식으로 확장한 후 UserDao의 기능을 함께 사용할 수 있다.
	abstract protected Connection getConnection() throws ClassNotFoundException, SQLException ;


	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		UserDao dao = new NUserDao();

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
 * 이제는, UserDao의 코드는 한줄도 수정할 필요가 없어졌다.
 * 새로운 DB 연결 방법을 적용해야 할때에는 UserDao를 상속을 통해 확장해주기만 하면 된다.
 * 
 * "템블릿 메소드 패턴"
 * = 추상메소드나 오버라이딩이 가능한 메소드 등으로 만든뒤, 서브클래스에서 이런 메소드를
 *   필요에 맞게 구현해서 사용하도록 하는 방법
 *   
 * 이제 UserDao는 완벽한가? -> 아니다.
 * "상속"을 사용했다는 단점이 생겼다.
 * 상속 자체는 간단해보이고 사용하기에 편리해보이지만, 한계점이 많다.
 * 자바는 다중상속을 허용하지 않는다.
 * 
 * 1. 커넥션 객체를 가져오는 방법을 분리하기위해 상속구조로 만들어버리면, 후에 다른 목적으로
 *    UserDao에 상속을 적용하기 힘들다.
 * 2. 상속을 통한 상하위 클래스의 관계가 생각보다 밀접하다.
 * 
 * 상속관계는 두 가지 관심사에 대해 긴밀한 결합을 허용하고 있다.
 * 서브클래스는 슈퍼클래스의 기능을 직접 사용할 수 있다.
 * -> 슈퍼클래스의 내부의 변경이 생기면? -> 모든 서브클래스를 함께 수정해야하거나 다시 개발해야한다.
 * 
 * 3. 확장된 기능인 DB 커넥션을 생성하는 코드를 다른 DAO 클래스에 적용할 수가 없다.
 * -> UserDao 외의 DAO 클래스들이 계속 만들어질때, 상속을 통하여 만들어진 getConnection()의 구현코드가
 *    매 DAO 클래스마다 중복돼서 나타나버린다. 
 *    
 *    --> 이것을 해결해야한다! 
 */
