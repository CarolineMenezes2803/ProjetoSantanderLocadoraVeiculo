package NovaTentativa;
public class PessoaFisica implements Cliente {
    private String cpf;
    private String nome;
    private String endereco;

    public PessoaFisica(String cpf, String nome, String endereco) {
        this.cpf = cpf;
        this.nome = nome;
        this.endereco = endereco;
    }

    // Getters e Setters

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public String getIdentificacao() {
        return cpf;
    }

    @Override
    public double calcularDesconto(int dias) {
        if (dias > 5) {
            return 0.05; // 5% de desconto
        }
        return 0;
    }
}
