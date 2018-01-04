package user.dao.E604.sandbox;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import user.dao.E604.DaoFactory;
import user.dao.E604.UserDao;
 
public class SingletonTest {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
		
		// getBean()를 두번 호출해서 가져온 오브젝트는 new에 의해 새로운 오브젝트가 만들어지지 않는다.
		// 스프링은 기본적으로 별다른 설정을 하지않으면 내부에서 생성하는 빈 오브젝트를 모두 싱글톤으로 만든다.
		// 왜 스프링은 싱글톤으로 빈을 만들까?
		// 이는 스프링이 주로 적용되는 대상이 자바 엔터프라이즈 기술을 사용하는 서버환경이기 때문이다.
		// 계속해서 새로운 오브젝트가 만들어지면 서버가 감당하기 힘들기 때문이다.
		// 그래서 애플리케이션 안에 제한된 수, 대개 한개의 오브젝트만 만들어서 사용하는 것. (=싱글톤 패턴의 원리)
		
		// 하지만, 싱글톤 패턴 구현 방식에는 문제가 있다.
		// 1. private 생성자를 갖고있기 때문에 상속할 수 없다.
		// 2. 싱글톤은 테스트 하기 힘들다.
		// 3. 서버환경에서는 싱글톤이 하나만 만들어지는것을 보장하지 못한다.
		// 4. 싱글톤의 사용은 전역상태를 만들 수 있기 때문에 바람직하지 못하다.
		System.out.println(context.getBean(UserDao.class));
		System.out.println(context.getBean(UserDao.class));
	}
} 
