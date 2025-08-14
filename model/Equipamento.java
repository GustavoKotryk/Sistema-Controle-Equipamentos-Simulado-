package model;

public class Equipamento {
    protected String codigo;
    protected String nome;
    protected int quantidade;
    protected double preco;

    public Equipamento(String codigo, String nome, int quantidade, double preco){
        this.codigo = codigo;
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public String getCodigo(){
        return nome;
    }
    public void setCodigo(String codigo){
        this.codigo = codigo;
    }

    public String getNome(){
        return nome;
    }

    public void serNome(String nome){
        this.nome = nome;
    }

    public int getQuantidade(){
        return quantidade;
    }

    public void setQuantidade (int quantidade){
        this.quantidade = quantidade;
    }

    public double getPreco(){
        return preco;
    }

    public void serPreco(double preco){
        this.preco = preco;
    }



    @Override
    public String toString(){
        return "Equipamento [ Cód:" + nome + " Nome:" + nome + " Quantidade:" + quantidade + " Preço:" + preco + " ]";
    }
}
