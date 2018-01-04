package user.dao.E604;

/**
 * CountingConnectionMaker 클래스
 * - ConnectionMaker 인터페이스를 구현했지만, 내부에서 직접 DB 커넥션을 만들지 않는다.
 * - 대신, DAO가 DB 커넥션을 가져올때마다 호출하는 makeConnection()에서 DB 연결횟수 카운터를 증가시킨다.
 */
import java.sql.Connection;
import java.sql.SQLException;

public class CountingConnectionMaker implements ConnectionMaker {
	int counter = 0;
	private ConnectionMaker realConnectionMaker;
	
	// 생성자
	// -> CountingConnectionMaker도 DI를 받는 것을 알 수 있다.
	public CountingConnectionMaker(ConnectionMaker realConnectionMaker) {
		this.realConnectionMaker = realConnectionMaker;
	}

	public Connection makeConnection() throws ClassNotFoundException, SQLException {
		this.counter++;
		// DB 커넥션을 만들어주는 realConnectionMaker에 저장된 ConnectionMaker 타입의 오브젝트의
		// makeConnection()을 호출해서 그 결과를 DAO에 돌려준다.
		return realConnectionMaker.makeConnection();
	}
	
	public int getCounter() {
		return this.counter;
	}
}
