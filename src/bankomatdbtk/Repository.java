/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankomatdbtk;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Repository {

    Connection con;
    Statement stmt;

    public Repository() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankomat",
                    "root", "lukas0691");
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
