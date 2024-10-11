package br.com.fiap.coletaSeletiva.controller;

import br.com.fiap.coletaSeletiva.dto.PontoExibicaoDTO;
import br.com.fiap.coletaSeletiva.model.PontoDescarte;
import br.com.fiap.coletaSeletiva.service.PontoDescarteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PontoDescarteController {

    @Autowired
    private PontoDescarteService pontoDescarteService;

    @PostMapping("/ponto-descarte")
    @ResponseStatus(HttpStatus.CREATED)
    public PontoDescarte salvar(@RequestBody PontoDescarte pontoDescarte) {
        return pontoDescarteService.salvarPonto(pontoDescarte);
    }

    @GetMapping("/ponto-descarte")
    @ResponseStatus(HttpStatus.OK)
    public List<PontoExibicaoDTO> listarTodos(){
        return pontoDescarteService.listarPontos();
    }

    @GetMapping("/ponto-descarte/{pontoId}")
    public ResponseEntity<PontoExibicaoDTO> buscarPorId(@PathVariable Long pontoId) {
            return ResponseEntity.ok(pontoDescarteService.buscarPontoPorId(pontoId));
    }

    @DeleteMapping("/ponto-descarte/{pontoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long pontoId) {
        pontoDescarteService.excluirPonto((pontoId));
    }

    @PutMapping("/ponto-descarte")
    @ResponseStatus(HttpStatus.OK)
    public PontoDescarte atualizar(@RequestBody PontoDescarte pontoDescarte){
        return pontoDescarteService.atualizar(pontoDescarte);
    }

}
