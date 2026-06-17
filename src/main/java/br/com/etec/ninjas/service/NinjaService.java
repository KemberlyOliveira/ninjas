package br.com.etec.ninjas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.etec.ninjas.model.Ninja;
import br.com.etec.ninjas.repository.NinjaRepository;

@Service
public class NinjaService {

    @Autowired
    // Injeção de Dependência: O Spring cuida da criação e gerenciamento do objeto.
    // Não precisamos criar a conexão com o banco de dados na mão (instanciar com 'new').
    // Sem a injeção, seria como se tivéssemos que fabricar as peças do carro toda
    // vez que fôssemos dirigir.
    private NinjaRepository ninjaRepository;

    // public           == Modificador de acesso: significa que qualquer outra classe do projeto pode usar este método.
    // Ninja            == Tipo de Retorno: é o tipo de dado que o método vai devolver quando terminar de rodar (vai devolver um objeto Ninja).
    // cadastrarNinja   == Nome do Método: o nome que escolhemos para chamar/executar essa ação.
    // (Ninja ninja)    == Parâmetros: o que o método precisa receber para funcionar.
    //                      - "Ninja" (com N maiúsculo) é a classe/molde (o tipo do objeto).
    //                      - "ninja" (com n minúsculo) é a variável/apelido local (o objeto real que chegou, ex: o Naruto).
    public Ninja cadastrarNinja(Ninja ninja) {

        // return            == Palavra-chave de retorno: diz ao Java "devolva o resultado disso aqui para quem chamou o método".
        // ninjaRepository   == O nosso "repositório": a ferramenta que o Spring injetou e que tem acesso direto ao banco de dados.
        // .save(...)        == Função pronta do Spring Data: o método mágico que pega os dados do objeto e cria o "INSERT INTO..." no SQL por baixo dos panos.
        // (ninja)           == O argumento: estamos passando o ninja que recebemos lá em cima para dentro da função .save().
        return ninjaRepository.save(ninja);
    }


// public       == Qualquer classe do projeto pode chamar este método.
// List<Ninja>  == O método não devolve um único Ninja, mas uma LISTA de Ninjas.
//                 List é uma coleção ordenada do Java — como uma fileira de objetos.
//                 <Ninja> indica o tipo de elemento dentro da lista (só Ninjas aqui).
// listarNinjas == Nome do método: descreve bem o que ele faz — listar todos os ninjas.
// ()           == Sem parâmetros: não precisa receber nada para funcionar,
//                 pois a busca é geral (traz TODOS os registros).
public List<Ninja> listarNinjas() {

    // return                == Devolve o resultado para quem chamou o método.
    // ninjaRepository       == Objeto injetado pelo Spring com acesso ao banco de dados.
    // .findAll()            == Método herdado da interface JpaRepository.
    //                          Por baixo dos panos, o Spring gera automaticamente:
    //                          SELECT * FROM ninjas
    //                          Você não precisa escrever SQL — o JPA faz isso por você.
    return ninjaRepository.findAll();
}

// o Optional faz nn ser obrtgatorio ter um retorno
// Long id o parametro 
public Optional<Ninja> pesquisarNinjas(Long id) {
    return ninjaRepository.findById(id);
}


public Ninja pesquisarNinjasPorNome (String nome){
    return ninjaRepository.findByNome(nome);
}



}
