
package Principal;
import DAO.CarroDAO;
import Carro.Carro;
import Conexao.Conexao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Principal {

    
    public static void main(String[] args) {
        Carro carro = new Carro("1234", 2020, "city", "honda", 1200, true);
        CarroDAO carroDAO;
        try{
            carroDAO = new CarroDAO();
            carroDAO.delete(carro.getChassi());
        }catch(SQLException e){
            System.out.println("Erro: "+e.toString());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        /*try {
            Connection con = Conexao.getConexao();
        } catch (SQLException e1) {
            System.out.print("\n Erro SQLException: "+e1.toString());
    }*/
    
}
}
