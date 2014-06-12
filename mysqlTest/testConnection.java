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
	//下面是透過 ResultSetMetaData 來接 rs 執行完 sql的結果。
	// 先宣告rsmd，等rs有值之後再去接rs
	ResultSetMetaData rsmd = null;
	public List dbCatch(){

		List list = new ArrayList();
		try{
			// 註冊 MySQL 驅動. 也可以使用下面兩種方式的任一種
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			//new com.mysql.jdbc.Driver();
			//Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			// 獲得資料庫連接。 三個參數分別為 連接URL，使用者名稱，密碼
			/*conn = DriverManager.getConnection(
								"jdbc:mysql://localhost:3306/test", 
								"David", 
								"8065");*/
			conn = DriverManager.getConnection(
								Url, 
								User, 
								Password);
			// 獲得 Statement。 Statement 對像用於執行 SQL。相當於控制台。
			stmt = conn.createStatement();
			
			String q_sql = "select a.o_index,a.m_index,a.o_time,b.m_name,";
			q_sql +="c.o_index, d.p_name, d.p_price from m_order as a ";
			q_sql +="inner join member as b ";
			q_sql +="on a.m_index = b.m_index ";
			q_sql +="inner join m_orderDetail as c ";
			q_sql +="on a.o_index = c.o_index ";
			q_sql +="inner join product as d ";
			q_sql +="on c.p_index = d.p_index";
			
			// 使用 Statement 執行 SELECT 敘述。傳回結果集。
			rs = stmt.executeQuery(q_sql);	
			 rsmd = rs.getMetaData();
			
						// 檢查結果集。rs.next() 傳回結果集中是否還有下一條記錄。如果有，自動捲動到下一條記錄並傳回 true
						while (rs.next()) {
							
							// Map 需要從java.util.HashMap import
							// Map 特性為一個key 對應一個 value
							Map map = new HashMap(); 
							int columnCount = rsmd.getColumnCount();
							for(int i=0;i<columnCount;i++){
								String columnName = rsmd.getColumnName(i+1);
								map.put(columnName, rs.getObject(i+1));
							}
							list.add(map);
							
							/*
							int id = rs.getInt("o_index"); // 整形類型  //2014.6.14 Let id = order index. 
										//	  // Next time, u can change the value of id
							int m_index = rs.getInt("m_index");
							Date o_time = rs.getDate("o_time"); // 日期類型，只有日期資訊而沒有時間資訊
							String m_name = rs.getString("m_name"); // 字串類型
							String p_price = rs.getString("p_price"); // 字串類型
							
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
							+ id + "' onclick='return confirm(\"確定刪除該記錄？\")'>刪除</a>");
							System.out.println("		<a href='operatePerson.jsp?action=edit&id="
							+ id + "'>修改</a>");
							System.out.println("	</td>");
							System.out.println("		</tr>");
							*/
						}

		}catch(SQLException e){
			System.out.println("發生了例外：" + e.getMessage());
			e.printStackTrace();
		}finally{
				// 關閉
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
