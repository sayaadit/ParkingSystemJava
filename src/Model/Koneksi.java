package Model;

import javax.swing.JOptionPane;
import java.sql.*;

public class Koneksi {
    public Connection con;
    public Statement stm;
    
    private String jdbc = "jdbc:mysql://";  
    private String host = "localhost:";
    private String port = "3306/";
    private String database = "tubes_impal";
    private String url = jdbc + host + port + database;  
    private String username = "root";
    private String password = "";  
    
    public void config(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
            stm = con.createStatement();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Koneksi Gagal "+e.getMessage());
        }
    }
}
