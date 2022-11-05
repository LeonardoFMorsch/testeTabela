/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TABELA;

import DAO.ProdutoDAO;
import MODEL.Produto;
import static java.lang.Integer.parseInt;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author wisecase2
 */
@Stateless
public class ProdutoListar {
    
    public List<String> listar(HttpServletRequest dados){
        List<String> response = new ArrayList<>(); // status, data
        
        List<Produto> cprodutos;
        try {
            ProdutoDAO dao = new ProdutoDAO();
            cprodutos = dao.listar();
        } catch (SQLException ex) {
            response.add("400");
            response.add("Erro de sql.");                   
            return response;
        }
        JSONObject jprodutos = getJSONObjectProduto(cprodutos);
        response.add("200");
        response.add(jprodutos.toString());  
  
        return response;
    }

    public List<String> delete(HttpServletRequest dados){
        List<String> response = new ArrayList<>(); // status, data
        
        try {
            ProdutoDAO dao = new ProdutoDAO();
            dao.del(parseInt(dados.getParameter("produtoID")));
        } catch (SQLException ex) {
            response.add("400");
            response.add("Erro de sql.");                   
            return response;
        }
        response.add("200");
        response.add("teste");
  
        return response;
    }
    
    public JSONObject getJSONObjectProduto(List<Produto> cprodutos){
        JSONObject jprodutos = new JSONObject();
        JSONArray arrProdutos = new JSONArray();
        
        for(Produto cproduto : cprodutos){
            JSONObject jproduto = new JSONObject();
            
            jproduto.put("ID", cproduto.getId());
            jproduto.put("NOME", cproduto.getNome());
            jproduto.put("IMAGEM", cproduto.getImg());
            jproduto.put("PRECO", cproduto.getPreco());
            jproduto.put("DESCONTO", cproduto.getDesconto());
            jproduto.put("CATEGPK", cproduto.getCateg_pk());
           
            arrProdutos.put(jproduto);
        }
        jprodutos.put("PRODUTOS", arrProdutos);
        
        return jprodutos;
    }
}
