package br.com.etec.ninjas.model;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Classe que representa a entidade "Ninjas" no sistema e a tabela "ninja" no Banco de Dados.
 * Utiliza as especificações do Jakarta Persistence (JPA).
 */
@Valid
@Entity // Define que esta classe é uma entidade gerenciada pelo JPA e será mapeada como uma tabela.
@Table(name = "ninja") // Especifica o nome exato da tabela no banco de dados.
@Data //  É a junção de @ToString, @EqualsAndHashCode, @Getter, @Setter e @RequiredArgsConstructor (cria construtor para campos final).
public class Ninja {
    
    @Id // Define o atributo abaixo como a Chave Primária (Primary Key) da tabela.
    @GeneratedValue(strategy = GenerationType.AUTO) // Configura o ID como auto-incremento (gerado automaticamente pelo banco de dados).
    @Column(name = "id_ninja") // Mapeia o nome da coluna de ID no banco de dados.
    private Long id_ninja; 

    @NotBlank(message = "Nome é obrigatorio.")
    @Size(min = 3, max = 255, message = "Nome deve yer entre 3 e 255 caracteres.")
    @Column(name = "nome_ninja", nullable = false) // Mapeia a coluna; 'nullable = false' torna o campo obrigatório (NOT NULL).
    private String nome;

    @CPF
    @Column(name = "cpf_ninja", nullable = false, unique = true) // 'unique = true' garante que não haverá CPFs duplicados no banco.
    private String cpf;

    @Email(message = "Elail invalido.")
    @Column(name = "email_ninja", nullable = false, unique = true) // Campo obrigatório e único.
    private String email;

}