package user.dao.E501;

import java.sql.SQLException;

import user.domain.User;

public class UserDaoTest {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
		// AnnotationConfigApplicationContext�� getBean �޼ҵ带 ���� UserDao�� ������Ʈ�� �����´�.
		// getBean() �޼ҵ� ȣ���,
		// �ڽ��� �� ���(DaoFactory���� @Bean)���� ��û�� �̸��� �ִ��� ã�� �����Ѵٸ�,
		// ���� �����ϴ� �޼ҵ带 ȣ���Ͽ� ������Ʈ�� ������ �� Ŭ���̾�Ʈ�� �����ش�.
		UserDao dao = context.getBean("userDao", UserDao.class);

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
