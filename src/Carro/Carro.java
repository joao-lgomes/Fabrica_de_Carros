
package Carro;

public class Carro {
    String chassi;
    int ano;
    String modelo;
    String fabricante;
    float potencia;
    boolean arCondicionado;

    public Carro(){
        this.chassi = "";
        this.ano = 2020;
        this.modelo = "";
        this.fabricante = "";
        this.potencia = 0;
        this.arCondicionado = false;
    }
    
    public Carro(String chassi, int ano, String modelo, String fabricante, float potencia, boolean arCondicionado) {
        this.chassi = chassi;
        this.ano = ano;
        this.modelo = modelo;
        this.fabricante = fabricante;
        this.potencia = potencia;
        this.arCondicionado = arCondicionado;
    }

    public String getChassi() {
        return chassi;
    }

    public void setChassi(String chassi) {
        this.chassi = chassi;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public float getPotencia() {
        return potencia;
    }

    public void setPotencia(float potencia) {
        this.potencia = potencia;
    }

    public boolean isArCondicionado() {
        return arCondicionado;
    }

    public void setArCondicionado(boolean arCondicionado) {
        this.arCondicionado = arCondicionado;
    }

    @Override
    public String toString() {
        return "Carro{" + "chassi=" + chassi + ", ano=" + ano + ", modelo=" + modelo + ", fabricante=" + fabricante + ", potencia=" + potencia + ", arCondicionado=" + arCondicionado + '}';
    }
    
    
}
