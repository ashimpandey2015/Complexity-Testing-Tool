/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author ashim
 */
public class db {
     static ResultSet getData(String string) {

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    Connection conn = null;

    public static Connection java_db() {

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/spm", "root", "password");
            
            System.out.println("Connectedd");
            return conn;

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e);
            return null;
        }

    }
}
