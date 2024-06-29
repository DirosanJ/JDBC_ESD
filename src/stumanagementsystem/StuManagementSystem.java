/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package stumanagementsystem;
import java.sql.*;

/**
 *
 * @author DiroD
 */
public class StuManagementSystem {

  

    public static void main(String[] args) throws Exception{
       
         Class.forName("com.mysql.cj.jdbc.Driver");
         
         String url= "jdbc:mysql://localhost:3306/school";
         
         String user="root";
         
         String password="Diro2002";
                 
           String sql="SELECT * from students WHERE age=22";       
                 
        Connection con=DriverManager.getConnection(url, user, password);
            Statement st=con.createStatement();
             ResultSet rs= st.executeQuery(sql);
             
             rs.next();
             
             String name=rs.getString("name");
        
                
                 System.out.println(name);
    }
    
}