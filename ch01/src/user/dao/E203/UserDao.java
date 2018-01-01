/*
 * �ϳ��� �������� �����
 * E202.UserDao�� ���� �ҽ��� N��, D�翡�� �����ϰڴٰ� �ֹ��� ��������,
 * N��� D��� ���� �ٸ� ������ DB�� ����ϰ��ְ�,
 * DB Ŀ�ؼ��� �������µ��� �־� ���������� ���� ����� �����ϰ� �;��Ѵ�.
 * ���� ū ������, UserDao�� ������ ���Ŀ��� DB Ŀ�ؼ��� �������� ����� ���� ����� ���� �ִٴ� ���̴�.
 * 
 * [����� ���� Ȯ��]
 * = E202.UserDao�� �Ѵܰ� �� �и��ϸ� �ȴ�.
 * = �߻�޼ҵ带 �����! -> UserDao�� �߻�Ŭ������ �Ǿ���.
 * = �� �߻�Ŭ������ ����ϴ� �� N��, D���� UserDao�� ������.
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
	
	// �߻�޼ҵ� ����!
	// �̷����ϸ�, UserDao�� �ҽ��ڵ带 �����ؼ� ������ ������ ���� �ʾƵ�,
	// getConnection() �޼ҵ带 ���ϴ� ������� Ȯ���� �� UserDao�� ����� �Բ� ����� �� �ִ�.
	abstract protected Connection getConnection() throws ClassNotFoundException, SQLException ;


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

/*
 * ������, UserDao�� �ڵ�� ���ٵ� ������ �ʿ䰡 ��������.
 * ���ο� DB ���� ����� �����ؾ� �Ҷ����� UserDao�� ����� ���� Ȯ�����ֱ⸸ �ϸ� �ȴ�.
 * 
 * "�ۺ� �޼ҵ� ����"
 * = �߻�޼ҵ峪 �������̵��� ������ �޼ҵ� ������ �����, ����Ŭ�������� �̷� �޼ҵ带
 *   �ʿ信 �°� �����ؼ� ����ϵ��� �ϴ� ���
 *   
 * ���� UserDao�� �Ϻ��Ѱ�? -> �ƴϴ�.
 * "���"�� ����ߴٴ� ������ �����.
 * ��� ��ü�� �����غ��̰� ����ϱ⿡ ���غ�������, �Ѱ����� ����.
 * �ڹٴ� ���߻���� ������� �ʴ´�.
 * 
 * 1. Ŀ�ؼ� ��ü�� �������� ����� �и��ϱ����� ��ӱ����� ����������, �Ŀ� �ٸ� ��������
 *    UserDao�� ����� �����ϱ� �����.
 * 2. ����� ���� ������ Ŭ������ ���谡 �������� �����ϴ�.
 * 
 * ��Ӱ���� �� ���� ���ɻ翡 ���� ����� ������ ����ϰ� �ִ�.
 * ����Ŭ������ ����Ŭ������ ����� ���� ����� �� �ִ�.
 * -> ����Ŭ������ ������ ������ �����? -> ��� ����Ŭ������ �Բ� �����ؾ��ϰų� �ٽ� �����ؾ��Ѵ�.
 * 
 * 3. Ȯ��� ����� DB Ŀ�ؼ��� �����ϴ� �ڵ带 �ٸ� DAO Ŭ������ ������ ���� ����.
 * -> UserDao ���� DAO Ŭ�������� ��� ���������, ����� ���Ͽ� ������� getConnection()�� �����ڵ尡
 *    �� DAO Ŭ�������� �ߺ��ż� ��Ÿ��������. 
 *    
 *    --> �̰��� �ذ��ؾ��Ѵ�! 
 */
