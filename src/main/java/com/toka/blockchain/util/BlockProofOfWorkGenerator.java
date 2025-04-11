package com.toka.blockchain.util;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class BlockProofOfWorkGenerator {


    public static String PROOF_OF_WORK = "0000";

    public static Long proofOfWork(Long lastProof) {

        long proof = 0L;

        while (!validProof(lastProof, proof))
            proof += 1L;

        return proof;
    }

    public static boolean validProof(Long lastProof, Long proof) {

        String s = "" + lastProof + "" + proof;

        String sha256 = Hashing.sha256().hashString(s, StandardCharsets.UTF_8).toString();

        return sha256.endsWith(PROOF_OF_WORK);
    }
}
