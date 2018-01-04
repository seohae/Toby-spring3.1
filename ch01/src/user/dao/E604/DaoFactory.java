package user.dao.E604;

/**
 * DaoFactory�� ��Ÿ�� ������ UserDao�� ����� ConnectionMaker Ÿ���� ������Ʈ�� �����ϰ� �̸� ������ �Ŀ�,
 * UserDao�� ������ �Ķ���ͷ� �����ؼ� UserDao�� DConnectionMaker�� ������Ʈ�� ��Ÿ�� �������踦 �ΰ� ���ش�.
 * 
 * DaoFactory�� ���� �������� �������� ����[DI]�� �̿��Ͽ���.
 * 
 * DaoFactory�� �� ������Ʈ ������ ��Ÿ�� �������踦 �������ִ� �������� ���� �۾��� �ֵ��ϴ� �����̸�
 * ���ÿ� IoC ������� ������Ʈ�� ����,�ʱ�ȭ,���� ���� �۾��� �����ϴ� �����̳��̴�. -> DI �����̳�

 */
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoFactory {
	@Bean
	public UserDao userDao() {
		// new �����ڿ� ���� ȣ�⶧���� ���ο� ������Ʈ�� ���������.
		// ������, getBean()�� �ι� ȣ���ؼ� ������ ������Ʈ�� new�� ���� ���ο� ������Ʈ�� ����������ʴ´�.
		UserDao dao = new UserDao(connectionMaker());
		// �����ڸ� ���� ���Թ��� DConnectionMaker ������Ʈ�� �������� ����� �� �ִ�.
		return dao;
	}

	@Bean
	public ConnectionMaker connectionMaker() {
		ConnectionMaker connectionMaker = new DConnectionMaker();
		return connectionMaker;
	}
}
