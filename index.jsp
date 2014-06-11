<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.Statement"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="teatapi1.Add" %>
<jsp:directive.page import="java.sql.Date" />
<jsp:directive.page import="java.sql.Timestamp" />
<jsp:directive.page import="java.sql.SQLException"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>My JSP 'listPerson.jsp' starting page</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<style type="text/css">body, td, th, input {font-size:12px; text-align:center; }</style>
	</head>
	<body>

		<table align=right>
			<tr>

				<td><a href="addPerson.jsp">
				<%
				int aa = 5;
				int bb =3;
				Add ad = new Add();
				int cc= ad.addnumber(aa,bb);
				out.println(cc);
				%></a>
				</td>
			</tr>
		</table>
		<br />
		<br />
<%
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	try{
		// 註冊 MySQL 驅動. 也可以使用下面兩種方式的任一種
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		//new com.mysql.jdbc.Driver();
		//Class.forName("com.mysql.jdbc.Driver").newInstance();
		
		// 獲得資料庫連接。 三個參數分別為 連接URL，使用者名稱，密碼
		conn = DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/nblog10", 
							"nblogTW", 
							"nblogTW");
		
		// 獲得 Statement。 Statement 對像用於執行 SQL。相當於控制台。
		stmt = conn.createStatement();
		
		String q_sql = "select a.o_index,a.m_index,a.o_time,b.m_name,";
		q_sql +="c.o_index, d.p_name, d.p_price from m_order as a";
		q_sql +="inner join member as b";
		q_sql +="on a.m_index = b.m_index";
		q_sql +="inner join m_orderDetail as c";
		q_sql +="on a.o_index = c.o_index";
		q_sql +="inner join product as d";
		q_sql +="on c.p_index = d.p_index";
		
		// 使用 Statement 執行 SELECT 敘述。傳回結果集。
		rs = stmt.executeQuery("select * from n2_member");	
%>
		<form action="operatePerson.jsp" method=get>
			<table bgcolor="#CCCCCC" cellspacing=1 cellpadding=5 width=100%>
				<tr bgcolor=#DDDDDD>
					<th></th>
					<th>
						ID
					</th>
					<th>
						姓名
					</th>
					<th>
						英文名
					</th>
					<th>
						性別
					</th>
					<th>
						年齡
					</th>
					<th>
						生日
					</th>
					<th>
						備註
					</th>
					<th>
						記錄建立時間
					</th>
					<th>
						操作
					</th>
				</tr>
				<%
					// 檢查結果集。rs.next() 傳回結果集中是否還有下一條記錄。如果有，自動捲動到下一條記錄並傳回 true
					while (rs.next()) {

						int o_index = rs.getInt("o_index"); // 整形類型
						int m_index = rs.getInt("m_index");
						String name = rs.getString("m_name"); // 字串類型
						int p_index = rs.getInt("p_index");
						Date birthday = rs.getDate("birthday"); // 日期類型，只有日期資訊而沒有時間資訊
						String p_price = rs.getString("p_price"); // 字串類型
						
						out.println("		<tr bgcolor=#FFFFFF>");
						out.println("	<td><input type=checkbox name=id value=" + id
						+ "></td>");
						out.println("	<td>" + o_index + "</td>");
						out.println("	<td>" + m_index + "</td>");
						out.println("	<td>" + m_name + "</td>");
						out.println("	<td>" + i_index + "</td>");
						out.println("	<td>" + i_price + "</td>");
						//
						//
						//
						out.println("	<td>");
						out.println("		<a href='operatePerson.jsp?action=del&id="
						+ o_index + "' onclick='return confirm(\"確定刪除該記錄？\")'>刪除</a>");
						out.println("		<a href='operatePerson.jsp?action=edit&id="
						+ o_index + "'>修改</a>");
						out.println("	</td>");
						out.println("		</tr>");

					}
				%>
			</table>
			<table align=left>
				<tr>
					<td>
						<input type='hidden' value='del' name='action'>
						<a href='#'
							onclick="var array=document.getElementsByName('id');for(var i=0; i<array.length;
							i++){array[i].checked=true;}">全選</a>
						<a href='#'
							onclick="var array=document.getElementsByName('id');for(var i=0; i<array.length;
							i++){array[i].checked=false;}">取消全選</a>
						<input type='submit'
							onclick="return confirm('即將刪除所選擇的記錄。是否刪除？'); " value='刪除'>
					</td>
				</tr>
			</table>
		</form>
<%
	}catch(SQLException e){
		out.println("發生了例外：" + e.getMessage());
		e.printStackTrace();
	}finally{
			// 關閉
			if(rs != null)
				rs.close();
			if(stmt != null)
				stmt.close();
			if(conn != null)
				conn.close();
	}
%>
	</body>
</html>
