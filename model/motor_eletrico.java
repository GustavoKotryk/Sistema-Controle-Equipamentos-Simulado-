package model;

public class motor_eletrico extends Equipamentos{
    protected double potencia;

    public motor_eletrico(){
        super("", "", 0, 0.0); 
        this.potencia = 0.0;
    }

    public motor_eletrico(String cod, String nome, int quantidade , double preco , double potencia ){
        super(cod, nome, quantidade, preco);
        this.potencia = potencia;
    }

    public double getPotencia(){
        return potencia;
    }

    public void serPotencia(double potencia){
        this.potencia = potencia;
    }

    @Override
    public String toString(){
        return "Motor-Eletrico [Cód: ]" + codigo + "Nome: " + nome + "Quantidade: " + quantidade + "Preço: " + preco + "Potência: " + potencia + "]";
    }
}
