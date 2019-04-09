package priv.zx.ecruit.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import priv.zx.ecruit.db.DBUtil;
import priv.zx.ecruit.model.Comment;

/**
 * 评论数据库操作dao
 * @author zx
 *
 */
public class CommentDao {
	
	public void addComment(Comment c) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"insert into tb_comment " +
				"(stuUsername,epUsername,relation,content,date) " +
				"values(?,?,?,?,?) ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, c.getStuUsername());
		ptmt.setString(2,c.getEpUsername());
		ptmt.setString(3, c.getRelation());
		ptmt.setString(4, c.getContent());
		ptmt.setDate(5, new Date(c.getDate().getTime()));
		ptmt.execute();
		ptmt.close();
		DBUtil.close(conn);
	}
	
	//按时间降序查询出该公司的评论
	public ArrayList<Comment> queryComment(String epUsername) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"select * from tb_comment " +
				"where epUsername = ? " + 
				"order by date desc ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, epUsername);
		ResultSet rs = ptmt.executeQuery();
		ArrayList<Comment> arrComm = new ArrayList<Comment>();
		Comment c = null;
		while(rs.next()){
			c = new Comment();
			c.setStuUsername(rs.getString("stuUsername"));
			c.setEpUsername(rs.getString("epUsername"));
			c.setRelation(rs.getString("relation"));
			c.setContent(rs.getString("content"));
			c.setDate(rs.getDate("date"));
			arrComm.add(c);
		}
		rs.close();
		ptmt.close();
		DBUtil.close(conn);
		return arrComm;
	}
	
	//管理员查询出所有的评论
	public ArrayList<Comment> queryAll() throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"select * from tb_comment " +
				"order by date desc ";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		ArrayList<Comment> arrComm = new ArrayList<Comment>();
		Comment c = null;
		while(rs.next()){
			c = new Comment();
			c.setStuUsername(rs.getString("stuUsername"));
			c.setEpUsername(rs.getString("epUsername"));
			c.setRelation(rs.getString("relation"));
			c.setContent(rs.getString("content"));
			c.setDate(rs.getDate("date"));
			arrComm.add(c);
		}
		rs.close();
		stmt.close();
		DBUtil.close(conn);
		return arrComm;
	}
	
	//删除指定的评论
	public void delComment(String stuUsername,String epUsername,String content) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"delete from tb_comment " +
				"where stuUsername = ? and epUsername = ? and content = ? ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, stuUsername);
		ptmt.setString(2, epUsername);
		ptmt.setString(3, content);
		ptmt.execute();
		ptmt.close();
		DBUtil.close(conn);
	}
	
	//删除某公司的所有评论
	public void delCommentOfEP(String epUsername) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"delete from tb_comment " +
				"where epUsername = ? ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, epUsername);
		ptmt.execute();
		ptmt.close();
		DBUtil.close(conn);
	}
	
	//删除某毕业生的所有评论
	public void delCommentOfStu(String stuUsername) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "" +
				"delete from tb_comment " +
				"where stuUsername = ? ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, stuUsername);
		ptmt.execute();
		ptmt.close();
		DBUtil.close(conn);
	}
}
