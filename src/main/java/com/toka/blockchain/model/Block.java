package com.toka.blockchain.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.hash.Hashing;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Data
@Builder
@JsonPropertyOrder(alphabetic = true)
@AllArgsConstructor
@NoArgsConstructor
public class Block {

    private Long index;

    private Long timestamp;

    private List<Transaction> transactions;

    private Long proof;

    // hash of the previous Block
    private String previousHash;

    public static final Long GENESIS_BLOCK_PROOF = 100L;
    public static final String GENESIS_BLOCK_PREV_HASH = "1";

    public String hash(ObjectMapper mapper) throws JsonProcessingException {
        String json = mapper.writeValueAsString(this);
        return Hashing.sha256().hashString(json, StandardCharsets.UTF_8).toString();
    }
}