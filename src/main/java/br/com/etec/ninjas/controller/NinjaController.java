package br.com.etec.ninjas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
}