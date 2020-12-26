package Conexao;

import Principal.Principal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexao {
    public static Connection getConexao() throws SQLException{
        Connection connection=null;
        try {
            Class.forName("org.postgresql.Driver");
            
            System.out.println("Connected to PostgreSQL database");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Trabalho4", "postgres", "joaoherbalspy744");
        } catch (SQLException e) {
            System.out.println("Connection failure.");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return (connection);
    } 
}
