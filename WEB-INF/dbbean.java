import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import teatapi1.Add;
import java.sql.ResultSet;

public class todo{
	todo(){
	try{
		// ���U MySQL �X��. �]�i�H�ϥΤU����ؤ覡�����@��
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		//new com.mysql.jdbc.Driver();
		//Class.forName("com.mysql.jdbc.Driver").newInstance();
		
		// ��o��Ʈw�s���C �T�ӰѼƤ��O�� �s��URL�A�ϥΪ̦W�١A�K�X
		conn = DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/nblog10", 
							"David", 
							"8065");
		
		// ��o Statement�C Statement �ﹳ�Ω���� SQL�C�۷�󱱨�x�C
		stmt = conn.createStatement();
		
		String q_sql = "select a.o_index,a.m_index,a.o_time,b.m_name,";
		q_sql +="c.o_index, d.p_name, d.p_price from m_order as a";
		q_sql +="inner join member as b";
		q_sql +="on a.m_index = b.m_index";
		q_sql +="inner join m_orderDetail as c";
		q_sql +="on a.o_index = c.o_index";
		q_sql +="inner join product as d";
		q_sql +="on c.p_index = d.p_index";
		
		// �ϥ� Statement ���� SELECT �ԭz�C�Ǧ^���G���C
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
						�m�W
					</th>
					<th>
						�^��W
					</th>
					<th>
						�ʧO
					</th>
					<th>
						�~��
					</th>
					<th>
						�ͤ�
					</th>
					<th>
						�Ƶ�
					</th>
					<th>
						�O���إ߮ɶ�
					</th>
					<th>
						�ާ@
					</th>
				</tr>
				<%
					// �ˬd���G���Crs.next() �Ǧ^���G�����O�_�٦��U�@���O���C�p�G���A�۰ʱ��ʨ�U�@���O���öǦ^ true
					while (rs.next()) {

						int o_index = rs.getInt("o_index"); // �������
						int m_index = rs.getInt("m_index");
						Date o_time = rs.getDate("o_time"); // ��������A�u�������T�ӨS���ɶ���T
						String m_name = rs.getString("m_name"); // �r������
						String p_price = rs.getString("p_price"); // �r������
						
						out.println("		<tr bgcolor=#FFFFFF>");
						out.println("	<td><input type=checkbox name=id value=" + id
						+ "></td>");
						out.println("	<td>" + o_index + "</td>");
						out.println("	<td>" + m_index + "</td>");
						out.println("	<td>" + m_name + "</td>");
						out.println("	<td>" + p_index + "</td>");
						out.println("	<td>" + p_price + "</td>");
						//
						//
						//
						out.println("	<td>");
						out.println("		<a href='operatePerson.jsp?action=del&id="
						+ o_index + "' onclick='return confirm(\"�T�w�R���ӰO���H\")'>�R��</a>");
						out.println("		<a href='operatePerson.jsp?action=edit&id="
						+ o_index + "'>�ק�</a>");
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
							i++){array[i].checked=true;}">����</a>
						<a href='#'
							onclick="var array=document.getElementsByName('id');for(var i=0; i<array.length;
							i++){array[i].checked=false;}">��������</a>
						<input type='submit'
							onclick="return confirm('�Y�N�R���ҿ�ܪ��O���C�O�_�R���H'); " value='�R��'>
					</td>
				</tr>
			</table>
		</form>
<%
	}catch(SQLException e){
		out.println("�o�ͤF�ҥ~�G" + e.getMessage());
		e.printStackTrace();
	}finally{
			// ����
			if(rs != null)
				rs.close();
			if(stmt != null)
				stmt.close();
			if(conn != null)
				conn.close();
	}
%>
	}
}