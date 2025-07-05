package co.com.ceiba.reto.common.application.handler;

import jakarta.transaction.Transactional;

public interface ParameterResponseHandler<P, R> {
    @Transactional
    R execute(P parameter);
}
