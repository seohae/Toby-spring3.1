/*
 * 1.2.1 ���ɻ��� �и�
 * = �ּ����� �۾������� ����� ������ �� �ִ�
 * = ��ȭ�� �߻� -> ���ߵ� �Ѱ����� ���� -> ������ ������ ������ ����������.
 * = �� ��ȭ�� �̸� ���� -> "������ ���� �� ���� ������, ������ �ٸ����� ���� �������ְ� �Ѵ�"
 * 
 * ��, ���ɻ��� �и���?
 * = ������ ���� �ͳ����� �ϳ��� ��ü ������ �Ǵ� ģ�� ��ü�� ���̰� �ϰ�, 
 *   ������ �ٸ� ���� ������ �� ���� �������� ���� ������ ���� �ʵ��� �и��Ѵ�
 * = ���� ������ ���ɻ縦 �����ϰ� �����ϰ� ���� �и��ϴ� �۾��� ������Ѵ�
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
		// DB ������ ���� Connection ������Ʈ�� �������� �κ�
		// �� DAO �޼ҵ忡���� �̷��� �и��� getConnection() �޼ҵ带 ȣ���ؼ� DB Ŀ�ؼ��� �����´�
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
		// �� DAO �޼ҵ忡���� �̷��� �и��� getConnection() �޼ҵ带 ȣ���ؼ� DB Ŀ�ؼ��� �����´�
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

	// �ߺ� �ڵ��� �޼ҵ� ����
	// Ŀ�ؼ��� �������� �ߺ��� �ڵ带 �и��Ѵ�! 
	// => �ߺ��� �ڵ带 �������� �޼ҵ�� ���� �ߺ��� �����Ͽ���.
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
		user.setName("��⼱");
		user.setPassword("married");

		dao.add(user);
			
		System.out.println(user.getId() + " ��� ����");
		
		User user2 = dao.get(user.getId());
		System.out.println(user2.getName());
		System.out.println(user2.getPassword());
			
		System.out.println(user2.getId() + " ��ȸ ����");
	}

}

/*
 * �����丵 �Ͽ���.
 * �����丵�̶�?
 * = �ʳ��� DAO �ڵ带 �����س����� �������� ����� �߰��ϰ� �������� �ʾҴ�.
 *   ��, ������ �巯���� ���(���)�� �״��������, 
 *   �ڵ� ������ ���� ���(�޼ҵ� ����)�� �ٲ����ν� ������ DAO�� �������.
 * = ������ �ڵ带 �ܺ��� ���۹�Ŀ��� ��ȭ���� ���� ������ �����ؼ� �籸���ϴ� �۾� �Ǵ� ���.
 */
