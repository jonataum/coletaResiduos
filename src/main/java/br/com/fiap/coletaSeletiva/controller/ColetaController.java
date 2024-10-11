package br.com.fiap.coletaSeletiva.controller;

import br.com.fiap.coletaSeletiva.model.Coleta;
import br.com.fiap.coletaSeletiva.service.ColetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ColetaController {

    @Autowired
    private ColetaService coletaService;

    @PostMapping("/coleta")
    @ResponseStatus(HttpStatus.CREATED)
    public Coleta salvar(@RequestBody Coleta coleta){
        return coletaService.salvarColeta(coleta);
    }

    @GetMapping("/coleta")
    @ResponseStatus(HttpStatus.OK)
    public List<Coleta> listarTodos(){
        return coletaService.listarColetas();
    }

    @GetMapping("/coleta/{coletaId}")
    public Coleta buscarPorId(@PathVariable Long coletaId){
        return coletaService.buscarColetaPorId(coletaId);
    }

    @DeleteMapping("/coleta/{coletaId}")
    public void excluir(@PathVariable Long coletaId){
        coletaService.excluirColeta(coletaId);
    }

    @PutMapping("/coleta")
    @ResponseStatus(HttpStatus.OK)
    public Coleta atualizar(@RequestBody Coleta coleta){
        return coletaService.atualizarColeta(coleta);
    }
}
