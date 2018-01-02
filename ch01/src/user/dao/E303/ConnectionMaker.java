package user.dao.E303;

/**
 * 인터페이스를 만들었다!

 */
import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionMaker {

	public abstract Connection makeConnection() throws ClassNotFoundException,
			SQLException;

}