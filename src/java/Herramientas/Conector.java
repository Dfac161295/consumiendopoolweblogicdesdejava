package Herramientas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
//import orc.apache.dbutil.dbutils;




public class Conector {

    String jndi = "pool/jdbctienda";
    Connection c1 = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    
    
 public boolean conectar(){
     
     boolean retorno = false;
 
        try {
            
            Hashtable ht = new Hashtable();
            ht.put(Context.INITIAL_CONTEXT_FACTORY,"weblogic.jndi.WLInitialContextFactory");
            ht.put(Context.PROVIDER_URL,"t3://localhost:7001");
            
            Context ctx = new InitialContext(ht);
            DataSource ds = (DataSource)ctx.lookup("pool/jdbctienda");
            
            if(ds != null){
              this.c1 = ds.getConnection();
              retorno = true;
            }else{
              retorno = false;
            }
            
            
        } catch (NamingException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
     
   return retorno;  
 } 
 
 
 public ResultSet Consultas(String sql){
 
        try {
            ps = c1.prepareStatement(sql);
            ps.setQueryTimeout(5);
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
 
     return rs;
 }
 
 
  public void Transacciones(String sql){
  
      try {
          ps = c1.prepareStatement(sql);
          ps.executeUpdate();
          c1.close();
      } catch (Exception e) {
      }
      
  
  }  
    
  public boolean desconectar(){
  
      boolean cerro = false;
      try {
          rs.close();
          ps.close();
          c1.close();
          cerro = true;
      } catch (Exception e) {
          System.out.println(e.getMessage());
      }
   
      return cerro;
  }  
    
    
}
