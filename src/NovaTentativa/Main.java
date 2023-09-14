package NovaTentativa;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AluguelVeiculosApp app = new AluguelVeiculosApp();
        Scanner scanner = new Scanner(System.in);

        int escolha;

        do {
            System.out.println("Menu Principal:");
            System.out.println("1. Cadastrar Veículo");
            System.out.println("2. Alterar Veículo");
            System.out.println("3. Buscar Veículo");
            System.out.println("4. Cadastrar Cliente");
            System.out.println("5. Alterar Cliente");
            System.out.println("6. Alugar Veículo");
            System.out.println("7. Devolver Veículo");
            System.out.println("8. Listar Veículos Cadastrados");
            System.out.println("9. Listar Clientes Cadastrados");
            System.out.println("10. Listar Aluguéis Concluídos");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            escolha = scanner.nextInt();
            scanner.nextLine(); // Limpar a quebra de linha

            switch (escolha) {
                case 1:
                    app.cadastrarVeiculo();
                    break;
                case 2:
                    app.alterarVeiculo();
                    break;
                case 3:
                    app.buscarVeiculo();
                    break;
                case 4:
                    app.cadastrarCliente();
                    break;
                case 5:
                    app.alterarCliente();
                    break;
                case 6:
                    app.alugarVeiculo();
                    break;
                case 7:
                    app.devolverVeiculo();
                    break;
                case 8:
                    app.listarVeiculosCadastrados();
                    break;
                case 9:
                    app.listarClientesCadastrados();
                    break;
                case 10:
                    app.listarAlugueisConcluidos();
                    break;
                case 0:
                    System.out.println("Saindo do programa.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (escolha != 0);
    }
}

