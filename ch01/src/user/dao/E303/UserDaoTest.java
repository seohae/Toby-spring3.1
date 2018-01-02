package user.dao.E303;

import java.sql.SQLException;

import user.domain.User;

public class UserDaoTest {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// UserDao�� ����� ConnectionMaker ���� Ŭ������ �����ϰ� ������Ʈ�� �����
		ConnectionMaker connectionMaker = new DConnectionMaker();
		
		// 1. UserDao ����
		// 2. ����� ConnectionMaker Ÿ���� ������Ʈ�� ���� 
		// -> �ᱹ, �� ������Ʈ ������ �������� ���� ȿ��
		// �̷ν� UserDao�� �����ڸ� �����Ͽ���!
		UserDao dao = new UserDao(connectionMaker);

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
