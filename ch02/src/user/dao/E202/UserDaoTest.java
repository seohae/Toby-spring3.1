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
	
	// JUnit���� �׽�Ʈ�� �޼ҵ� ���� �˷��ش�.
	@Test 
	public void addAndGet() throws SQLException { // JUnit �׽�Ʈ �޼ҵ�� �ݵ�� public���� ����ž��Ѵ�.
		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
		UserDao dao = context.getBean("userDao", UserDao.class);
		
		User user = new User();
		// JUnit �׽�Ʈ�� ��ȯ�ϴ� �迡 ���ο� ������� �׽�Ʈ �����͵� �ٲ㺸��!
		user.setId("gyumee");
		user.setName("�ڼ�ö");
		user.setPassword("springno1");

		dao.add(user);
			
		User user2 = dao.get(user.getId());
		
		// if���� ���� ���
		// addAndGet() �� ����ߴ� user ������Ʈ�� name���� get()���� ������ user2 ������Ʈ�� name���� ������ �Ѿ��,
		// �ƴϸ� �׽�Ʈ�� �����ϰԵȴ�.
		assertThat(user2.getName(), is(user.getName()));
		assertThat(user2.getPassword(), is(user.getPassword()));
	}
	
	public static void main(String[] args) {
		JUnitCore.main("springbook.user.dao.UserDaoTest"); // JUnit�� �̿��ؼ� �׽�Ʈ�� �����غ���!
	}
}
