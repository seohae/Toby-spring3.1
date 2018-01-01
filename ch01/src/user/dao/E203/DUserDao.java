// D사의 UserDao
package user.dao.E203;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//추상클래스 UserDao를 상속하였다
public class DUserDao extends UserDao {
	// 상속받은 메서드를 확장하고있다
	// 메소드의 구현을 담당 (추상메소드는 상속받은 UsrDao에 선언)
	protected Connection getConnection() throws ClassNotFoundException,
			SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection c = DriverManager.getConnection(
				"jdbc:mysql://localhost/springbook?characterEncoding=UTF-8",
				"spring", "book");
		return c;
	}
}
