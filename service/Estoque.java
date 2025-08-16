package service;
import model.*;

import java.util.List;
import java.util.ArrayList;

public class Estoque {
    private List<Equipamento> equipamentos;

    public Estoque(){
        this.equipamentos = new ArrayList<>();
    }

    public void cadastrarEquipamento(Equipamento equipamento) {
        if (buscarPorCodigo(equipamento.getCodigo()) != null){
            throw new RuntimeException("Erro: Já existe um equipamento com esse código!");
        }
        equipamentos.add(equipamento);
        System.out.println("Equipamento cadastrado com sucesso!");
    }

    public void listarTodos() {
        if (equipamentos.isEmpty()) {
            System.out.println("Nenhum equipamento cadastrado.");
            return;
        }
        
        System.out.println("\n=== TODOS OS EQUIPAMENTOS ===");
        for (Equipamento eq : equipamentos) {
            System.out.println(eq.toString());
        }
    }

    public void listarPorTipo(String tipo) {
        System.out.println("\n=== EQUIPAMENTOS DO TIPO: " + tipo + " ===");
        boolean encontrou = false;
        
        for (Equipamento eq : equipamentos) {
            if ((tipo.equals("MotorEletrico") && eq instanceof motor_eletrico) ||
                (tipo.equals("PainelControle") && eq instanceof painel_solar)) {
                System.out.println(eq.toString());
                encontrou = true;
            }
        }
        
        if (!encontrou) {
            System.out.println("Nenhum equipamento encontrado do tipo " + tipo);
        }
    }

    public Equipamento buscarPorCodigo(String codigo) {
        for (Equipamento eq : equipamentos) {
            if (eq.getCodigo().equals(codigo)) {
                return eq;
            }
        }
        return null;
    }

    public void pesquisarPorCodigo(String codigo) {
        Equipamento eq = buscarPorCodigo(codigo);
        if (eq != null) {
            System.out.println("\n=== EQUIPAMENTO ENCONTRADO ===");
            System.out.println(eq.toString());
        } else {
            System.out.println("Equipamento com código '" + codigo + "' não encontrado.");
        }
    }

    public void removerPorCodigo(String codigo) {
        Equipamento eq = buscarPorCodigo(codigo);
        if (eq != null) {
            equipamentos.remove(eq);
            System.out.println("Equipamento removido com sucesso!");
        } else {
            throw new RuntimeException("Equipamento com código '" + codigo + "' não encontrado.");
        }
    }

    public void movimentarEstoque(String codigo, int operacao, int quantidade) {
        Equipamento eq = buscarPorCodigo(codigo);
        if (eq == null) {
            throw new RuntimeException("Equipamento não encontrado!");
        }
        
        if (operacao == 1) { 
            eq.setQuantidade(eq.getQuantidade() + quantidade);
            System.out.println("Adicionadas " + quantidade + " unidades. Nova quantidade: " + eq.getQuantidade());
        } 
        else if (operacao == 2) { 
            if (eq.getQuantidade() < quantidade) {
                throw new RuntimeException("Erro: Quantidade insuficiente em estoque! Disponível: " + eq.getQuantidade());
            }
            eq.setQuantidade(eq.getQuantidade() - quantidade);
            System.out.println("Retiradas " + quantidade + " unidades. Nova quantidade: " + eq.getQuantidade());
        } else {
            throw new RuntimeException("Operação inválida! Use 1 para adicionar ou 2 para retirar.");
        }
    }
}