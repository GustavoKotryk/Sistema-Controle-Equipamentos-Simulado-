package service;
import model.*;
import view.InterfaceUsuario.*;

import java.util.List;
import java.util.ArrayList;

public class Estoque {
    private List<Equipamentos> estoque;

    public Estoque(){
        estoque = new ArrayList<>();
    }

   public void cadastrarEquipamento(Equipamentos equipamentos){
    estoque.add(equipamentos);
   }

   public List<Equipamentos> listarEquipamentos(){
    return estoque;
   }

   public void removerPorCod(String cod){
    Equipamentos equipamento = pesquisarPorCod(cod);
    if(equipamento != null){
        estoque.remove(equipamento);
    }
   }
}
