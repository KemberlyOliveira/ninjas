package br.com.etec.ninjas.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Entity 
@Table(name = "missao") 
@Data 
public class Missao {
    

    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO) 
    @Column(name = "id_missao")
    private Long idMissao;

    @NotBlank(message = "Nome é obrigatorio.")
    @Size(min = 3, max = 255, message = "Nome deve ter entre 3 e 255 caracteres.")
    @Column(name = "nome_missao", nullable = false) 
    private String nome;

    @Column(name = "descricao_missao", nullable = false)
    private String descricao;

    @Column(name = "dificuldade_missao", nullable = false ) 
    private String dificuldade;

}
