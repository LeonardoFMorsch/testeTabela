/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lm006234
 */
public class DatabaseConnection {

    /**
     * @param args the command line arguments
     */
    private static DatabaseConnection instance;

    private Connection connection;

    private DatabaseConnection() {
        try {
            String url = "jdbc:firebirdsql:127.0.0.1/3050:C:\\Database\\ECOMMERCE.FDB";
            String usuario = "SYSDBA";
            String senha = "masterkey";

            Class.forName("org.firebirdsql.jdbc.FBDriver");

            this.connection = DriverManager.getConnection(url, usuario, senha);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //método público que retorna a conexão com o BD
    public Connection getConnection(){
        return connection;
    }
    
    // método para retornar a instância da classe
    public static DatabaseConnection getInstance(){
        //caso a instancia esteja nula, criamos uma nova
        if(instance == null){
            instance = new DatabaseConnection();
        }else{
            try{
                //caso a conexão esteja fechada criamos uma nova
                if(instance.getConnection().isClosed()){
                    instance = new DatabaseConnection();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return instance;
    }
}
