package user.dao.E604.sandbox;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import user.dao.E604.DaoFactory;
import user.dao.E604.UserDao;
 
public class SingletonTest {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
		
		// getBean()�� �ι� ȣ���ؼ� ������ ������Ʈ�� new�� ���� ���ο� ������Ʈ�� ��������� �ʴ´�.
		// �������� �⺻������ ���ٸ� ������ ���������� ���ο��� �����ϴ� �� ������Ʈ�� ��� �̱������� �����.
		// �� �������� �̱������� ���� �����?
		// �̴� �������� �ַ� ����Ǵ� ����� �ڹ� ������������ ����� ����ϴ� ����ȯ���̱� �����̴�.
		// ����ؼ� ���ο� ������Ʈ�� ��������� ������ �����ϱ� ����� �����̴�.
		// �׷��� ���ø����̼� �ȿ� ���ѵ� ��, �밳 �Ѱ��� ������Ʈ�� ���� ����ϴ� ��. (=�̱��� ������ ����)
		
		// ������, �̱��� ���� ���� ��Ŀ��� ������ �ִ�.
		// 1. private �����ڸ� �����ֱ� ������ ����� �� ����.
		// 2. �̱����� �׽�Ʈ �ϱ� �����.
		// 3. ����ȯ�濡���� �̱����� �ϳ��� ��������°��� �������� ���Ѵ�.
		// 4. �̱����� ����� �������¸� ���� �� �ֱ� ������ �ٶ������� ���ϴ�.
		System.out.println(context.getBean(UserDao.class));
		System.out.println(context.getBean(UserDao.class));
	}
} 
