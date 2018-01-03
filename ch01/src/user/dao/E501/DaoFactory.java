package user.dao.E501;

/**
 * 1. Ŭ���̾�Ʈ�� ��ü���� ���丮 Ŭ������ �� �ʿ䰡 ����.
 * 2. ���ø����̼� ���ؽ�Ʈ�� ���� IoC ���񽺸� �������ش�.
 * 3. ���ø����̼� ���ؽ�Ʈ�� ���� �˻��ϴ� �پ��� ����� �����Ѵ�.
 */
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // ���ø����̼� ���ؽ�Ʈ �Ǵ� �� ���丮�� ����� ����������� ǥ��
public class DaoFactory {
	// ���ø����̼� ���ؽ�Ʈ�� DaoFactory Ŭ������ ���������� ����صΰ� 
	// @Bean�� ���� �޼ҵ��� �̸��� ������ �� ����� ������ش�.
	@Bean // ������Ʈ ������ ����ϴ� IoC�� �޼ҵ��� ǥ��
	public UserDao userDao() {
		UserDao dao = new UserDao(connectionMaker());
		return dao;
	}

	@Bean // ������Ʈ ������ ����ϴ� IoC�� �޼ҵ��� ǥ��
	public ConnectionMaker connectionMaker() {
		ConnectionMaker connectionMaker = new DConnectionMaker();
		return connectionMaker;
	}
}
