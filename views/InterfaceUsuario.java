package views;

import service.Estoque;
import model.*;
import java.util.Scanner;

public class InterfaceUsuario {
    private Estoque estoqueService;
    private Scanner scanner;
    
    public InterfaceUsuario() {
        this.estoqueService = new Estoque();
        this.scanner = new Scanner(System.in);
    }
    
    public void exibirMenu() {
        int opcao;
        
        System.out.println("========================================");
        System.out.println("    BEM-VINDO AO SISTEMA WEG!");
        System.out.println("========================================");
        
        do {
            mostrarOpcoes();
            
            try {
                opcao = Integer.parseInt(scanner.nextLine());
                processarOpcao(opcao);
                
            } catch (NumberFormatException e) {
                System.out.println("\n ERRO: Digite apenas números!");
                opcao = -1; 
            }
            
            if (opcao != 0) {
                System.out.println("\nPressione ENTER para continuar...");
                scanner.nextLine();
            }
            
        } while (opcao != 0);
        
        System.out.println("\n Obrigado por usar o Sistema WEG!");
        scanner.close();
    }
    
    private void mostrarOpcoes() {
        System.out.println("\n===============================");
        System.out.println("  Sistema de Controle WEG 2.0");
        System.out.println("===============================");
        System.out.println("1 - Cadastrar Equipamento");
        System.out.println("2 - Listar Todos os Equipamentos");
        System.out.println("3 - Listar Equipamentos por Tipo");
        System.out.println("4 - Pesquisar Equipamento por Código");
        System.out.println("5 - Remover Equipamento por Código");
        System.out.println("6 - Movimentar Estoque");
        System.out.println("0 - Sair");
        System.out.println("===============================");
        System.out.print("Digite a opção desejada: ");
    }
    
    private void processarOpcao(int opcao) {
        System.out.println();
        
        switch (opcao) {
            case 1:
                cadastrarEquipamento();
                break;
            case 2:
                estoqueService.listarTodos();
                break;
            case 3:
                listarPorTipo();
                break;
            case 4:
                pesquisarPorCodigo();
                break;
            case 5:
                removerPorCodigo();
                break;
            case 6:
                movimentarEstoque();
                break;
            case 0:
                System.out.println("Saindo do sistema...");
                break;
            default:
                System.out.println("Opção inválida! Tente novamente.");
        }
    }
    
    private void cadastrarEquipamento() {
        System.out.println("=== CADASTRAR NOVO EQUIPAMENTO ===");
        
        try {
            System.out.print("Código do equipamento: ");
            String codigo = scanner.nextLine().trim();
            
            if (codigo.isEmpty()) {
                System.out.println("Código não pode estar vazio!");
                return;
            }
            
            System.out.print("Nome do equipamento: ");
            String nome = scanner.nextLine().trim();
            
            if (nome.isEmpty()) {
                System.out.println("Nome não pode estar vazio!");
                return;
            }
            
            System.out.print("Quantidade inicial: ");
            int quantidade = Integer.parseInt(scanner.nextLine());
            
            if (quantidade < 0) {
                System.out.println("Quantidade não pode ser negativa!");
                return;
            }
            
            System.out.print("Preço (R$): ");
            double preco = Double.parseDouble(scanner.nextLine());
            
            if (preco < 0) {
                System.out.println("Preço não pode ser negativo!");
                return;
            }
            
            System.out.println("\nEscolha o tipo de equipamento:");
            System.out.println("1 - Motor Elétrico");
            System.out.println("2 - Painel de Controle");
            System.out.print("Sua escolha: ");
            
            int tipo = Integer.parseInt(scanner.nextLine());
            
            Equipamento equipamento = null;
            
            if (tipo == 1) {
                System.out.print("Potência (CV): ");
                double potencia = Double.parseDouble(scanner.nextLine());
                
                if (potencia <= 0) {
                    System.out.println("Potência deve ser maior que zero!");
                    return;
                }
                
                equipamento = new motor_eletrico(codigo, nome, quantidade, preco, potencia);
                
            } else if (tipo == 2) {
                System.out.print("Tensão: ");
                String tensao = scanner.nextLine().trim();
                
                if (tensao.isEmpty()) {
                    System.out.println(" Tensão não pode estar vazia!");
                    return;
                }
                
                equipamento = new painel_solar(codigo, nome, quantidade, preco, tensao);
                
            } else {
                System.out.println(" Tipo inválido! Escolha 1 ou 2.");
                return;
            }
            
            estoqueService.cadastrarEquipamento(equipamento);
            
        } catch (NumberFormatException e) {
            System.out.println(" ERRO: Digite apenas números nos campos numéricos!");
        } catch (RuntimeException e) {
            System.out.println(" " + e.getMessage());
        }
    }
    
    private void listarPorTipo() {
        System.out.println(" === LISTAR EQUIPAMENTOS POR TIPO ===");
        System.out.println("1 - Motores Elétricos");
        System.out.println("2 - Painéis de Controle");
        System.out.print("Escolha o tipo: ");
        
        try {
            int tipo = Integer.parseInt(scanner.nextLine());
            
            if (tipo == 1) {
                estoqueService.listarPorTipo("MotorEletrico");
            } else if (tipo == 2) {
                estoqueService.listarPorTipo("PainelControle");
            } else {
                System.out.println(" Tipo inválido! Escolha 1 ou 2.");
            }
            
        } catch (NumberFormatException e) {
            System.out.println(" ERRO: Digite apenas números!");
        }
    }
    
    private void pesquisarPorCodigo() {
        System.out.println("=== PESQUISAR EQUIPAMENTO ===");
        System.out.print("Digite o código do equipamento: ");
        
        String codigo = scanner.nextLine().trim();
        
        if (codigo.isEmpty()) {
            System.out.println(" Código não pode estar vazio!");
            return;
        }
        
        estoqueService.pesquisarPorCodigo(codigo);
    }
    
    private void removerPorCodigo() {
        System.out.println(" === REMOVER EQUIPAMENTO ===");
        System.out.print(" Digite o código do equipamento a remover: ");
        
        String codigo = scanner.nextLine().trim();
        
        if (codigo.isEmpty()) {
            System.out.println(" Código não pode estar vazio!");
            return;
        }
        
        System.out.println("\n Equipamento a ser removido:");
        estoqueService.pesquisarPorCodigo(codigo);
        
        System.out.print("\n Tem certeza que deseja remover? (S/N): ");
        String confirmacao = scanner.nextLine().trim().toUpperCase();
        
        if (confirmacao.equals("S") || confirmacao.equals("SIM")) {
            try {
                estoqueService.removerPorCodigo(codigo);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Operação cancelada.");
        }
    }
    
    private void movimentarEstoque() {
        System.out.println(" === MOVIMENTAR ESTOQUE ===");
        
        try {
            System.out.print(" Digite o código do equipamento: ");
            String codigo = scanner.nextLine().trim();
            
            if (codigo.isEmpty()) {
                System.out.println(" Código não pode estar vazio!");
                return;
            }
            
            System.out.println("\n Equipamento encontrado:");
            estoqueService.pesquisarPorCodigo(codigo);
            
            System.out.println("\n Escolha a operação:");
            System.out.println("1 - Adicionar unidades (Entrada)");
            System.out.println("2 - Retirar unidades (Saída)");
            System.out.print("Sua escolha: ");
            
            int operacao = Integer.parseInt(scanner.nextLine());
            
            if (operacao != 1 && operacao != 2) {
                System.out.println(" Operação inválida! Escolha 1 ou 2.");
                return;
            }
            
            System.out.print(" Digite a quantidade: ");
            int quantidade = Integer.parseInt(scanner.nextLine());
            
            if (quantidade <= 0) {
                System.out.println(" Quantidade deve ser maior que zero!");
                return;
            }
            
            estoqueService.movimentarEstoque(codigo, operacao, quantidade);
            
        } catch (NumberFormatException e) {
            System.out.println(" ERRO: Digite apenas números nos campos numéricos!");
        } catch (RuntimeException e) {
            System.out.println( e.getMessage());
        }
    }
}