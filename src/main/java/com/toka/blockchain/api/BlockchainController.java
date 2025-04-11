package com.toka.blockchain.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toka.blockchain.model.*;
import com.toka.blockchain.service.Blockchain;
import com.toka.blockchain.util.BlockProofOfWorkGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping({"/"})
public class BlockchainController {
    @Autowired
    private Blockchain blockChain;
    @Autowired
    private ObjectMapper mapper;
    public static final String NODE_ID = UUID.randomUUID().toString().replace("-", "");
    public static final String NODE_ACCOUNT_ADDRESS = "0";
    public static final BigDecimal MINING_CASH_AWARD;

    public BlockchainController() {
    }

    @GetMapping({"/mine"})
    public MineResponse mine() throws JsonProcessingException {
        Block lastBlock = this.blockChain.lastBlock();
        Long lastProof = lastBlock.getProof();
        Long proof = BlockProofOfWorkGenerator.proofOfWork(lastProof);
        this.blockChain.addTransaction("0", NODE_ID, MINING_CASH_AWARD);
        Block newBlock = this.blockChain.createBlock(proof, lastBlock.hash(this.mapper));
        return MineResponse.builder().message("New Block Forged").index(newBlock.getIndex()).transactions(newBlock.getTransactions()).proof(newBlock.getProof()).previousHsh(newBlock.getPreviousHash()).build();
    }

    @GetMapping({"/chain"})
    public ChainResponse fullChain() throws JsonProcessingException {
        return ChainResponse.builder().chain(this.blockChain.getChain()).length(this.blockChain.getChain().size()).build();
    }

    @PostMapping({"/transactions"})
    public TransactionResponse newTransaction(@RequestBody @Valid Transaction trans) throws JsonProcessingException {
        Long index = this.blockChain.addTransaction(trans.getSender(), trans.getRecipient(), trans.getAmount());
        return TransactionResponse.builder().index(index).build();
    }

    static {
        MINING_CASH_AWARD = BigDecimal.ONE;
    }
}
