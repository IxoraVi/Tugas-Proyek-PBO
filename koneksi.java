package koneksi;
import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class koneksi { // koneksi ke database
    static Connection koneksi;
    public static Connection getConnection() {
        try {
            koneksi= DriverManager.getConnection("jdbc:mysql://localhost/perpustakaan","root","");
        } 
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Koneksi Database Gagal");        
        }
        return koneksi;
    }
}