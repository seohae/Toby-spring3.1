package user.dao.E604;

/**
 * �̱��� ������Ʈ�� : �������� ���� �̱��� ������ ������Ʈ�� �����ϰ� ����� �����Ѵ�.
 * ���� : static �޼ҵ�� private �����ڸ� ����ؾ��ϴ� ���������� Ŭ������ �ƴ϶�,
 * ����� �ڹ� Ŭ������ �̱������� Ȱ���ϰ� ���ش�.
 * �̱��� ������� ���� ���ø����̼� Ŭ������ public �����ڸ� ���� �� �ִ�.
 */

/**
 * �������� DI
 * = ����) A�� B�� �����ϰ� �ִ�.
 * -> B�� ����� �߰��ǰų� ����ǰų� ������ �ٲ�ų� �ϸ�, �� ������ A�� ���޵ȴ�.
 * 
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import user.domain.User;

public class UserDao {
	// �ν��Ͻ� ������ ���
	// connectionMaker�� �б� ������ �����̱� ������ �ν��Ͻ� ������ �����ؼ� ����ص� �������.
	
	// DI [ UserDao�� ConnectionMaker�� �����ϰ� �ִ� ����]
	// -> UserDao�� �������̽� ConnectionMaker���� �����ϰ� �ִ�.
	// -> �������̽��� ���Ѵٸ� �� ������ UserDao�� ���������� �ްԵ�����,
	//    ConnectionMaker �������̽��� ������ Ŭ����, �� DConnetionMaker ���� �ٸ������� �ٲ�ų�
	//    �� ���ο��� ����ϴ� �޼ҵ忡 ��ȭ�� ���ܵ� UserDao�� ������ ���� �ʴ´�.
	private ConnectionMaker connectionMaker;
	
	public UserDao(ConnectionMaker simpleConnectionMaker) {
		this.connectionMaker = simpleConnectionMaker;
	}

	public void add(User user) throws ClassNotFoundException, SQLException {
		Connection c = this.connectionMaker.makeConnection();

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
		Connection c = this.connectionMaker.makeConnection();
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

	

}
