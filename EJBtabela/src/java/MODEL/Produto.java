/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

/**
 *
 * @author wisecase2
 */
public class Produto {
    private int id;
    private int categ_pk;
    private String nome;
    private String img;
    private String desc;
    private float preco;
    private float desconto;
    private float qtde;

    public Produto() {
    }

    public Produto(int id, String nome, String img, float preco) {
        this.id = id;
        this.nome = nome;
        this.img = img;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public float getDesconto() {
        return desconto;
    }

    public void setDesconto(float desconto) {
        this.desconto = desconto;
    }

    public int getCateg_pk() {
        return categ_pk;
    }

    public void setCateg_pk(int categ_pk) {
        this.categ_pk = categ_pk;
    }

    public float getQtde() {
        return qtde;
    }

    public void setQtde(float qtde) {
        this.qtde = qtde;
    }
}
