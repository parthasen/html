package testConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.ResultSet;



public class testConnection {
	//
	// Creat some variables to store data. 
	private String Url = "jdbc:mysql://localhost:3306/test";;
	private String User = "David";
	private String Password = "8065";
	

	
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	//�U���O�z�L ResultSetMetaData �ӱ� rs ���槹 sql�����G�C
	// ���ŧirsmd�A��rs���Ȥ���A�h��rs
	ResultSetMetaData rsmd = null;
	public List dbCatch(){

		List list = new ArrayList();
		try{
			// ���U MySQL �X��. �]�i�H�ϥΤU����ؤ覡�����@��
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			//new com.mysql.jdbc.Driver();
			//Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			// ��o��Ʈw�s���C �T�ӰѼƤ��O�� �s��URL�A�ϥΪ̦W�١A�K�X
			/*conn = DriverManager.getConnection(
								"jdbc:mysql://localhost:3306/test", 
								"David", 
								"8065");*/
			conn = DriverManager.getConnection(
								Url, 
								User, 
								Password);
			// ��o Statement�C Statement �ﹳ�Ω���� SQL�C�۷�󱱨�x�C
			stmt = conn.createStatement();
			
			String q_sql = "select a.o_index,a.m_index,a.o_time,b.m_name,";
			q_sql +="c.o_index, d.p_name, d.p_price from m_order as a ";
			q_sql +="inner join member as b ";
			q_sql +="on a.m_index = b.m_index ";
			q_sql +="inner join m_orderDetail as c ";
			q_sql +="on a.o_index = c.o_index ";
			q_sql +="inner join product as d ";
			q_sql +="on c.p_index = d.p_index";
			
			// �ϥ� Statement ���� SELECT �ԭz�C�Ǧ^���G���C
			rs = stmt.executeQuery(q_sql);	
			 rsmd = rs.getMetaData();
			
						// �ˬd���G���Crs.next() �Ǧ^���G�����O�_�٦��U�@���O���C�p�G���A�۰ʱ��ʨ�U�@���O���öǦ^ true
						while (rs.next()) {
							
							// Map �ݭn�qjava.util.HashMap import
							// Map �S�ʬ��@��key �����@�� value
							Map map = new HashMap(); 
							int columnCount = rsmd.getColumnCount();
							for(int i=0;i<columnCount;i++){
								String columnName = rsmd.getColumnName(i+1);
								map.put(columnName, rs.getObject(i+1));
							}
							list.add(map);
							
							/*
							int id = rs.getInt("o_index"); // �������  //2014.6.14 Let id = order index. 
										//	  // Next time, u can change the value of id
							int m_index = rs.getInt("m_index");
							Date o_time = rs.getDate("o_time"); // ��������A�u�������T�ӨS���ɶ���T
							String m_name = rs.getString("m_name"); // �r������
							String p_price = rs.getString("p_price"); // �r������
							
							System.out.println("		<tr bgcolor=#FFFFFF>");
							System.out.println("	<td><input type=checkbox name=id value=" + id
							+ "></td>");
							System.out.println("	<td>" + id + "</td>");
							System.out.println("	<td>" + m_index + "</td>");
							System.out.println("	<td>" + m_name + "</td>");
							//out.println("	<td>" + p_index + "</td>");
							System.out.println("	<td>" + p_price + "</td>");
							//
							//
							//
							System.out.println("	<td>");
							System.out.println("		<a href='operatePerson.jsp?action=del&id="
							+ id + "' onclick='return confirm(\"�T�w�R���ӰO���H\")'>�R��</a>");
							System.out.println("		<a href='operatePerson.jsp?action=edit&id="
							+ id + "'>�ק�</a>");
							System.out.println("	</td>");
							System.out.println("		</tr>");
							*/
						}

		}catch(SQLException e){
			System.out.println("�o�ͤF�ҥ~�G" + e.getMessage());
			e.printStackTrace();
		}finally{
				// ����
				if(rs != null)
					try {
						rs.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				if(stmt != null)
					try {
						stmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				if(conn != null)
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		}
		return list;
	}
	
	
}
