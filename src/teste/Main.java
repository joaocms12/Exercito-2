/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

/**
 *
 * @author Joao
 */
import java.sql.*;

public class Main {

    public static void main(String[] args) {
        // string de conexão
        String databaseURL
                = "jdbc:firebirdsql:localhost:C:\\Users\\Joao\\Documents\\Projetos\\Exercito\\src\\BancodeDados\\BANCO.FDB";
        String user = "sysdba";
        String password = "masterkey";
        String driverName = "org.firebirdsql.jdbc.FBDriver";

        try {
            Class.forName(driverName).newInstance();
            Connection conn = DriverManager.getConnection(databaseURL, user, password);
            System.out.println("Conexão obtida com sucesso.");
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } catch (Exception e) {
            System.out.println("Problemas ao tentar conectar com o banco de dados: " + e);
        }
    }
}
