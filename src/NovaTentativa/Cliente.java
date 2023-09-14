package NovaTentativa;

public interface Cliente {
    String getIdentificacao();
    String getNome();
    String getEndereco();
    double calcularDesconto(int dias);
}
