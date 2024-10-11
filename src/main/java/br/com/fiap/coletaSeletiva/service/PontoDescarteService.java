package br.com.fiap.coletaSeletiva.service;

import br.com.fiap.coletaSeletiva.dto.PontoExibicaoDTO;
import br.com.fiap.coletaSeletiva.exceptions.PontoNaoEncontradoException;
import br.com.fiap.coletaSeletiva.model.PontoDescarte;
import br.com.fiap.coletaSeletiva.repository.PontoDescarteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PontoDescarteService {

    @Autowired
    private PontoDescarteRepository pontoDescarteRepository;

    public PontoDescarte salvarPonto(PontoDescarte pontoDescarte){
        return pontoDescarteRepository.save(pontoDescarte);
    }

    public PontoExibicaoDTO buscarPontoPorId(Long id){
        Optional<PontoDescarte> pontoDescarteOptional =
                pontoDescarteRepository.findById(id);

        if(pontoDescarteOptional.isPresent()){
            return new PontoExibicaoDTO(pontoDescarteOptional.get());
        } else {
            throw new PontoNaoEncontradoException("Ponto de coleta não existe!");
        }
    }

    public List<PontoExibicaoDTO> listarPontos(){

        return pontoDescarteRepository
                .findAll()
                .stream()
                .map(PontoExibicaoDTO::new)
                .toList();
    }

    public void excluirPonto(Long id){
        Optional<PontoDescarte> pontoDescarteOptional =
                pontoDescarteRepository.findById(id);

        if(pontoDescarteOptional.isPresent()){
            pontoDescarteRepository.delete(pontoDescarteOptional.get());
        } else {
            throw new RuntimeException("Ponto de descarte encontrado!");
        }
    }

    public PontoDescarte atualizar(PontoDescarte pontoDescarte){
        Optional<PontoDescarte> pontoDescarteOptional =
                pontoDescarteRepository.findById(pontoDescarte.getPontoId());

        if(pontoDescarteOptional.isPresent()){
            return pontoDescarteRepository.save(pontoDescarte);
        } else {
            throw new RuntimeException("Ponto de descarte não encontrado!");
        }
    }
}
