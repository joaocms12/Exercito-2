package ConexaoBanco;

import java.sql.*;
import javax.swing.JOptionPane;

public class GerenciarBD {

    public static Connection criacaoConexao = null;
    public static Statement execucaoSQL = null;
    public static ResultSet conjuntoResultados = null;
    public static final String driverBD = "org.firebirdsql.jdbc.FBDriver";
    public static final String urlBD = "jdbc:firebirdsql:localhost:C:\\Users\\Joao\\Documents\\Projetos\\Exercito\\src\\BancodeDados\\BANCO.FDB";

    /*      Método para conectar ao um Banco de Dados, retorna true em caso de sucesso, retorna false em caso negativo     */
    public static boolean criarConexao() {
        try {
            Class.forName(driverBD);
            criacaoConexao = DriverManager.getConnection(urlBD, "sysdba", "masterkey");
            execucaoSQL = criacaoConexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            System.out.println("Conexão com o banco de dados estabelecida com sucesso! ");
            return true;
        } catch (ClassNotFoundException erro) {
            erro.printStackTrace();
            JOptionPane.showMessageDialog(null, "Falha na conexao com o Banco de Dados MySQL");
            return false;
        } catch (SQLException erro) {
            erro.printStackTrace();
            JOptionPane.showMessageDialog(null, "Falha SQL");
            return false;
        }
    }

    /* Carrega no ResultSet o conteúdo do script ComandoSQL  */
    public static void consultarSQL(String ComandoSQL) {
        try {
            conjuntoResultados = execucaoSQL.executeQuery(ComandoSQL);            // Consulta Base de Dados            
        } catch (SQLException erro) {
            erro.printStackTrace();
        }
    }

    /* Executa um script SQL de atualização (ComandoSQL), retorna a quantidade de linhas afetadas pelo script  */
    public static int atualizarSQL(String ComandoSQL) {
        int qtdelinhas = 0;
        try {
            qtdelinhas = execucaoSQL.executeUpdate(ComandoSQL);             // Atualiza Base de Dados
        } catch (SQLException erro) {
            erro.printStackTrace();
        }
        return qtdelinhas;
    }


    /* **************** Métodos para fechar e liberar os recursos do Banco de Dados ************ */
    /* Fecha conjuntoResultados (resultSet), execucaoSQL (Statement) e criacaoConexao (Connection) */
    public static void fechar() {
        fecharResultSet();
        fecharStatement();
        fecharConnection();
    }

    /* Método para fechar uma criacaoConexao */
    public static void fecharConnection() {
        try {
            criacaoConexao.close();
            //System.out.println("Conexão encerrada com sucesso! ");
        } catch (SQLException erro) {
            erro.printStackTrace();
        }
    }

    /* Método para fechar uma execucaoSQL */
    public static void fecharStatement() {
        try {
            execucaoSQL.close();
        } catch (SQLException excecao) {
            excecao.printStackTrace();
        }
    }

    /* Método para fechar um conjuntoResultados */
    public static void fecharResultSet() {
        try {
            conjuntoResultados.close();
        } catch (SQLException excecao) {
            excecao.printStackTrace();
        }
    }

}
