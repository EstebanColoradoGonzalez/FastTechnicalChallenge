package co.com.ceiba.reto.common.application.handler;

import jakarta.transaction.Transactional;

public interface CommandParameterResponseHandler<C, P, R> {
    @Transactional
    R execute(C command, P parameter);
}