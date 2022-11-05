package controller;

import ejbconnection.EJBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Controller", urlPatterns = {"/Controller"})
public class Controller extends HttpServlet
{

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()){
            
            String classe = request.getParameter("classe");
            String metodo = request.getParameter("metodo");
            
            if(classe == null || classe.equals("")){
                response.setStatus(404);
                out.print("Classe vazia.");
                return;
            }
            if(metodo == null || metodo.equals("")){
                response.setStatus(404);
                out.print("metodo vazio.");
                return;
            }
            
            System.out.println("classe: " + classe + " metodo: " + metodo);
            
            Object obj = EJBConnection.execMethod("EJBtabela/" + classe, metodo, request);
            
            System.out.println("teste");
            
            // conversão de objeto para List https://stackoverflow.com/questions/7347442/how-to-convert-java-lang-object-to-arraylist/7347487
            List<?> resposta = new ArrayList<>();
            if (obj.getClass().isArray()) {
                resposta = Arrays.asList((Object[])obj);
            } else if (obj instanceof Collection) {
                resposta = new ArrayList<>((Collection<?>)obj);
            }
            
            if(resposta.size() != 2){
                response.setStatus(400);
                out.print(" Tamanho da lista de saída incorreto" + resposta.size());
                return;
            }
            
            
            response.setStatus(200);//Integer.parseInt((String)resposta.get(0)));
            out.print((String)resposta.get(1));
            
            //EJBConnection.execMethod("EJBProdutos/EJBProdutos", "getStrJSON", request)
            
            
            ///PrintWriter test = response.getWriter();
            //test.write("<p>test response</p>"); //finish
            
            //out.print("{\"data\":\"Curso salvo com sucesso!\"}");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
