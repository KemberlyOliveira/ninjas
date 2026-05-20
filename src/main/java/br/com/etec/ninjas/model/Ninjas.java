package br.com.etec.ninjas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Classe que representa a entidade "Ninjas" no sistema e a tabela "ninja" no Banco de Dados.
 * Utiliza as especificações do Jakarta Persistence (JPA).
 */
@Entity // Define que esta classe é uma entidade gerenciada pelo JPA e será mapeada como uma tabela.
@Table(name = "ninja") // Especifica o nome exato da tabela no banco de dados.
public class Ninjas {
    
    @Id // Define o atributo abaixo como a Chave Primária (Primary Key) da tabela.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configura o ID como auto-incremento (gerado automaticamente pelo banco de dados).
    @Column(name = "id_ninja") // Mapeia o nome da coluna de ID no banco de dados.
    private Long id_ninja; 

    @Column(name = "nome_ninja", nullable = false) // Mapeia a coluna; 'nullable = false' torna o campo obrigatório (NOT NULL).
    private String nome;

    @Column(name = "cpf_ninja", nullable = false, unique = true) // 'unique = true' garante que não haverá CPFs duplicados no banco.
    private String cpf;

    @Column(name = "email_ninja", nullable = false, unique = true) // Campo obrigatório e único.
    private String email;

    // =========================================================================
    // CONSTRUTORES
    // =========================================================================

    /**
     * Construtor padrão (vazio). 
     * Obrigatório para o funcionamento do Hibernate/JPA para instanciar o objeto ao buscar no banco.
     */
    public Ninjas() {
    }

    /**
     * Construtor personalizado para criação de novos objetos com dados iniciais.
     * O ID não entra aqui pois é gerado automaticamente pelo banco de dados.
     */
    public Ninjas(String nome, String cpf, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }

    // =========================================================================
    // MÉTODOS GETTERS E SETTERS
    // (Necessários para que o JPA consiga ler e injetar os dados nos atributos)
    // =========================================================================

    public Long getId_ninja() {
        return id_ninja;
    }

    public void setId_ninja(Long id_ninja) {
        this.id_ninja = id_ninja;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}