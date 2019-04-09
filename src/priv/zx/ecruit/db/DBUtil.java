package priv.zx.ecruit.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBUtil {    
	//����ȫ�ֱ���
	private static ComboPooledDataSource cpds;
	//��̬����飬��������
	static {
		cpds = new ComboPooledDataSource("mysql");
	}
	//��ȡ����Դ
	public static DataSource getDataSource() {
		return cpds;
	}
	//��ȡ����
	public static Connection getConnection() throws SQLException {
		return cpds.getConnection();
	}
	//�ر�����
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
