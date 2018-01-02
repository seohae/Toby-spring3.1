package user.dao.E301;

/**
 * [Ŭ������ �и�]
 * �̹�����, ���ɻ�� ��ȭ�� ������ �ٸ� �ΰ��� �ڵ带 �� �и��غ� ���̴�!
 * 
 * ��Ӱ��谡 �ƴ�, ������ �������� Ŭ������ ������.
 * DB Ŀ�ؼǰ� ���õ� �κ��� ����Ŭ������ �ƴ�, ������ Ŭ������ ��´�.
 * -> simpleConnectionMaker.java ����
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import user.domain.User;

public abstract class UserDao {
	private SimpleConnectionMaker simpleConnectionMaker;
	
	public UserDao() {
		// �ѹ��� ����� �ν��Ͻ� ������ �����صΰ� �޼ҵ忡�� ����ϰ��Ѵ�!
		// ������ �Ϻ����� �ʴ�.
		// UserDao�� SimpleConnectionMaker��� Ư�� Ŭ������ �� �ڵ忡 �������̱� ������
		// ������ ���������� �ٽ� ���ư����ȴ�!
		this.simpleConnectionMaker = new SimpleConnectionMaker();
	}

	public void add(User user) throws ClassNotFoundException, SQLException {
		Connection c = this.simpleConnectionMaker.getConnection();

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
		Connection c = this.simpleConnectionMaker.getConnection();
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

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		UserDao dao = new NUserDao();

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
