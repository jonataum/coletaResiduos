package br.com.fiap.coletaSeletiva.dto;
import br.com.fiap.coletaSeletiva.model.PontoDescarte;

public record PontoExibicaoDTO(

    Long pontoId,
    String nome,
    String endereco,
    String tipoResiduo,
    Double capacidadeMax,
    Double quantidadeAtual){

    public PontoExibicaoDTO(PontoDescarte pontoDescarte){
        this(
                pontoDescarte.getPontoId(),
                pontoDescarte.getNome(),
                pontoDescarte.getEndereco(),
                pontoDescarte.getTipoResiduo(),
                pontoDescarte.getCapacidadeMax(),
                pontoDescarte.getQuantidadeAtual());
    }
}
