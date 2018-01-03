package user.dao.E501;

import java.sql.SQLException;

import user.domain.User;

public class UserDaoTest {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
		// AnnotationConfigApplicationContext의 getBean 메소드를 통해 UserDao의 오브젝트를 가져온다.
		// getBean() 메소드 호출시,
		// 자신의 빈 목록(DaoFactory에서 @Bean)에서 요청한 이름이 있는지 찾고 존재한다면,
		// 빈을 생성하는 메소드를 호출하여 오브젝트를 생성한 후 클라이언트에 돌려준다.
		UserDao dao = context.getBean("userDao", UserDao.class);

		User user = new User();
		user.setId("whiteship");
		user.setName("백기선");
		user.setPassword("married");

		dao.add(user);
			
		System.out.println(user.getId() + " 등록 성공");
		
		User user2 = dao.get(user.getId());
		System.out.println(user2.getName());
		System.out.println(user2.getPassword());
			
		System.out.println(user2.getId() + " 조회 성공");
	}
}
