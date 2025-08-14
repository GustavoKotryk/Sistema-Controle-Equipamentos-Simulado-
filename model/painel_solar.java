package model;

public class painel_solar extends Equipamento {
    protected String tensao;

    public painel_solar(){
    super("", "", 0, 0.0);
    this.tensao = "";
    }

    public painel_solar(String codigo, String nome, int quantidade, double preco, String tensao){
        super(codigo, nome, quantidade, preco);
        this.tensao = tensao;
    }

    public String getTensao(){
        return tensao;
    }

    public void setTensao(String tensao){
        this.tensao = tensao;
    }


    @Override
    public String toString(){
        return "Painel Solar [Cód: ]" + codigo + "Nome: " + nome + "Quantidade? " + quantidade + "Preço: " + preco + "Tensão: " + tensao;
    }
}

