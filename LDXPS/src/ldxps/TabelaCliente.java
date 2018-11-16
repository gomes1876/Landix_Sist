package ldxps;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 *
 * @author gabri
 */
public class TabelaCliente {
    private Connection con;
    
    public TabelaCliente() {
        
    }
    
    public void Conectar(String _databaseName, String _user, String _password) throws ClassNotFoundException, SQLException {
        //Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + _databaseName + "?useTimezone=true&serverTimezone=UTC", _user, _password);
    }
    
    public ResultSet SelectClientes() throws SQLException {
        String sql;
        boolean executado;
        ResultSet rs= null;
        PreparedStatement stmt;
        
        sql = "SELECT * FROM CLIENTES";
        stmt = con.prepareStatement(sql);
        executado = stmt.execute();
        if (executado)
            rs = stmt.getResultSet();
        
        return rs;
    }
    
    public ResultSet SelectClientes(String _whereClause) throws SQLException {
        String sql;
        boolean executado;
        ResultSet rs= null;
        PreparedStatement stmt;
        
        sql = "SELECT * FROM CLIENTES WHERE ";
        sql += _whereClause;
        stmt = con.prepareStatement(sql);
	//stmt.setString(1, _whereClause);
        executado = stmt.execute();
        if (executado)
            rs = stmt.getResultSet();
        
        return rs;
    }
    
    public boolean InsertCliente(String _dsnome, String _idtipo, String _cdvend, double _dslim) throws SQLException {
        String sql;
        boolean executado;
        PreparedStatement stmt;
        
        sql = "INSERT INTO CLIENTES (CDCL, DSNOME, IDTIPO, CDVEND, DSLIM) VALUES (?, ?, ?, ?, ?)";
        stmt = con.prepareStatement(sql);
        stmt.setString(1, UUID.randomUUID().toString());
	stmt.setString(2, _dsnome);
        stmt.setString(3, _idtipo);
        stmt.setString(4, _cdvend);
        stmt.setDouble(5, _dslim);
        executado = stmt.execute();
        stmt.close();
        
        return executado;
    }
    
    public boolean UpdateCliente(String _field, String _value, String _whereClause) throws SQLException {
        String sql;
        boolean executado;
        PreparedStatement stmt;
        
        sql = "UPDATE CLIENTES SET ? = ? WHERE ?";
        stmt = con.prepareStatement(sql);
        stmt.setString(1, _field);
        stmt.setString(2, _value);
        stmt.setString(3, _whereClause);
        executado = stmt.execute();
        stmt.close();
        
        return executado;
    }
    
    public boolean UpdateCliente(String[] _fields, String[] _values, String _whereClause) throws SQLException {
        String sql;
        boolean executado;
        PreparedStatement stmt;
        
        sql = "UPDATE CLIENTES SET ";
        for (int i = 0; i < _fields.length; i++) {
            sql += _fields[i] + " = " + _values[i];
            sql += ", ";
        }
        sql = sql.substring(0, sql.length() - 2);
        sql += " WHERE " + _whereClause;
        stmt = con.prepareStatement(sql);
        executado = stmt.execute();
        stmt.close();
        
        return executado;
    }
    
    public boolean DeleteCliente(String _whereClause) throws SQLException {
        String sql;
        boolean executado;
        PreparedStatement stmt;
        
        sql = "DELETE FROM CLIENTES WHERE ?";
        stmt = con.prepareStatement(sql);
        stmt.setString(1, _whereClause);
        executado = stmt.execute();
        stmt.close();
        
        return executado;
    }
    
    public void Desconectar() throws SQLException {
        con.close();
    }
}