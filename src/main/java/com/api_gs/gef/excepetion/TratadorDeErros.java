// package com.api_gs.gef.excepetion;

// import java.time.LocalDateTime;
// import java.util.List;
// import java.util.stream.Collectors;

// import org.springframework.dao.DataIntegrityViolationException;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.validation.FieldError;
// import org.springframework.web.bind.MethodArgumentNotValidException;
// import org.springframework.web.bind.annotation.ExceptionHandler;
// import org.springframework.web.bind.annotation.RestControllerAdvice;

// import jakarta.persistence.EntityNotFoundException;
// import lombok.extern.slf4j.Slf4j;

// @Slf4j
// @RestControllerAdvice
// public class TratadorDeErros {

//     @ExceptionHandler(EntityNotFoundException.class)
//     public ResponseEntity<ErroResponse> tratarErro404(EntityNotFoundException ex) {
//         log.warn("Erro 404: {}", ex.getMessage());
//         return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                 .body(new ErroResponse(HttpStatus.NOT_FOUND.value(), "Recurso não encontrado", LocalDateTime.now()));
//     }

//     @ExceptionHandler(MethodArgumentNotValidException.class)
//     public ResponseEntity<List<DadosErrosValidacao>> tratarErro400(MethodArgumentNotValidException ex) {
//         var erros = ex.getFieldErrors().stream()
//                 .map(DadosErrosValidacao::new)
//                 .collect(Collectors.toList());
//         return ResponseEntity.badRequest().body(erros);
//     }

//     @ExceptionHandler(IllegalArgumentException.class)
//     public ResponseEntity<ErroResponse> tratarIllegalArgument(IllegalArgumentException ex) {
//         log.warn("Erro 400: {}", ex.getMessage());
//         return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                 .body(new ErroResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), LocalDateTime.now()));
//     }

//     @ExceptionHandler(DataIntegrityViolationException.class)
//     public ResponseEntity<ErroResponse> tratarViolacaoIntegridade(DataIntegrityViolationException ex) {
//         log.error("Erro de integridade de dados: {}", ex.getMessage());
//         return ResponseEntity.status(HttpStatus.CONFLICT)
//                 .body(new ErroResponse(HttpStatus.CONFLICT.value(), "Violação de integridade de dados", LocalDateTime.now()));
//     }

//     @ExceptionHandler(Exception.class)
//     public ResponseEntity<ErroResponse> tratarErroGeral(Exception ex) {
//         log.error("Erro inesperado: ", ex);
//         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                 .body(new ErroResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Erro interno no servidor", LocalDateTime.now()));
//     }

//     private record DadosErrosValidacao(String campo, String mensagem) {
//         public DadosErrosValidacao(FieldError erro) {
//             this(erro.getField(), erro.getDefaultMessage());
//         }
//     }

//     private record ErroResponse(int status, String mensagem, LocalDateTime timestamp) {
//     }
// }
