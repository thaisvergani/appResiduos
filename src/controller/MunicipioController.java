package controller;


import javax.swing.*;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import model.Municipio;
import model.MunicipioDAO;

public class MunicipioController {
    static Executavel executavel;

    public static void cadastrarMunicipio(String nome, String codigo, String popUrbana, String popTotal){
        try {
            // colocar essa logica dentro do model
            Municipio m = new Municipio();
            m.setCodigoMunicipio(codigo);
            m.setNome(nome);
            m.setPopUrbana(popUrbana);
            m.setPopTotal(popTotal);
            executavel.municipios.add(m);
            
            new MunicipioDAO().salvar(m);
            
            JOptionPane.showMessageDialog(null, "Municipio Cadastrado com sucesso", "Sucesso", 1);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "erro", "erro", 1);
        }

    }
    
    public static void editarMunicipio(String nome, String codigo, String popUrbana, String popTotal, int pos){
        Municipio m = new Municipio();
        m.setCodigoMunicipio(codigo);
        m.setNome(nome);
        m.setPopUrbana(popUrbana);
        m.setPopTotal(popTotal);
        executavel.municipios.set(pos, m);
        
        JOptionPane.showMessageDialog(null, "Municipio Editado com sucesso", "Sucesso", 1);
    
    
    }
    
    public static void excluirMunicipio(JTable tabela){
        if(tabela.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(null, "Selecione um registro", "Erro", 0);
        
        } else {
            int reply = JOptionPane.showConfirmDialog(null, "Tem certeza?", "Excluir Municipio", JOptionPane.YES_NO_OPTION);
            if(reply==JOptionPane.YES_OPTION){
                executavel.municipios.remove(tabela.getSelectedRow());
                JOptionPane.showMessageDialog(null, "Municipio Excluída com Sucesso", "Sucesso", 1);
            }
        }
    
    }

    public static void preencherTabela(JTable tabela, List<Municipio> lista) {
        
        DefaultTableModel dtm = (DefaultTableModel) tabela.getModel();
        dtm.setRowCount(lista.size());
        tabela.setModel(dtm);
        
        int posLinha = 0;
        for (Municipio m1 : lista){
            tabela.setValueAt(m1.getCodigoMunicipio(), posLinha, 0);
            tabela.setValueAt(m1.getNome(), posLinha, 1);
            tabela.setValueAt(m1.getPopUrbana(), posLinha, 2);
            tabela.setValueAt(m1.getPopTotal(), posLinha, 3);
            posLinha++;
        }
        
    
    }
    
    private Date formatarData(String data) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return new Date( formatter.parse(data).getTime() );
    }

    public void salvar(String codigo, String nome, String popTotal, String popUrbana) 
		throws SQLException, ParseException 
	{
        Municipio municipio = new Municipio();
        municipio.setCodigoMunicipio(codigo);
        municipio.setNome(nome);
        municipio.setPopTotal(popTotal);
        municipio.setPopUrbana(popUrbana);

        new MunicipioDAO().salvar(municipio);
    }

    public void alterar(String codigo, String nome, String popTotal, String popUrbana) 
		throws ParseException, SQLException 
	{
        
        Municipio municipio = new Municipio();
        municipio.setCodigoMunicipio(codigo);
        municipio.setNome(nome);
        municipio.setPopTotal(popTotal);
        municipio.setPopUrbana(popUrbana);
        new MunicipioDAO().alterar(municipio);
    }

    public List listaContatos() {
        MunicipioDAO dao = new MunicipioDAO();
        try {
            return dao.findMunicipios();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
				"Problemas ao localizar contaton" + 
				e.getLocalizedMessage()
			);
        }
        return null;
    }

    public void excluir(long id) throws SQLException {
        new MunicipioDAO().excluir(id);
    }

    public Municipio buscaContatoPorNome(String nome) throws SQLException {
        MunicipioDAO dao = new MunicipioDAO();
        return dao.findByName(nome);
    }
}