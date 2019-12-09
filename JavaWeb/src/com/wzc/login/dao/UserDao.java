package com.wzc.login.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wzc.login.domain.User;
/**
 * @description 数据库连接与操作类用于增删改查数据并返回给servlet使用
 * @author WANGZIC
 *
 */
public class UserDao {

	/**
	 * 新增用户
	 * @param user 用户信息
	 * @return 新增是否成功
	 */
	public boolean insert(User user) {
		Connection con = getConnection();
		PreparedStatement pstmt =null;
		String sql = "INSERT INTO user(username,nickname,password,gender,phone,email,address) VALUES(?,?,?,?,?,?,?)";
		boolean res = false;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,user.getUsername());
			pstmt.setString(2,user.getNickname());
			pstmt.setString(3,user.getPassword());
			pstmt.setString(4,user.getGender());
			pstmt.setString(5,user.getPhone());
			pstmt.setString(6,user.getEmail());
			pstmt.setString(7,user.getAddress());
			res = (pstmt.executeUpdate()==1);
		}catch (SQLException e) {
			if(!e.getMessage().contains("PRIMARY")){
				e.printStackTrace();
			}else {
				System.out.println("该用户名已存在");
				return false;
			}
		}finally {
			closeCon(con,pstmt);
		}
		return res;

	}

	/**
	 * 根据用户id删除用户信息
	 * @param userid 用户id
	 * @return 删除是否成功
	 */
	public boolean delete(Integer userid) {
		Connection con = getConnection();
		PreparedStatement pstmt =null;
		String sql = "delete from user where userid= ?";
		boolean res = false;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,userid);
			res = (pstmt.executeUpdate()==1);
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			closeCon(con,pstmt);
		}
		return res;
	}

	/**
	 * 根据用户名重置密码
	 * @param username 要修改密码的用户名
	 * @return 修改是否成功
	 */
	public boolean passwordUpdata(String username,String newpassword) {
		Connection con = getConnection();
		PreparedStatement pstmt =null;
		String sql = "update user set password=? where username= ?";
		boolean res = false;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,newpassword);
			pstmt.setString(2,username);
			res = (pstmt.executeUpdate()==1);
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			closeCon(con,pstmt);
		}
		return res;
	}
	
	
	
	/**
	 * 更新用户信息
	 * @param user 修改后的用户信息
	 * @return 更新是否成功
	 */
	public boolean update(User user) {
		Connection con = getConnection();
		PreparedStatement pstmt =null;
		String sql = "update user set username=?,nickname=?,password=?,gender=?,phone=?,email=?,address=? where userid= ?";
		boolean res = false;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,user.getUsername());
			pstmt.setString(2,user.getNickname());
			pstmt.setString(3,user.getPassword());
			pstmt.setString(4,user.getGender());
			pstmt.setString(5,user.getPhone());
			pstmt.setString(6,user.getEmail());
			pstmt.setString(7,user.getAddress());
			pstmt.setInt(8,user.getUserid());
			res = (pstmt.executeUpdate()==1);
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			closeCon(con,pstmt);
		}
		return res;

	}

	/**
	 * 根据用户名查找用户密码
	 * @param username 用户名
	 * @return 用户信息
	 */
	public User selectByUsername(String username){
		List<User> list = new ArrayList<>();
		String sql = "select * from user where username=?";
		Connection con =getConnection();
		PreparedStatement pstmt =null;
		ResultSet rs;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			list=resultSetToBean(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeCon(con,pstmt);
		}
		return list.isEmpty()?null:list.get(0);
	}

	/**
	 * 根据用户id查询用户信息
	 * @param userid 用户id
	 * @return 用户信息
	 */
	public User selectByUserId(Integer userid) {
		List<User> list = new ArrayList<>();
		String sql = "select * from user where userid= ?";
		Connection con =getConnection();
		PreparedStatement pstmt =null;
		ResultSet rs;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,userid);
			rs = pstmt.executeQuery();
			list=resultSetToBean(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeCon(con,pstmt);
		}
		return list.isEmpty()?null:list.get(0);
	}

	/**
	 * 根据传入的参数查询用户
	 * @param paramMap 参数Map
	 * @return 用户列表
	 */
	public List<User> selectByMap(Map<String,Object> paramMap) {
		List<User> list = new ArrayList<>();
		StringBuilder sql =new StringBuilder("select * from user where 1=1");
		List<Object> paramList = new ArrayList<>();
		for(String key:paramMap.keySet()){
			sql.append(" and ").append(key).append(" = ?");
			paramList.add(paramMap.get(key));
		}
		Connection con =getConnection();
		PreparedStatement pstmt =null;
		try {
			pstmt = con.prepareStatement(sql.toString());
			for(int i=0;i<paramList.size();i++){
				pstmt.setObject(i,paramList.get(i));
			}
			list=resultSetToBean(pstmt.executeQuery());
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeCon(con,pstmt);
		}
		return list;
	}

	/**
	 * 将查询结果转换位对象
	 * @param rs 查询结果
	 * @return 用户列表
	 * @throws SQLException
	 */
	private static List<User> resultSetToBean(ResultSet rs) throws SQLException {
		List<User> list = new ArrayList<>();
		while (rs.next()){
			User user = new User();
			user.setUserid(rs.getInt("userid"));
			user.setUsername(rs.getString("username"));
			user.setNickname(rs.getString("nickname"));
			user.setPassword(rs.getString("password"));
			user.setPhone(rs.getString("phone"));
			user.setGender(rs.getString("gender"));
			user.setEmail(rs.getString("email"));
			user.setAddress(rs.getString("address"));
			list.add(user);
		}
		return list;
	}


	/**
	 * 获取JDBC连接
	 */
	private static Connection getConnection(){
		//记得依赖mysql-jdbc驱动包
		String driver ="com.mysql.jdbc.Driver";
		//修改为自己的数据库
		String url ="jdbc:mysql://localhost:3306/users";
		//修改未自己数据库的用户名密码
		String user ="root";
		//修改未自己数据库的名密码
		String password ="root";
		Connection connection =null;
		try {
			Class.forName(driver);
			connection =DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	/**
	 * 关闭JDBC连接
	 * @param con 连接实例
	 * @param pstmt PreparedStatement实例
	 */
	private static void closeCon(Connection con, PreparedStatement pstmt){
		try {
			if(pstmt!=null) {
				pstmt.close();
			}
			if(con!=null) {
				con.close();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 测试数据库连接是否正常
	 * @param args
	 */
//	public static void main(String[] args) {
//		System.out.println(new UserDao().selectByUsername("123"));
//		System.out.println(new UserDao().selectByMap(new HashMap<>(0)));
//	}
	
}
