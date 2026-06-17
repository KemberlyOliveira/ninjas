package br.com.etec.ninjas.repository; //  Indica o pacote onde a interface está localizada

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.query.JpqlQueryBuilder.Select;

import br.com.etec.ninjas.model.Ninja;

/**
 * Interface Repository para a entidade Ninja.
 * Ela herda todos os métodos de CRUD (Criar, Ler, Atualizar e Deletar) do Spring Data JPA.
 */
 //@Repository// Indica ao Spring que esta classe é um componente de acesso a dados (Repository) e permite a tradução de exceções do banco.
public interface NinjaRepository extends JpaRepository<Ninja, Long> {
    
    // Por herdar de JpaRepository, você já ganha de graça métodos como:
    // - save(Ninja ninja) -> Salva ou atualiza um ninja no banco
    // - findById(Long id) -> Busca um ninja pelo ID
    // - findAll()         -> Lista todos os ninjas
    // - deleteById(Long id)-> Deleta um ninja pelo ID



    Ninja findByNome (String nome);

    List <Ninja> findByNomeContaining(String nome);
    
}