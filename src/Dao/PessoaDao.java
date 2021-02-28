package Dao;

import ConnectionFactory.ConexaoDao;
import Model.Pessoa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class PessoaDao {
    
    Connection conn;
    PreparedStatement pstm;
    ResultSet rs;
    
        ArrayList<Pessoa>lista = new ArrayList<>();
 
        //1- Cadastrar
    public void CadastrarPessoa(Pessoa pessoa){
        String sql = "insert into pessoa (nome, cpf) values (?,?)";
        
        conn = new ConexaoDao().ConexaoBD();
        
        try{
            
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, pessoa.getNome());
            pstm.setInt(2, pessoa.getCpf());
            
            pstm.execute();
            pstm.close();
            
        }catch(Exception erro){
            JOptionPane.showMessageDialog(null, "Erro" + erro);
            
        }       
    }
    
    
        //2- Pesquisar    
    public ArrayList <Pessoa> pesquisarPessoa(){
        String sql = "select * from pessoa";
        
        conn = new ConexaoDao().ConexaoBD();
        
        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            
            while (rs.next()){
                Pessoa pessoa = new Pessoa();
                pessoa.setId(rs.getInt("id"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setCpf(rs.getInt("cpf"));
                
                lista.add(pessoa);
                
            }            
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro" + erro);
        }
        return lista;
    }
        //3- Alterar/Editar  
    public void EditarPessoa(Pessoa pessoa){
        
        String sql = "update pessoa set nome = ?, cpf = ?, where id = ?";
        
        conn = new ConexaoDao().ConexaoBD();
        
        try{
            
            pstm = conn.prepareStatement(sql);            
            pstm.setString(1, pessoa.getNome());
            pstm.setInt(2, pessoa.getCpf());
            pstm.setInt(3, pessoa.getId());
            
            pstm.execute();
            pstm.close();
            
        }catch(Exception erro){
            JOptionPane.showMessageDialog(null, "Erro" + erro);
            
        } 
        
    }
    
    //4- Excluir
    public void ExcluirPessoa(Pessoa pessoa){
        
       String sql = "delete from pessoa where id = ?";
        
        conn = new ConexaoDao().ConexaoBD();
        
        try{
            
            pstm = conn.prepareStatement(sql);            
            pstm.setInt(1, pessoa.getId());
            
            pstm.execute();
            pstm.close();
            
        }catch(Exception erro){
            JOptionPane.showMessageDialog(null, "Erro" + erro);
            
        }  
        
    }
    
}
