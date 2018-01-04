package user.dao.E604;

/**
 * DaoFactory는 런타임 시점에 UserDao가 사용할 ConnectionMaker 타입의 오브젝트를 결정하고 이를 생성한 후에,
 * UserDao의 생성자 파라미터로 주입해서 UserDao가 DConnectionMaker의 오브젝트와 런타임 의존관계를 맺게 해준다.
 * 
 * DaoFactory를 만든 시점에서 의존관계 주입[DI]를 이용하였다.
 * 
 * DaoFactory는 두 오브젝트 사이의 런타임 의존관계를 설정해주는 의존관계 주입 작업을 주도하는 존재이며
 * 동시에 IoC 방식으로 오브젝트의 생성,초기화,제공 등의 작업을 수행하는 컨테이너이다. -> DI 컨테이너

 */
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoFactory {
	@Bean
	public UserDao userDao() {
		// new 연산자에 의해 호출때마다 새로운 오브젝트가 만들어진다.
		// 하지만, getBean()를 두번 호출해서 가져온 오브젝트는 new에 의해 새로운 오브젝트가 만들어지지않는다.
		UserDao dao = new UserDao(connectionMaker());
		// 생성자를 통해 주입받은 DConnectionMaker 오브젝트를 언제든지 사용할 수 있다.
		return dao;
	}

	@Bean
	public ConnectionMaker connectionMaker() {
		ConnectionMaker connectionMaker = new DConnectionMaker();
		return connectionMaker;
	}
}
