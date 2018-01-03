package user.dao.E402;

/**
 * 팩토리란?
 * = 객체의 생성 방법을 결정하고 만들어진 오브젝트를 돌려주는 것.
 * 
 * UserDaoFactory 클래스를 생성하여 분리한 이유는,
 * 오브젝트를 생성하는 쪽과 생성된 오브젝트를 사용하는 쪽의 역할과 책임을
 * 깔끔하게 분리하려는 목적이다.
 */
public class UserDaoFactory {
	public UserDao userDao() {
		UserDao dao = new UserDao(connectionMaker());
		return dao;
	}

	public ConnectionMaker connectionMaker() {
		ConnectionMaker connectionMaker = new DConnectionMaker();
		return connectionMaker;
	}
}
