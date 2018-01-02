package user.dao.E301;

/**
 * 새롭게 만든 클래스 -> DB커넥션
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//더이상, 추상클래스가 아니다! -> 상속을 이용한 확장방식을 사용하지 않을것.
public class SimpleConnectionMaker {
	public Connection getConnection() throws ClassNotFoundException,
			SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection c = DriverManager.getConnection(
				"jdbc:mysql://localhost/springbook?characterEncoding=UTF-8", "spring", "book");
		return c;
	}
}
