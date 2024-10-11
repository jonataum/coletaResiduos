package br.com.fiap.coletaSeletiva.advice;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> manusearArgumentosInvalidos(MethodArgumentNotValidException erro){
        Map<String, String> mapaErro = new HashMap<>();
        List<FieldError> campos = erro.getBindingResult().getFieldErrors();

        for (FieldError campo : campos){
            mapaErro.put(campo.getField(), campo.getDefaultMessage());
        }

        return mapaErro;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public Map<String, String> manusearIntegridadeDados(){
        Map<String, String> mapaErro = new HashMap<>();
        mapaErro.put("erro", "Usuário já cadastrado!");
        return mapaErro;
    }

//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ExceptionHandler(RuntimeException.class)
//    public Map<String, String> manusearErroNaoEncontrado(RuntimeException erro){
//        Map<String, String> mapaErro = new HashMap<>();
//        mapaErro.put("erro", erro.getMessage());
//        return mapaErro;
//    }
}
