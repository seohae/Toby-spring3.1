package user.dao.E402;

/**
 * DaoFactory�� ��û�ؼ� �̸� ������� UserDao ������Ʈ�� ������ ����Ѵ�.
 * UserDaoFactory �и� ����,
 * UserDao�� ��� ����������� ��� �ʱ�ȭ�Ǿ� �ִ����� �Ű澲�� �ʰ�
 * ���丮�κ��� UserDao ������Ʈ�� �޾ƴٰ� �ڽ��� ���ɻ��� �׽�Ʈ�� ����
 * Ȱ���ϱ⸸�ϸ� �ȴ�.
 * �� Ŭ������ �ڽ��� å�ӿ��� ����ϵ��� ���ҿ� ���� �и��ϴ� �۾��� �Ͽ���.
 */
import java.sql.SQLException;

import user.domain.User;

public class UserDaoTest {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		UserDao dao = new UserDaoFactory().userDao();

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
