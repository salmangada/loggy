package com.sal.loggy.elk.interceptor;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sal.loggy.elk.dto.TransactionDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;
import java.util.UUID;

@Slf4j
@Component
@AllArgsConstructor
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class ApplicationInterceptor implements HandlerInterceptor {

    private final ObjectMapper mapper;

    @Override
    @SuppressWarnings("NullableProblems")
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.info("Requested Path: {}", request.getServletPath());
        setMDC(request);
        return true;
    }

    private void setMDC(HttpServletRequest request) {
        Map<String, String> transactions = mapper.convertValue(getTransactionDTO(request), new TypeReference<>() {
        });
        transactions.forEach(MDC::put);
    }

    private TransactionDTO getTransactionDTO(HttpServletRequest request) {
        return TransactionDTO.builder()
                .origin(request.getHeader("origin"))
                .userId(Long.valueOf(request.getHeader("user-id")))
                .transactionId(UUID.randomUUID().toString())
                .timeZone(request.getHeader("x-timezone"))
                .uri(request.getRequestURI())
                .build();
    }
}
