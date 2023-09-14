package NovaTentativa;

public class Veiculo {
    private String placa;
    private String modelo;
    private String marca;
    private String tipo;
    private boolean disponivel;

    public Veiculo(String placa, String marca, String modelo, String tipo) {
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
        this.tipo = tipo;
        this.disponivel = true;
    }

    // Getters e Setters

    public String getPlaca() {
        return placa;
    }

    public String getModelo() {
        return modelo;
    }

    public String getMarca() {
        return marca;
    }

    public String getTipo() {
        return tipo;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}


