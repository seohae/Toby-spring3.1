package user.dao.E402;

/**
 * DaoFactory에 요청해서 미리 만들어진 UserDao 오브젝트를 가져와 사용한다.
 * UserDaoFactory 분리 이후,
 * UserDao가 어떻게 만들어지는지 어떻게 초기화되어 있는지에 신경쓰지 않고
 * 팩토리로부터 UserDao 오브젝트를 받아다가 자신의 관심사인 테스트를 위해
 * 활용하기만하면 된다.
 * 각 클래스는 자신의 책임에만 충실하도록 역할에 따라 분리하는 작업을 하였다.
 */
import java.sql.SQLException;

import user.domain.User;

public class UserDaoTest {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		UserDao dao = new UserDaoFactory().userDao();

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
