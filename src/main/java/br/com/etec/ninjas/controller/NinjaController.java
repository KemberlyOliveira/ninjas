package br.com.etec.ninjas.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.etec.ninjas.model.Ninja;
import br.com.etec.ninjas.service.NinjaService;
import jakarta.validation.Valid;

/**
 * Controller responsável por receber as requisições HTTP relacionadas a Ninjas.
 * Atua como a "porta de entrada" da aplicação — recebe os dados, repassa para
 * a Service e devolve a resposta ao cliente (ex: Postman, front-end).
 */
@RestController  // Indica que esta classe é um Controller REST (responde JSON automaticamente)
@RequestMapping("/ninjas") // Define a URL base: todas as rotas aqui começam com /ninjas
public class NinjaController {

    /**
     * Injeção de dependência do Spring.
     * Em vez de criar o objeto manualmente com "new NinjaService()",
     * o Spring cria e injeta automaticamente — isso se chama IoC (Inversão de Controle).
     */
    @Autowired
    private NinjaService ninjaService;

    // o Mapping siginifica que mapeia antes de executar

    /**
     * Endpoint para cadastrar um novo Ninja.
     *
     * Fluxo da requisição:
     *   Cliente envia JSON  →  @RequestBody converte para objeto Ninja
     *   →  ninjaService processa a regra de negócio
     *   →  retorna o Ninja salvo (com ID gerado, por exemplo)
     *
     * Método HTTP : POST
     * URL         : /ninjas
     * Corpo       : JSON com os dados do Ninja
     * Retorno     : objeto Ninja salvo, serializado em JSON
     */
    @PostMapping // Mapeia requisições HTTP POST para este método
    public Ninja cadastrarNinja(@Valid @RequestBody Ninja ninja) {
        // @RequestBody: o Spring lê o JSON do corpo da requisição
        // e converte automaticamente para um objeto do tipo Ninja (desserialização)

        // Delega a lógica de negócio para a camada Service
        // O Controller não deve conter regras — só receber e responder
        return ninjaService.cadastrarNinja(ninja);
    }

/**
 * Endpoint para listar todos os Ninjas cadastrados.
 *
 * Método HTTP : GET
 * URL         : /ninjas
 * Corpo       : nenhum (GET não envia corpo)
 * Retorno     : lista de Ninjas em JSON
 */
@GetMapping // Mapeia requisições HTTP GET para este método.
            // GET = "me dá uma informação" — usado para BUSCAR dados, nunca para criar ou editar.
public List<Ninja> listaNinjas() {

    // Delega para a Service, que por sua vez chama o Repository.
    // O Controller continua sem regra de negócio — só recebe e responde.
    return ninjaService.listarNinjas();
}

// Método HTTP : GET
// URL         : /ninjas/id/1  (exemplo)
// Busca um ninja pelo ID exato.
// Optional<Ninja> == pode retornar um Ninja ou vazio — não estoura erro se não achar.
@GetMapping("/id/{id}")
public Optional<Ninja> pesquisarNinjas(@PathVariable Long id) {
    // @PathVariable == captura o {id} da URL e joga dentro da variável Long id.
    // Ex: GET /ninjas/id/3  →  id = 3
    return ninjaService.pesquisarNinjas(id);
}


// Método HTTP : GET
// URL         : /ninjas/nomecompleto/Naruto  (exemplo)
// Busca um ninja pelo nome EXATO — precisa bater certinho.
@GetMapping("/nomecompleto/{nome}")
public Ninja pesquisarNinja(@PathVariable String nome) {
    // @PathVariable == captura o {nome} da URL.
    // Ex: GET /ninjas/nomecompleto/Naruto  →  nome = "Naruto"
    // Devolve UM único Ninja (ou null se não achar).
    return ninjaService.pesquisarNinjaPorNome(nome);
}


// Método HTTP : GET
// URL         : /ninjas/nome/Na  (exemplo)
// Busca ninjas que CONTENHAM o trecho no nome — busca parcial.
@GetMapping("/nome/{nome}")
public List<Ninja> pesquisarNinjaPorParteDoNome(@PathVariable String nome) {
    // Ex: GET /ninjas/nome/Na  →  pode retornar [Naruto, Nagato, Nawaki...]
    // Devolve uma LISTA pois podem existir vários com aquele trecho.
    return ninjaService.pesquisarNinjaPorParteDoNome(nome);
}


// Método HTTP : PUT
// URL         : /ninjas/1  (exemplo)
// PUT == substitui/atualiza um recurso existente. Precisa do ID na URL
//        e dos novos dados no corpo (JSON).
@PutMapping("/{id}")
public Ninja atualizarNinja(@PathVariable Long id, @Valid @RequestBody Ninja ninja) {
    // @PathVariable  == pega o {id} da URL — identifica QUEM será atualizado.
    // @RequestBody   == pega o JSON do corpo da requisição — são os NOVOS dados.
    // @Valid         == ativa as validações anotadas no model Ninja
    //                   (ex: @NotNull, @Size, @Email...) antes de entrar no método.
    //                   Se algum campo for inválido, o Spring rejeita antes de chamar a Service.
    return ninjaService.atualizarNinja(id, ninja);
}


// Método HTTP : DELETE
// URL         : /ninjas/1  (exemplo)
// DELETE == remove permanentemente o registro do banco.
@DeleteMapping("/{id}")
public void deletarNinja(@PathVariable Long id) {
    // @PathVariable == pega o {id} da URL — identifica QUEM será deletado.
    // Sem @RequestBody: DELETE não envia corpo, só o ID na URL basta.
    // void == não devolve nada ao cliente após deletar.
    ninjaService.deletarNinja(id);
}

}