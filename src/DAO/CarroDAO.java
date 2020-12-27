package DAO;
import Carro.Carro;
import Conexao.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CarroDAO {
    // possui um atributo do tipo connection
    Connection conexao;
    
    
    public CarroDAO() throws SQLException, ClassNotFoundException{
        // gerando nossa conexão por meio de um construtor padrão
        this.conexao = Conexao.getConexao();
    }
    
    // criando nossa função de inserir
    public void inserir(Carro carro) throws SQLException{
        //Criando uma instrução preparada
        PreparedStatement instrucao;
        
        //Criando nosso pré código de inserção
        String codigo = "insert into Carro(chassi, ano, modelo, fabricante, potencia, arcondicionado) values(?,?,?,?,?,?);";
        
        try{
            //falando que nossa instrução preparada será feita por meio da conexão e pelo pré código da string código
            instrucao = this.conexao.prepareStatement(codigo);
            
            //INSERINDO OS VALORES EM CADA UMA DAS INTERROGAÇÕES
            instrucao.setString(1, carro.getChassi());
            instrucao.setInt(2, carro.getAno());
            instrucao.setString(3,carro.getModelo());
            instrucao.setString(4, carro.getFabricante());
            instrucao.setFloat(5, carro.getPotencia());
            instrucao.setBoolean(6, carro.isArCondicionado());
            
            //EXECUTANDO A INSTRUÇÃO PREPARADA
            instrucao.execute();
            
            //FECHANDO A INSTRUÇÃO PREPARADA
            instrucao.close();
            
            //MENSAGEM DE QUE O CARRO FOI ADICIONADO COM SUCESSO
            System.out.println("Carro adicionado com sucesso");
        }catch(SQLException e){
            //MENSAGEM CASO OCORRA UM ERRO
            System.out.println("Erro ao tentar criar um carro e adiciona-lo: "+e.toString());
        }
    }
    
    public void update(Carro carro) throws SQLException{
        //Criando uma instrução preparada
        PreparedStatement instrucao;
        
        //Criando um pré condigo possuindo alguns valores como variáveis
        String codigo = "update Carro set chassi=?, ano=?, modelo=?, fabricante=?, potencia=?, arcondicionado=?;";
        
        try{
            //informando que nossa instrução preparada será feita por meio da conexão pelo pré código escrito
            instrucao = this.conexao.prepareStatement(codigo);
            
            //Informando qual o valor de cada uma das interrogações
            instrucao.setString(1, carro.getChassi());
            instrucao.setInt(2, carro.getAno());
            instrucao.setString(3,carro.getModelo());
            instrucao.setString(4, carro.getFabricante());
            instrucao.setFloat(5, carro.getPotencia());
            instrucao.setBoolean(6, carro.isArCondicionado());
            
            //Executando a isntrução
            instrucao.execute();
            
            //Fechando a instrução
            instrucao.close();
            
            //Mensagem de sucesso
            System.out.println("Carro atualizado com sucesso");
        }catch(SQLException e){
            //Mensagem de erro
            System.out.println("Erro ao tentar atualizar carro: "+e.toString());
        }
    }
    
    
    public ArrayList<Carro> getAll(boolean ordernar) throws SQLException{
        PreparedStatement instrucao;
        ResultSet res;
        ArrayList <Carro> ListaRetorno = new ArrayList();
        
        String codigo = "select * from carro;";
        if(ordernar)
            codigo = codigo+"order by (modelo)";
        try{
            instrucao = this.conexao.prepareStatement(codigo);
            res = instrucao.executeQuery();
            while(res.next()){
                Carro carro = new Carro();
                carro.setChassi(res.getString("chassi"));
                carro.setAno(res.getInt("ano"));
                carro.setModelo(res.getString("modelo"));
                carro.setFabricante(res.getString("fabricante"));
                carro.setPotencia(res.getFloat("potencia"));
                carro.setArCondicionado(res.getBoolean("arCondicionado"));
                
                ListaRetorno.add(carro);
            }
            
            res.close();
            instrucao.close();
        }catch(SQLException e){
            System.out.println("Erro ao tentar recuperar a lista "+e.toString());
        }
        return ListaRetorno;
    }
    
    public ArrayList<Carro> getOne(String chassi) throws SQLException{
        PreparedStatement instrucao;
        ResultSet res;
        ArrayList <Carro> ListaRetorno = new ArrayList();
        
        String codigo = "select * from carro where chassi='"+chassi +"';";
        
        try{
            instrucao = this.conexao.prepareStatement(codigo);
            res = instrucao.executeQuery();
            ListaRetorno = new ArrayList<>();
            while(res.next()){
                Carro carro = new Carro();
                carro.setChassi(res.getString("chassi"));
                carro.setAno(res.getInt("ano"));
                carro.setModelo(res.getString("modelo"));
                carro.setFabricante(res.getString("fabricante"));
                carro.setPotencia(res.getFloat("potencia"));
                carro.setArCondicionado(res.getBoolean("arCondicionado"));
                
                ListaRetorno.add(carro);
            }
            
            res.close();
            instrucao.close();
        }catch(SQLException e){
            System.out.println("Erro ao tentar recuperar a lista "+e.toString());
        }
        System.out.println("Carro buscado com sucesso");
        return ListaRetorno;
    }
    
    public void delete(String chassi) throws SQLException{
        PreparedStatement instrucao;
        
        String codigo = "delete from carro where chassi=?;";
        
        try{
            instrucao = this.conexao.prepareStatement(codigo);
            instrucao.setString(1, chassi);
            
            instrucao.execute();
            instrucao.close();
            
            System.out.println("Carros deletados com sucesso");
        
        }catch(SQLException e){
            System.out.println("Erro ao tentar deletar: "+e.toString());
        }
    
    }
    
    public ArrayList<Carro> maiorPotencia(){
        ArrayList <Carro> ListaRetorno = null;
        PreparedStatement instrucao;
        ResultSet res;
        
        String codigo = "SELECT * FROM carros WHERE potencia in (select MAX(potencia) FROM carros)";
        
        try{
            ListaRetorno = new ArrayList();
            instrucao = this.conexao.prepareStatement(codigo);
            res = instrucao.executeQuery();
            
            while(res.next()){
                Carro carro = new Carro(res.getString("chassi"), res.getInt("ano"), res.getString("modelo"), res.getString("fabricante"), res.getFloat("potencia"), res.getBoolean("arCondicionado"));
                ListaRetorno.add(carro);
            }
            
            
        }catch(SQLException e){
            System.out.println("error: "+e.toString());
        }
        
        return ListaRetorno;
    }
    
    
    public float MediaAnos(){
        PreparedStatement instrucao;
        ResultSet res;
        String codigo = "select avg(ano) from carros";
        
        float media = 0;
        try{
            instrucao = this.conexao.prepareStatement(codigo);
            res = instrucao.executeQuery();
            
            while(res.next()){
                media = res.getFloat("avg");
            }
        }catch(SQLException e){
            System.out.println("erro: "+e.toString());
        }
       
        return media;
    }
    
    public int lenght(){
        ArrayList <Carro> ListaCarros = new ArrayList();
        PreparedStatement instrucao;
        ResultSet res;
        
        String codigo = "select * from Carro";
        
        try{
            instrucao = this.conexao.prepareStatement(codigo);
            res = instrucao.executeQuery();
            
            while(res.next()){
                Carro carro = new Carro(res.getString("chassi"), res.getInt("ano"), res.getString("modelo"), res.getString("fabricante"), res.getFloat("potencia"), res.getBoolean("arCondicionado"));
                ListaCarros.add(carro);
            }   
        }catch(SQLException e){
            System.out.println("erro: "+e.toString());
        }
        
        return (ListaCarros.size());
    }
    
    public ArrayList<String> menorAno(){
        ArrayList <String> ListaRetorno = new ArrayList();
        PreparedStatement instrucao;
        ResultSet res;
        
        String codigo = "select fabricante from carro where ano in (select MIN(ano) from carro) group by (fabricante)";
        
        try{
            instrucao = this.conexao.prepareStatement(codigo);
            res = instrucao.executeQuery();
            
            while(res.next()){
                ListaRetorno.add(res.getString("fabricante"));
            }
        }catch(SQLException e){
            System.out.println("Erro: "+e.toString());
        }
        
        return ListaRetorno;
    }
    
    public ArrayList<Carro> ComAr(){
        ArrayList <Carro> ListaRetorno = null;
        PreparedStatement instrucao;
        ResultSet res;
        
        String codigo = "select * from carro where arCondicionado=true";
        
        try{
            ListaRetorno = new ArrayList();
            instrucao = this.conexao.prepareStatement(codigo);
            res = instrucao.executeQuery();
            
            while(res.next()){
                ListaRetorno.add(new Carro(res.getString("chassi"), res.getInt("ano"), res.getString("modelo"), res.getString("fabricante"), res.getFloat("potencia"), res.getBoolean("arCondicionado")));
            }
        }catch(SQLException e){
            System.out.println("Erro: "+e.toString());
        }
        return ListaRetorno;
    }
}

    