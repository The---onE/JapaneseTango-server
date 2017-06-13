package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.Constants;

public class JDBCUtil {
	private String jdbcDriver = "com.mysql.jdbc.Driver";
	private String strCon = Constants.MYSQL_URL + Constants.DATABASE_NAME;
	private String username = Constants.MYSQL_USERNAME;
	private String password = Constants.MYSQL_PASSWORD;

	private static Connection conn = null;
	private PreparedStatement pstm = null;
	private ResultSet rs = null;


	private static JDBCUtil util = null;
	/**
	 * ��ȡJDBCUtil����
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 */
	public static JDBCUtil getInstance() throws ClassNotFoundException {
		if (util == null) {
			util = new JDBCUtil();
			util.initDB();
		}
		return util;
	}

	public void initDB() throws ClassNotFoundException {
		// 1����������
		Class.forName(jdbcDriver);
	}

	/**
	 * �������ݿ�
	 * 
	 * @throws SQLException
	 */
	public boolean connectDB() throws SQLException {
		conn = DriverManager.getConnection(strCon, username, password);
		if (Constants.DEBUG_FLAG) {
			System.out.println(conn != null ? "���ӳɹ�" : "����ʧ��");
		}
		return conn != null;
	}

	/**
	 * ��������
	 * 
	 * @throws SQLException
	 */

	public static void beginTran() throws SQLException {
		conn.setAutoCommit(false);
	}

	/**
	 * �ع�����
	 * 
	 * @throws SQLException
	 */
	public static void rollBack() throws SQLException {
		conn.rollback();
	}

	/**
	 * �ύ����
	 * 
	 * @throws SQLException
	 */
	public static void commitTran() throws SQLException {
		conn.commit();
	}

	/**
	 * �ر�����
	 * 
	 * @throws SQLException
	 */
	public void closeConn() throws SQLException {
		System.out.println("close ......");
		if (rs != null) {
			rs.close();
		}
		if (pstm != null) {
			pstm.close();
		}
		if (conn != null) {
			conn.close();
		}
	}

	/**
	 * ����PrepareStatement�����е�Sql���Ĳ���
	 * 
	 * @throws SQLException
	 */

	private void setPrepareStatementParams(String sql, Object[] params)
			throws SQLException {
		pstm = conn.prepareStatement(sql);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				pstm.setObject(i + 1, params[i]);
			}
		}
	}

	/**
	 * ִ�в�ѯ
	 * 
	 * @param sql
	 *            sql���
	 * @param params
	 *            �����б�
	 * @return ����ResultSet���͵Ĳ�ѯ���
	 * @throws SQLException
	 */
	public ResultSet executeQuery(String sql, Object[] params)
			throws SQLException { // ִ�в�ѯ���ݿ�ӿ�

		util.setPrepareStatementParams(sql, params); // ������
		rs = pstm.executeQuery(); // ִ�в�ѯ����
		return rs;
	}

	/**
	 * ִ�����ݵ���ɾ��
	 * 
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public int executeUpdate(String sql, Object[] params) throws SQLException // ִ���޷������ݵ����ݲ�ѯ������ֵ�Ǳ��ı���������ݿ�����
	{
		int result = -1;
		util.setPrepareStatementParams(sql, params); // ������
		pstm.executeUpdate(); // ִ�и���
		result = 1;
		return result;
	}
}
