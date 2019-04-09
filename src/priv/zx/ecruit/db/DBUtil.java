package priv.zx.ecruit.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBUtil {    
	//定义全局变量
	private static ComboPooledDataSource cpds;
	//静态代码块，命名配置
	static {
		cpds = new ComboPooledDataSource("mysql");
	}
	//获取数据源
	public static DataSource getDataSource() {
		return cpds;
	}
	//获取连接
	public static Connection getConnection() throws SQLException {
		return cpds.getConnection();
	}
	//关闭连接
	public static void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
