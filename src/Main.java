
import java.sql.*;
public class Main {
    private Connection connection;
    private static Statement statement;

    public Main()throws Exception
    {
        Class.forName("oracle.jdbc.OracleDriver");
        connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:30000:FREE","SYSTEM","1234");
        statement = connection.createStatement();
    }

    public void select() throws Exception
    {
        ResultSet rs =  statement.executeQuery("SELECT * FROM PRUEBA");
        ResultSetMetaData md = rs.getMetaData();
        while(rs.next()) {
            String d1 = rs.getString(1);
            String d2 = rs.getString(2);
            println(d1 +"\t\t  "+d2);
        }
        rs.close();
    }

    public void insert(String b) throws SQLException {
        String qry = "INSERT INTO PRUEBA (TXT) VALUES (?)";
        PreparedStatement ps = connection.prepareStatement(qry);
        try{
            ps.setString(1,b);
            ps.executeUpdate();
        }finally {
            ps.close();
        }
    }

    public void update(int a, String b) throws SQLException {
        String qry = "UPDATE PRUEBA SET TXT = ?  where id_prueba = ?";
        PreparedStatement ps = connection.prepareStatement(qry);
        try{
            ps.setString(1,b);
            ps.setInt(2,a);
            ps.executeUpdate();
        }finally {
            ps.close();
        }
    }
    public void close() throws Exception
    {
        statement.close();
        connection.close();
    }

    public void delete(int a ) throws SQLException {
        String qry = "DELETE FROM PRUEBA WHERE ID_PRUEBA = ? ";
        PreparedStatement ps = connection.prepareStatement(qry);
        try{
            ps.setInt(1,a);
            ps.executeUpdate();
        }finally {
            ps.close();
        }
    }

    public static void println(String x){
        System.out.println(x);
    }

    public static void main(String[] args)throws Exception {
        Main m = new Main();
        ResultSet rs =  statement.executeQuery("SELECT * FROM PRUEBA");
        ResultSetMetaData md = rs.getMetaData();
        println(md.getColumnName(1)+" "+ md.getColumnName(2));
        m.select();
        //m.insert("aaa");
        //m.select();
        m.update(0,"hola");
        //m.select();
        m.delete(20);
        m.close();
    }
}