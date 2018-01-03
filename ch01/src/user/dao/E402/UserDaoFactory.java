package user.dao.E402;

/**
 * ���丮��?
 * = ��ü�� ���� ����� �����ϰ� ������� ������Ʈ�� �����ִ� ��.
 * 
 * UserDaoFactory Ŭ������ �����Ͽ� �и��� ������,
 * ������Ʈ�� �����ϴ� �ʰ� ������ ������Ʈ�� ����ϴ� ���� ���Ұ� å����
 * ����ϰ� �и��Ϸ��� �����̴�.
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
