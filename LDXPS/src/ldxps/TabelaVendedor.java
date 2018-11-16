package ldxps;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 *
 * @author gabri
 */
public class TabelaVendedor {
    private Connection con;
    
    public TabelaVendedor() {
        
    }
    
    public void Conectar(String _databaseName, String _user, String _password) throws ClassNotFoundException, SQLException {
        //Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + _databaseName + "?useTimezone=true&serverTimezone=UTC", _user, _password);
    }
    
    public ResultSet SelectVendedores() throws SQLException {
        String sql;
        boolean executado;
        ResultSet rs= null;
        PreparedStatement stmt;
        
        sql = "SELECT * FROM VENDEDORES";
        stmt = con.prepareStatement(sql);
        executado = stmt.execute();
        if (executado)
            rs = stmt.getResultSet();
        
        return rs;
    }
    
    public ResultSet SelectVendedores(String _whereClause) throws SQLException {
        String sql;
        boolean executado;
        ResultSet rs= null;
        PreparedStatement stmt;
        
        sql = "SELECT * FROM VENDEDORES WHERE ?";
        stmt = con.prepareStatement(sql);
	stmt.setString(1, _whereClause);
        executado = stmt.execute();
        if (executado)
            rs = stmt.getResultSet();
        
        return rs;
    }
    
    public boolean InsertVendedor(String _dsnome, int _cdtab, Date _dtnasc) throws SQLException {
        String sql;
        boolean executado;
        PreparedStatement stmt;
        
        sql = "INSERT INTO VENDEDORES (CDVEND, DSNOME, CDTAB, DTNASC) VALUES (?, ?, ?, ?)";
        stmt = con.prepareStatement(sql);
        stmt.setString(1, UUID.randomUUID().toString());
	stmt.setString(2, _dsnome);
        stmt.setInt(3, _cdtab);
        stmt.setDate(4, _dtnasc);
        executado = stmt.execute();
        stmt.close();
        
        return executado;
    }
    
    public boolean UpdateVendedor(String _field, String _value, String _whereClause) throws SQLException {
        String sql;
        boolean executado;
        PreparedStatement stmt;
        
        sql = "UPDATE VENDEDORES SET ? = ? WHERE ?";
        stmt = con.prepareStatement(sql);
        stmt.setString(1, _field);
        stmt.setString(2, _value);
        stmt.setString(3, _whereClause);
        executado = stmt.execute();
        stmt.close();
        
        return executado;
    }
    
    public boolean UpdateVendedor(String[] _fields, String[] _values, String _whereClause) throws SQLException {
        String sql;
        boolean executado;
        PreparedStatement stmt;
        
        sql = "UPDATE VENDEDORES SET ";
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
    
    public boolean DeleteVendedor(String _whereClause) throws SQLException {
        String sql;
        boolean executado;
        PreparedStatement stmt;
        
        sql = "DELETE FROM VENDEDORES WHERE ?";
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