package user.dao.E501;

/**
 * 1. 클라이언트는 구체적인 팩토리 클래스를 알 필요가 없다.
 * 2. 애플리케이션 컨텍스트는 종합 IoC 서비스를 제공해준다.
 * 3. 애플리케이션 컨텍스트는 빈을 검색하는 다양한 방법을 제공한다.
 */
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 애플리케이션 컨텍스트 또는 빈 팩토리가 사용할 설정정보라는 표시
public class DaoFactory {
	// 애플리케이션 컨텍스트는 DaoFactory 클래스를 설정정보로 등록해두고 
	// @Bean이 붙은 메소드의 이름을 가져와 빈 목록을 만들어준다.
	@Bean // 오브젝트 생성을 담당하는 IoC용 메소드라는 표시
	public UserDao userDao() {
		UserDao dao = new UserDao(connectionMaker());
		return dao;
	}

	@Bean // 오브젝트 생성을 담당하는 IoC용 메소드라는 표시
	public ConnectionMaker connectionMaker() {
		ConnectionMaker connectionMaker = new DConnectionMaker();
		return connectionMaker;
	}
}
