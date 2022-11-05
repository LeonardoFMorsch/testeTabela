/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import MODEL.Produto;
import databaseconnection.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author wisecase2
 */
public class ProdutoDAO {
    Connection con;

    public ProdutoDAO() {
        this.con = DatabaseConnection.getInstance().getConnection();
    }
    
    
    public List<Produto> listar() throws SQLException {
        List<Produto> produtos = new ArrayList<>();

        StringBuilder sqlb = new StringBuilder();
        sqlb.append("SELECT");
        
        sqlb.append(" PRODUTO.ID, PRODUTO.NOME, PRODUTO.IMAGEM, PRODUTO.PRECO, PRODUTO.DESCONTO, PRODUTO.CATEGORIA_PK FROM PRODUTO");
        
        
        String sql = sqlb.toString();
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()){
            Produto produto = new Produto();
            produto.setId(rs.getInt("ID"));
            produto.setNome(rs.getString("NOME"));
            produto.setImg(rs.getString("IMAGEM"));
            produto.setPreco(rs.getFloat("PRECO"));
            produto.setDesconto(rs.getFloat("DESCONTO"));
            produto.setCateg_pk(rs.getInt("CATEGORIA_PK"));
            produtos.add(produto);
        }
        rs.close();
        ps.close();

        return produtos;
    }
    
     public void del(int produtoID) throws SQLException {

        String query = "DELETE FROM PRODUTO WHERE PRODUTO.ID = ?";
        PreparedStatement ps;
        ps = con.prepareStatement(query);
        ps.setInt(1, produtoID);
        ps.execute();
    }
    
}
