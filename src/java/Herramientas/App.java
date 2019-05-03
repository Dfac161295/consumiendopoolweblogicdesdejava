/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Herramientas;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 *
 * @author DanielAC
 */
public class App {
    
    public static void main(String[] args) throws SQLException {
        
        Conector c = new Conector();
        c.conectar();
        ResultSet rs = c.Consultas("SELECT * FROM categoria");
        
        while(rs.next()){
            System.out.println(rs.getInt(1));
            System.out.println(rs.getString(2));
        }
 
        
        
    
    }
    
    
}
