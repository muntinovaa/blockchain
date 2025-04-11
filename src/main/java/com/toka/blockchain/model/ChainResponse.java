package com.toka.blockchain.model;

import lombok.*;
import java.util.List;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ChainResponse {
    private Integer length;
    private List<Block> chain;
}
