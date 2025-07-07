package co.com.ceiba.reto.common.application.handler;

import jakarta.transaction.Transactional;

public interface ResponseHandler<R> {
    @Transactional
    R execute();
}