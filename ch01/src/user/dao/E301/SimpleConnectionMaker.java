package user.dao.E301;

/**
 * ���Ӱ� ���� Ŭ���� -> DBĿ�ؼ�
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//���̻�, �߻�Ŭ������ �ƴϴ�! -> ����� �̿��� Ȯ������ ������� ������.
public class SimpleConnectionMaker {
	public Connection getConnection() throws ClassNotFoundException,
			SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection c = DriverManager.getConnection(
				"jdbc:mysql://localhost/springbook?characterEncoding=UTF-8", "spring", "book");
		return c;
	}
}
