package com.sal.loggy.elk.dto;

import lombok.Builder;

@Builder
public record TransactionDTO(
        String origin,
        Long userId,
        String transactionId,
        String timeZone,
        String uri,
        Long threadId
) {
}