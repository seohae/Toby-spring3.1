// N���� UserDao
package user.dao.E203;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// �߻�Ŭ���� UserDao�� ����Ͽ���
public class NUserDao extends UserDao {
	// ��ӹ��� �޼��带 Ȯ���ϰ��ִ�
	protected Connection getConnection() throws ClassNotFoundException,
			SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection c = DriverManager.getConnection(
				"jdbc:mysql://localhost/springbook?characterEncoding=UTF-8",
				"spring", "book");
		return c;
	}
}
