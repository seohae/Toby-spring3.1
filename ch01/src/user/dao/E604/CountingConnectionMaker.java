package user.dao.E604;

/**
 * CountingConnectionMaker Ŭ����
 * - ConnectionMaker �������̽��� ����������, ���ο��� ���� DB Ŀ�ؼ��� ������ �ʴ´�.
 * - ���, DAO�� DB Ŀ�ؼ��� �����ö����� ȣ���ϴ� makeConnection()���� DB ����Ƚ�� ī���͸� ������Ų��.
 */
import java.sql.Connection;
import java.sql.SQLException;

public class CountingConnectionMaker implements ConnectionMaker {
	int counter = 0;
	private ConnectionMaker realConnectionMaker;
	
	// ������
	// -> CountingConnectionMaker�� DI�� �޴� ���� �� �� �ִ�.
	public CountingConnectionMaker(ConnectionMaker realConnectionMaker) {
		this.realConnectionMaker = realConnectionMaker;
	}

	public Connection makeConnection() throws ClassNotFoundException, SQLException {
		this.counter++;
		// DB Ŀ�ؼ��� ������ִ� realConnectionMaker�� ����� ConnectionMaker Ÿ���� ������Ʈ��
		// makeConnection()�� ȣ���ؼ� �� ����� DAO�� �����ش�.
		return realConnectionMaker.makeConnection();
	}
	
	public int getCounter() {
		return this.counter;
	}
}
