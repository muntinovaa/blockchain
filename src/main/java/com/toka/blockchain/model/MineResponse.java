package com.toka.blockchain.model;

import lombok.*;

import java.util.List;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MineResponse {
    private String message;
    private Long index;
    private List<Transaction> transactions;
    private Long proof;
    private String previousHsh;
}
