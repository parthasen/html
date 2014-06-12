<%@ page contentType="text/html;charset=utf-8"%>

<%@ page import="java.sql.*"%> 
<% 
try
{
//讀取mysqlDriver驅動程式
Class.forName("org.gjt.mm.mysql.Driver").newInstance();

try
{
//連接mysql資料庫

// 資料庫名稱"zend_test",帳號"root",密碼""(無),
// 使用Unicode編碼"true",字元集"UTF-8"
String db_user="David";
String db_pwd="8065";
String db_database="test_jsdb";
Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/"+db_database+"?user="+db_user+"&password="+db_pwd+"&useUnicode=true&characterEncoding=UTF-8"); 

try
{
//建立statement
Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
try
{
//建立SQL查詢
String sql="select * from employee"; 
ResultSet rs = stmt.executeQuery(sql);

%>

<h2>Employee</h2>
<table border=1>
<tr><th>id</th><th>artist</th><th>title</th></tr> 
<%
//顯示資料

while(rs.next())
{
%>
<tr>
<td><%=rs.getString("emp_id")%></td><td><%=rs.getString("emp_name")%></td><td><%=rs.getString("emp_age")%></td>
</tr>
<% 
}
%>
</table>
<%

// 關閉連線
rs.close();
rs = null;
stmt.close();
stmt = null;
conn.close();

}
catch (Exception ex)
{
out.println("can't read data");
out.println(ex.toString());
}
}
catch (Exception e)
{
out.println("can't create statement");
out.println(e.toString());
}
}
catch(Exception e)
{
out.println("can't content mysql database");
out.println(e.toString());
}

}
catch(Exception e)
{
out.println("can't load mysql driver");
out.println(e.toString());
}

%>