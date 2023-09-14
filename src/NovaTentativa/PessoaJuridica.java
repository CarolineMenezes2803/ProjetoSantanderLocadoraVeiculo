package NovaTentativa;
public class PessoaJuridica implements Cliente {
    private String cnpj;
    private String nome;

    public PessoaJuridica(String cnpj, String nome) {
        this.cnpj = cnpj;
        this.nome = nome;
    }

    // Getters e Setters

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String getEndereco() {
        return null;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String getIdentificacao() {
        return cnpj;
    }

    @Override
    public double calcularDesconto(int dias) {
        if (dias > 3) {
            return 0.10; // 10% de desconto
        }
        return 0;
    }
}
