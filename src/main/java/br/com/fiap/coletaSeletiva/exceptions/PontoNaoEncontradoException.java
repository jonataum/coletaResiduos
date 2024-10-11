package br.com.fiap.coletaSeletiva.exceptions;

public class PontoNaoEncontradoException extends RuntimeException{

    public PontoNaoEncontradoException(String mensagem){
        super(mensagem);
    }
}
