package Model;
import org.h2.jdbcx.JdbcDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;


public class DBConnexion {
	
	Connection dbConnection;
	
	public DBConnexion() {
		JdbcDataSource dataSource = new JdbcDataSource();
		dataSource.setURL("jdbc:h2:tcp://localhost/~/test");
		dataSource.setUser("sa");
		dataSource.setPassword("sa");
		
		try {
			this.dbConnection=dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	
	public Connection getDbConnection() {
		return dbConnection;
	}


	public void setDbConnection(Connection dbConnection) {
		this.dbConnection = dbConnection;
	}

	
}
