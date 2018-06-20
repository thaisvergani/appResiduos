package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MunicipioDAO extends GenericDao {

    public void salvar(Municipio municipio) throws SQLException {
        String insert = "INSERT INTO MUNICIPIOS(codigo, nome, popurbana, poptotal) VALUES(?,?,?,?)";
        save(insert, 
                municipio.getCodigoMunicipio(),
                municipio.getNome(),
                municipio.getPopUrbana(),
                municipio.getPopTotal());
    }

    public void alterar(Municipio municipio) throws SQLException {
        String update = "UPDATE MUNICIPIOS " +
                "SET nome = ?, popurbana = ?, poptotal = ? " +
                "WHERE codigo = ?";
        update(update,
                municipio.getNome(),
                municipio.getPopUrbana(), 
                municipio.getPopTotal(),
                municipio.getCodigoMunicipio());
    }

    public void excluir(long id) throws SQLException {
        String delete = "DELETE FROM MUNICIPIOS WHERE id = ?";
        delete(delete, id);
    }

    public List findMunicipios() throws SQLException {
        List municipios = new ArrayList();

        String select = "SELECT * FROM MUNICIPIOS";

        PreparedStatement stmt = getConnection().prepareStatement(select);
			
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Municipio municipio = new Municipio();
            municipio.setCodigoMunicipio(rs.getString("codigo"));
            municipio.setNome(rs.getString("nome"));
            municipio.setPopUrbana(rs.getString("popurbana"));
            municipio.setPopTotal(rs.getString("poptotal"));
            municipios.add(municipio);
        }

        rs.close();
        stmt.close();
        getConnection().close();

        return municipios;
    }

    public Municipio findByName(String nome) throws SQLException {
        String select = "SELECT * FROM MUNICIPIOS WHERE nome = ?";
        Municipio municipio = null;
        PreparedStatement stmt = 
			getConnection().prepareStatement(select);
			
        stmt.setString(1, nome);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            municipio = new Municipio();
            municipio.setCodigoMunicipio(rs.getString("codigo"));
            municipio.setNome(rs.getString("nome"));
            municipio.setPopUrbana(rs.getString("popurbana"));
            municipio.setPopTotal(rs.getString("poptotal"));
        }

        rs.close();
        stmt.close();
        getConnection().close();

        return municipio;
    }
}