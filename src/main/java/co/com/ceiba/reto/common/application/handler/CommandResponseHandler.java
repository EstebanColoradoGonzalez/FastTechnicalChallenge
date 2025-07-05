package co.com.ceiba.reto.common.application.handler;

import jakarta.transaction.Transactional;

public interface CommandResponseHandler<C, R> {
    @Transactional
    R execute(C command);
}