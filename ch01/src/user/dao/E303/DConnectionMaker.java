package user.dao.E303;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// �������̽��� ������ Ŭ���� DConnectionMaker
public class DConnectionMaker implements ConnectionMaker {
	public Connection makeConnection() throws ClassNotFoundException,
			SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection c = DriverManager.getConnection(
				"jdbc:mysql://localhost/springbook?characterEncoding=UTF-8", "spring", "book");
		return c;
	}
}