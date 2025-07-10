package com.zello.zello.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.stream.Collectors;

// Record usado para representar um erro de API no padrão RFC 7807 (problem details)
public record ApiError(
        String title, // Título do erro (ex: "Validation Failed")
        int status, // Código HTTP do erro (ex: 400, 404, etc.)
        String detail, // Descrição mais detalhada do erro
        String instance, // URI onde o erro ocorreu
        OffsetDateTime timestamp, // Data e hora do erro (com fuso horário)
        Object message // Mensagens de erro agrupadas por campo inválido
) {
    public static ApiError from(MethodArgumentNotValidException e, HttpServletRequest req) { // Método auxiliar para criar um ApiError a partir de uma exceção de validação
        // Extrai os erros de validação e agrupa por nome do campo,
        // coletando todas as mensagens associadas a cada campo
        var errors = e.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.groupingBy(
                        FieldError::getField, // Agrupa por nome do campo
                        Collectors.mapping(
                                DefaultMessageSourceResolvable::getDefaultMessage, // Pega a mensagem padrão
                                Collectors.toList() // Cria uma lista de mensagens por campo
                        )
                ));

        // Tenta recuperar informações adicionais do corpo da exceção, se disponíveis
        var title = e.getBody().getTitle(); // Define título padrão caso nulo
        var status = e.getBody().getStatus(); // Código HTTP padrão 400
        var detail = e.getBody().getDetail(); // Detalhe padrão
        var instance = req.getRequestURI(); // Obtém a URI onde ocorreu a exceção
        var timestamp = OffsetDateTime.now(); // Gera o timestamp atual com fuso horário
        var message = Map.copyOf(errors);

        return new ApiError(title, status, detail, instance, timestamp, message);
    }
}
