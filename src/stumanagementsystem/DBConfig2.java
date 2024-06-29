/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stumanagementsystem;
import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author DiroD
 */
public class DBConfig2 {
    private static DBConfig2 instance;
    
    
    private DBConfig2 (){
    }
    
    public static synchronized DBConfig2 getInstance(){
    if (instance == null){
    instance = new  DBConfig2();
    }
    return instance;
        }
            
            public Connection dbconnection () throws Exception {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/devAcademy";
            String user="root";
            String password="Diro2002";
            Connection con=DriverManager.getConnection(url, user, password);
            return con;
            
            }
    
    
}
