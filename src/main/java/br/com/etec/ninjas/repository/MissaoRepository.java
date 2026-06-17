package br.com.etec.ninjas.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.etec.ninjas.model.Missao; 

public interface MissaoRepository extends JpaRepository<Missao, Long> { 


    Missao findByDificuldade(String dificuldade);


    List<Missao> findByDificuldadeContaining(String dificuldade);
}
