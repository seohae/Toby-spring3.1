package user.dao.E202;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import user.domain.User;

public class UserDaoTest {
	
	// JUnit에게 테스트용 메소드 임을 알려준다.
	@Test 
	public void addAndGet() throws SQLException { // JUnit 테스트 메소드는 반드시 public으로 선언돼야한다.
		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
		UserDao dao = context.getBean("userDao", UserDao.class);
		
		User user = new User();
		// JUnit 테스트로 전환하는 김에 새로운 기분으로 테스트 데이터도 바꿔보자!
		user.setId("gyumee");
		user.setName("박성철");
		user.setPassword("springno1");

		dao.add(user);
			
		User user2 = dao.get(user.getId());
		
		// if문과 같은 기능
		// addAndGet() 에 사용했던 user 오브젝트의 name값과 get()에서 가져온 user2 오브젝트의 name값이 같으면 넘어가고,
		// 아니면 테스트가 실패하게된다.
		assertThat(user2.getName(), is(user.getName()));
		assertThat(user2.getPassword(), is(user.getPassword()));
	}
	
	public static void main(String[] args) {
		JUnitCore.main("springbook.user.dao.UserDaoTest"); // JUnit을 이용해서 테스트를 실행해본다!
	}
}
