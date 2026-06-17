package br.com.etec.ninjas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.etec.ninjas.model.Missao;
import br.com.etec.ninjas.repository.MissaoRepository;

@Service
public class MissaoService {

    @Autowired
    private MissaoRepository missaoRepository;

    public Missao cadastrarMissao(Missao missao) {
        return missaoRepository.save(missao);
    }

    public List<Missao> listarMissoes() {
        return missaoRepository.findAll();
    }


    public Missao pesquisarMissaoPorDificuldade(String dificuldade) {
        return missaoRepository.findByDificuldade(dificuldade);
    }



    public Missao atualizarMissao(Long id, Missao missaoAtualizada) {

        Optional<Missao> missaoCadastrada = missaoRepository.findById(id);

        if (missaoCadastrada.isPresent()) {
            Missao missao = missaoCadastrada.get();

            missao.setNome(missaoAtualizada.getNome());
            missao.setDescricao(missaoAtualizada.getDescricao());
            missao.setDificuldade(missaoAtualizada.getDificuldade());

            return missaoRepository.save(missao);
        }

        return null; 
    }

    public void deletarMissao(Long id) {
        missaoRepository.deleteById(id);
    }
}