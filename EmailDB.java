import java.io.*;
import java.sql.*;
import java.util.*;
import com.mysql.cj.jdbc.*;
class EmailDB
{
static Connection c;
Statement st;
PreparedStatement ps;
ResultSet rs;
String q;
boolean cstat;
EmailDB()
{
try
{
c=null;
Class.forName("com.mysql.cj.jdbc.Driver");
c=DriverManager.getConnection("jdbc:mysql://localhost:/mysql","root","");
cstat=true;
}
catch(Exception e)
{
cstat=false;
}
create();
}

void create()
{
try
{
st=c.createStatement();
q="create table if not exists locker";
q+="(user varchar(40) primary key,";
q+="password varchar(20))";
//q+="OTP integer(4)),";
st.execute(q);
cstat=true;
}
catch(SQLException e)
{
cstat=false;
}
}

boolean insert(String u,String p)
{
q="insert into locker values(?,?)";
try
{
ps=c.prepareStatement(q);
ps.setString(1,u);
ps.setString(2,p);
return (ps.executeUpdate()>0);
}
catch(Exception e)
{
return false;
}
}

boolean searchUser(String u)
{
q="select user from locker where user=?";
try
{
ps=c.prepareStatement(q);
ps.setString(1,u);
rs=ps.executeQuery();
return (rs.next());
}
catch(Exception e)
{
return false;
}
}

String getPassword(String u)
{
q="select password from locker where user=?";
try
{
ps=c.prepareStatement(q);
ps.setString(1,u);
rs=ps.executeQuery();
return (rs.next())?(rs.getString(1)):null;
}
catch(Exception e)
{
return null;
}
}

boolean setPassword(String u,String p)
{
q="update locker set password=? where user=?";
try
{
ps=c.prepareStatement(q);
ps.setString(1,p);
ps.setString(2,u);
return (ps.executeUpdate()>0);
}
catch(Exception e)
{
return false;
}
}
}