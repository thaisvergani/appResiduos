package controller;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.List;
import view.*;
import model.Municipio;

/**
 *
 * @author mathe
 */
public class Executavel {
    
    public static CadastroMunicipioFrame formPrin;
    public static List<Municipio> municipios;
    
    public static void main(String[] args){
        
    municipios = new ArrayList<Municipio>();
    
    formPrin = new CadastroMunicipioFrame();
    formPrin.setVisible(true);
    
    }
    
}
