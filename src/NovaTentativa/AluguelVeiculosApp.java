package NovaTentativa;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AluguelVeiculosApp {
    private List<Veiculo> veiculos = new ArrayList<>();
    private List<Cliente> clientes = new ArrayList<>();
    private List<Aluguel> alugueis = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void cadastrarVeiculo() {
        System.out.print("Digite a placa do veículo: ");
        String placa = scanner.nextLine();

        // Verificar se a placa já existe
        if (veiculoExiste(placa)) {
            System.out.println("Veículo com a placa já cadastrada.");
            return;
        }
        System.out.print("Digite o modelo do veículo: ");
        String modelo = scanner.nextLine();

        System.out.print("Digite o marca do veiculo: ");
        String marca = scanner.nextLine();

        System.out.print("Digite o tipo do veículo (PEQUENO, MEDIO ou SUV): ");
        String tipo = scanner.nextLine();

        Veiculo veiculo;

        switch (tipo) {
            case "PEQUENO":
                veiculo = new VeiculoPequeno(placa, marca, modelo);
                break;
            case "MEDIO":
                veiculo = new VeiculoMedio(placa, marca, modelo);
                break;
            case "SUV":
                veiculo = new VeiculoSUV(placa, marca, modelo);
                break;
            default:
                System.out.println("Tipo de veículo inválido.");
                return;
        }

        veiculos.add(veiculo);
        System.out.println("Veículo cadastrado com sucesso.");
    }

    public void alterarVeiculo() {
        System.out.print("Digite a placa do veículo que deseja alterar: ");
        String placa = scanner.nextLine();

        Veiculo veiculo = encontrarVeiculoPorPlaca(placa);

        if (veiculo == null) {
            System.out.println("Veículo não encontrado.");
            return;
        }

        System.out.print("Digite o novo nome do veículo: ");
        String novoNome = scanner.nextLine();

        System.out.print("Digite o novo modelo do veiculo: ");
        String novoModelo = scanner.nextLine();

        // Atualizamos o nome diretamente no construtor do veículo
        veiculo = new Veiculo(veiculo.getPlaca(), veiculo.getMarca(), veiculo.getModelo(), veiculo.getTipo());

        System.out.println("Veículo alterado com sucesso.");
    }

    public void buscarVeiculo() {
        System.out.print("Digite parte do nome do veículo que deseja buscar: ");
        String parteNome = scanner.nextLine();

        for (Veiculo veiculo : veiculos) {
            if (veiculo.getMarca().contains(parteNome)) {
                System.out.println("Placa: " + veiculo.getPlaca());
                System.out.println("Modelo: " + veiculo.getModelo());
                System.out.println("Tipo: " + veiculo.getTipo());
                System.out.println("Marca: " + veiculo.getMarca());
            }
        }
    }

    public void listarVeiculosCadastrados() {
        System.out.println("Lista de Veículos Cadastrados:");
        for (Veiculo veiculo : veiculos) {
            System.out.println("Placa: " + veiculo.getPlaca());
            System.out.println("Nome: " + veiculo.getModelo());
            System.out.println("Marca: " + veiculo.getMarca());
            System.out.println("Modelo: " + veiculo.getModelo());
            System.out.println("Tipo: " + veiculo.getTipo());
            System.out.println();
        }
    }


    public void cadastrarCliente() {
        System.out.print("Digite o tipo de cliente (PF para Pessoa Física, PJ para Pessoa Jurídica): ");
        String tipoCliente = scanner.nextLine();

        if (tipoCliente.equals("PF")) {
            System.out.print("Digite o CPF do cliente: ");
            String cpf = scanner.nextLine();

            if (clienteExiste(cpf)) {
                System.out.println("Cliente com o CPF já cadastrado.");
                return;
            }

            System.out.print("Digite o nome do cliente: ");
            String nome = scanner.nextLine();

            System.out.print("Digite o endereço: ");
            String endereco = scanner.nextLine();

            clientes.add(new PessoaFisica(cpf, nome, endereco));
            System.out.println("Cliente Pessoa Física cadastrado com sucesso.");
        } else if (tipoCliente.equals("PJ")) {
            System.out.print("Digite o CNPJ do cliente: ");
            String cnpj = scanner.nextLine();

            if (clienteExiste(cnpj)) {
                System.out.println("Cliente com o CNPJ já cadastrado.");
                return;
            }

            System.out.print("Digite o nome do cliente: ");
            String nome = scanner.nextLine();

            clientes.add(new PessoaJuridica(cnpj, nome));
            System.out.println("Cliente Pessoa Jurídica cadastrado com sucesso.");
        } else {
            System.out.println("Tipo de cliente inválido.");
        }
    }

    public void alterarCliente() {
        System.out.print("Digite o CPF/CNPJ do cliente que deseja alterar: ");
        String identificacao = scanner.nextLine();

        Cliente cliente = encontrarClientePorIdentificacao(identificacao);

        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        String novoNome;
        if (cliente instanceof PessoaFisica) {
            System.out.print("Digite o novo nome do cliente Pessoa Física: ");
            novoNome = scanner.nextLine();
            ((PessoaFisica) cliente).setNome(novoNome);
        } else if (cliente instanceof PessoaJuridica) {
            System.out.print("Digite o novo nome do cliente Pessoa Jurídica: ");
            novoNome = scanner.nextLine();
            ((PessoaJuridica) cliente).setNome(novoNome);
        }

        System.out.println("Cliente alterado com sucesso.");
    }

    public void listarClientesCadastrados() {
        System.out.println("Lista de Clientes Cadastrados:");
        for (Cliente cliente : clientes) {
            if (cliente instanceof PessoaFisica) {
                System.out.println("CPF: " + ((PessoaFisica) cliente).getCpf());
                System.out.println("Nome: " + ((PessoaFisica) cliente).getNome());
                System.out.println("Endereço: " + ((PessoaFisica) cliente).getEndereco());
            } else if (cliente instanceof PessoaJuridica) {
                System.out.println("CNPJ: " + ((PessoaJuridica) cliente).getCnpj());
                System.out.println("Nome: " + ((PessoaJuridica) cliente).getNome());
            }
            System.out.println();
        }
    }

    public void alugarVeiculo() {
        System.out.print("Digite a placa do veículo que deseja alugar: ");
        String placa = scanner.nextLine();

        Veiculo veiculo = encontrarVeiculoPorPlaca(placa);

        if (veiculo == null) {
            System.out.println("Veículo não encontrado.");
            return;
        }

        System.out.print("Digite o CPF/CNPJ do cliente que deseja alugar para: ");
        String identificacao = scanner.nextLine();

        Cliente cliente = encontrarClientePorIdentificacao(identificacao);

        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        System.out.print("Digite a data e hora de início do aluguel (AAAA-MM-DD HH:mm): ");
        String dataInicioStr = scanner.nextLine();

        System.out.print("Digite a data e hora de término do aluguel (AAAA-MM-DD HH:mm): ");
        String dataFimStr = scanner.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dataInicio;
        LocalDateTime dataFim;

        try {
            dataInicio = LocalDateTime.parse(dataInicioStr, formatter);
            dataFim = LocalDateTime.parse(dataFimStr, formatter);
        } catch (java.time.format.DateTimeParseException e) {
            System.out.println("Formato de data/hora inválido. Use o formato 'AAAA-MM-DD HH:mm'.");
            return;
        }

        if (dataInicio.isAfter(dataFim)) {
            System.out.println("A data de início deve ser anterior à data de término.");
            return;
        }

        long horasAluguel = ChronoUnit.HOURS.between(dataInicio, dataFim);

        double valorTotal = calcularValorAluguel(veiculo, horasAluguel, cliente);

        Aluguel aluguel = new Aluguel(cliente, veiculo, dataInicio, dataFim);
        alugueis.add(aluguel);

        if (!veiculo.isDisponivel()) {
            System.out.println("Veículo não está disponível para aluguel.");
            return;
        }

        veiculo.setDisponivel(false);

        aluguel = new Aluguel(cliente, veiculo, dataInicio, dataFim);
        alugueis.add(aluguel);

        System.out.println("Aluguel realizado com sucesso.");
        System.out.println("Valor total do aluguel: R$ " + valorTotal);
    }

    public void listarAlugueisConcluidos() {
        System.out.println("Lista de Aluguéis Concluídos:");
        for (Aluguel aluguel : alugueis) {
            System.out.println("Cliente: " + aluguel.getCliente().getNome());
            System.out.println("Veículo: " + aluguel.getVeiculo().getMarca());
            System.out.println("Data de Início: " + aluguel.getDataInicio());
            System.out.println("Data de Término: " + aluguel.getDataFim());
            System.out.println("Valor Total: R$ " + aluguel.getValorTotal());
            System.out.println();
        }
    }

    public void devolverVeiculo() {
        System.out.print("Digite a placa do veículo que deseja devolver: ");
        String placa = scanner.nextLine();

        Veiculo veiculo = encontrarVeiculoPorPlaca(placa);

        if (veiculo == null) {
            System.out.println("Veículo não encontrado.");
            return;
        }

        Aluguel aluguel = encontrarAluguelPorVeiculo(veiculo);

        if (aluguel == null) {
            System.out.println("Não existe um aluguel ativo para este veículo.");
            return;
        }

        LocalDateTime dataAtual = LocalDateTime.now();

        long horasAluguel = ChronoUnit.HOURS.between(aluguel.getDataInicio(), dataAtual);

        double valorTotal = calcularValorAluguel(veiculo, horasAluguel, aluguel.getCliente());

        if (aluguel.getCliente() instanceof PessoaFisica && horasAluguel > 5) {
            valorTotal *= (1 - ((PessoaFisica) aluguel.getCliente()).calcularDesconto((int) horasAluguel));
        } else if (aluguel.getCliente() instanceof PessoaJuridica && horasAluguel > 3) {
            valorTotal *= (1 - ((PessoaJuridica) aluguel.getCliente()).calcularDesconto((int) horasAluguel));
        }

        veiculo.setDisponivel(true);
        alugueis.remove(aluguel);

        System.out.println("Veículo devolvido com sucesso.");
        System.out.println("Valor total do aluguel: R$ " + valorTotal);
    }

    private boolean veiculoExiste(String placa) {
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getPlaca().equals(placa)) {
                return true;
            }
        }
        return false;
    }

    private Veiculo encontrarVeiculoPorPlaca(String placa) {
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getPlaca().equals(placa)) {
                return veiculo;
            }
        }
        return null;
    }

    private boolean clienteExiste(String identificacao) {
        for (Cliente cliente : clientes) {
            if (cliente.getIdentificacao().equals(identificacao)) {
                return true;
            }
        }
        return false;
    }

    private Cliente encontrarClientePorIdentificacao(String identificacao) {
        for (Cliente cliente : clientes) {
            if (cliente.getIdentificacao().equals(identificacao)) {
                return cliente;
            }
        }
        return null;
    }

    private Aluguel encontrarAluguelPorVeiculo(Veiculo veiculo) {
        for (Aluguel aluguel : alugueis) {
            if (aluguel.getVeiculo() == veiculo) {
                return aluguel;
            }
        }
        return null;
    }

    private double calcularValorAluguel(Veiculo veiculo, long horasAluguel, Cliente cliente) {
        if (veiculo == null || cliente == null) {
            System.out.println("Erro: Veículo ou cliente não encontrado.");
            return 0.0; // Ou um valor padrão apropriado
        }

        double valorDiaria;
        switch (veiculo.getTipo()) {
            case "PEQUENO":
                valorDiaria = 100.0;
                break;
            case "MEDIO":
                valorDiaria = 150.0;
                break;
            case "SUV":
                valorDiaria = 200.0;
                break;
            default:
                valorDiaria = 0.0;
        }

        return valorDiaria * Math.ceil(horasAluguel / 24.0);
    }
}