package dev.joaorooliveira.help_desk.infra.exception;

public class RegraNegocioException extends RuntimeException{

    public RegraNegocioException(String message) {
        super(message);
    }
}
