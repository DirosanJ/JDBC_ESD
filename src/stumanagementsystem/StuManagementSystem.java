/* 
 * Author : DirosanJ
 * Email : dirosan06@gmail.com
 * Date : 29th June 2024
 * 
 * Description : System Management System 
 */
package stumanagementsystem;
import java.sql.*;
import java.util.*; 


public class StuManagementSystem {
    private static DBConfig2 dbconfig=  DBConfig2.getInstance();
    
    
    public static void main(String[] args) throws Exception {
       
         insertStudent();
         
    }
    
    public static Connection dbConnection() throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url= "jdbc:mysql://localhost:3306/devAcademy";
        String user="root";
        String password="Diro2002";
        Connection con=DriverManager.getConnection(url, user, password);
        return con;
    }
    
    public static void getAllStudent() throws Exception{
       
        String sql="SELECT * from student";
        try (Connection con = dbconfig.dbconnection()) {
            Statement st=con.createStatement();
            ResultSet rs= st.executeQuery(sql);
            
            String name="";
            while(rs.next())
            {
                name=rs.getString(2);
                System.out.println(name);
            }
        }
        
    }
    
    public static void insertStudent() throws Exception {
    

        String name= "Dirosan";
        int age =22;
        String department="CyberSecurity";
        String district="Vavuniya";
        String nic="200216901653";
        String gender="Male";
        int performance=95;
        
        String sql="INSERT INTO student (name,age,department,district,nic,gender,performance)"
                + " VALUES (?,?,?,?,?,?,?)";
        try (Connection con = dbconfig.dbconnection()) {
            
            PreparedStatement ps=con.prepareStatement(sql);
                ps.setString(1, name);
                ps.setInt(2, age);
                ps.setString(3, department);
                ps.setString(4, district);
                ps.setString(5, nic);
                ps.setString(6, gender);
                ps.setInt(7, performance);
           int row= ps.executeUpdate();
            System.out.println(row);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
    public static void deleteStudent() throws Exception{
          
        
        Connection con = dbconfig.dbconnection();
            int id=6;
            String sql="DELETE from student WHERE id= ? ";
            
            PreparedStatement st=con.prepareStatement(sql);
            st.setInt(1, id);
            int row = st.executeUpdate();
            System.out.println(row); 
            
            
    }
    
    public static void callGetAllStudent() throws Exception{
        Connection con = dbconfig.dbconnection();
        
        CallableStatement cs =con.prepareCall("{call GetAll()}");
        
        ResultSet rs=cs.executeQuery();
        
        
        String name="";
            while(rs.next())
            {
                name=rs.getString(2);
                System.out.println(name);
            }
          
            
          
    }
    
    public static void callGetById() throws Exception{
          Connection con = dbconfig.dbconnection();
          int id=2;
           CallableStatement cs =con.prepareCall("{call GetByID(?)}");
           cs.setInt(1, id);
           ResultSet rs=cs.executeQuery();
           rs.next();
           System.out.println(rs.getString(2));
    }
    
    public static void callGetNameById() throws Exception{
          Connection con = dbconfig.dbconnection();
          int id=4;
           CallableStatement cs =con.prepareCall("{call GetNameByID(?,?)}");
           cs.setInt(1, id);
           cs.registerOutParameter(2,Types.VARCHAR);
           cs.executeUpdate();
         
           System.out.println(cs.getString(2));
    }
    
    public static void commitPractice() throws Exception {
          Connection con = dbconfig.dbconnection();
          String query ="UPDATE student SET performance= 50 WHERE student_id=1";
          String query2 ="UPDATE student SET performance= 50 WHERE student_id=2";
          con.setAutoCommit(false);
          
          Statement st= con.createStatement();
          int a=st.executeUpdate(query);
          int b=st.executeUpdate(query2);
          
          if(a>0 && b>0){
              con.commit();
          }
          
          
          System.out.println(a);
          System.out.println(b);
          con.close();
          
    }
    
    public static void batchProcessing() throws Exception{
       Connection con = dbconfig.dbconnection();
          String query ="UPDATE student SET performance= 75 WHERE student_id=1";
          String query2 ="UPDATE student SET performance= 75 WHERE student_id=2";
         
          Statement st= con.createStatement();
          st.addBatch(query);
          st.addBatch(query2);
         
         int[] a= st.executeBatch();
         System.out.println(Arrays.toString(a));
          
          
    }

  
}