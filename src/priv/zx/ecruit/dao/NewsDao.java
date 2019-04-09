package priv.zx.ecruit.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import priv.zx.ecruit.db.DBUtil;
import priv.zx.ecruit.model.News;

/**
 * 新闻表操作Dao
 * 
 *
 */
public class NewsDao {
	/**
	 * 增加一条新闻
	 * @param 一个新闻对象
	 * @throws SQLException
	 */
	public void addNews(News n) throws SQLException{
		//获取连接
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"insert into tb_news " +
				"(news_id,news_title,news_content,news_time) " +
				"values(?,?,?,?) ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		//传入参数
		ptmt.setString(1, n.getNews_id());
		ptmt.setString(2, n.getNews_title());
		ptmt.setString(3, n.getNews_content());
		ptmt.setDate(4, new Date(n.getNews_time().getTime()));
		ptmt.execute();
		ptmt.close();
		DBUtil.close(conn);
	}
	/**
	 * 获取全部新闻
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<News> queryAll() throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"select * from tb_news ";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		ArrayList<News> arrNews = new ArrayList<News>();
		News n = null;
		//赋值给news 对象
		while(rs.next()){
			n = new News();
			n.setNews_id(rs.getString("news_id"));
			n.setNews_title(rs.getString("news_title"));
			n.setNews_content(rs.getString("news_content"));
			n.setNews_time(rs.getDate("news_time"));
			//加入列表中
			arrNews.add(n);
		}
		rs.close();
		stmt.close();

		//返回整个列表


		DBUtil.close(conn);

		return arrNews;
	}
	/**
	 * 修改新闻
	 * @param n
	 * @throws SQLException
	 */
	public void modifyNews(News n) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"update tb_news " +
				"set news_title = ?,news_content = ?,news_time = ? " +
				"where news_id = ? ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, n.getNews_title());
		ptmt.setString(2, n.getNews_content());
		ptmt.setDate(3, new Date(n.getNews_time().getTime()));
		ptmt.setString(4, n.getNews_id());
		ptmt.execute();
		ptmt.close();
		DBUtil.close(conn);
	}
	//删除新闻
	public void delNews(String newsId) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"delete from tb_news " +
				"where news_id = ? ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, newsId);
		ptmt.execute();
		ptmt.close();
		DBUtil.close(conn);
	}
	
	//按时间顺序查询出新闻
	public ArrayList<News> queryNews() throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "select * from tb_news order by news_time desc";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		ArrayList<News> arrNews = new ArrayList<News>();
		News n = null;
		while(rs.next()){
			n = new News();
			n.setNews_id(rs.getString("news_id"));
			n.setNews_title(rs.getString("news_title"));
			n.setNews_content(rs.getString("news_content"));
			n.setNews_time(rs.getDate("news_time"));
			arrNews.add(n);
		}
		rs.close();
		stmt.close();
		DBUtil.close(conn);
		return arrNews;
	}
}
