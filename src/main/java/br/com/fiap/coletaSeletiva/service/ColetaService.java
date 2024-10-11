package br.com.fiap.coletaSeletiva.service;

import br.com.fiap.coletaSeletiva.model.Coleta;
import br.com.fiap.coletaSeletiva.model.PontoDescarte;
import br.com.fiap.coletaSeletiva.repository.ColetaRepository;
import br.com.fiap.coletaSeletiva.repository.PontoDescarteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ColetaService {

    @Autowired
    private ColetaRepository coletaRepository;

    @Autowired
    private PontoDescarteRepository pontoDescarteRepository;

    public Coleta salvarColeta(Coleta coleta) {
        Optional<PontoDescarte> pontoDescarteOptional = pontoDescarteRepository.findById(coleta.getPontoDescarte().getPontoId());

        if (pontoDescarteOptional.isPresent()) {
            coleta.setPontoDescarte(pontoDescarteOptional.get());
        } else {
            throw new RuntimeException("Ponto de coleta não existe!");
        }
        return coletaRepository.save(coleta);
    }

    public Coleta buscarColetaPorId(Long id){
        Optional<Coleta> coletaOptional =
                coletaRepository.findById(id);
        if(coletaOptional.isPresent()){
            return coletaOptional.get();
        } else{
            throw new RuntimeException("Coleta não existe!");
        }
    }

    public List<Coleta> listarColetas(){
        return coletaRepository.findAll();
    }

    public void excluirColeta(Long id){
        Optional<Coleta> coletaOptional =
                coletaRepository.findById(id);
        if(coletaOptional.isPresent()){
            coletaRepository.delete(coletaOptional.get());
        } else {
            throw new RuntimeException("Coleta não encontrada!");
        }
    }

    public Coleta atualizarColeta(Coleta coleta){
        Optional<Coleta> coletaOptional =
                coletaRepository.findById(coleta.getColetaId());

        if(coletaOptional.isPresent()){
            return coletaRepository.save(coleta);
        } else {
            throw new RuntimeException("Coleta não encontrada!");
        }
    }
}
