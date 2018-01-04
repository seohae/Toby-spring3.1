package user.dao.E604;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CountingDaoFactory {
	// ��� DAO�� ������ connectionMaker()���� ��������� ������Ʈ�� DI �޴´�.
	@Bean
	public UserDao userDao() {
		return new UserDao(connectionMaker());
	}

	@Bean
	public ConnectionMaker connectionMaker() {
		return new CountingConnectionMaker(realConnectionMaker());
	}

	@Bean
	public ConnectionMaker realConnectionMaker() {
		return new DConnectionMaker();
	}
}

