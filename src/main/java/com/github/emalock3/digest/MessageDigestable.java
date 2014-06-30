package com.github.emalock3.digest;

import com.github.emalock3.digest.MessageDigestExtensions.Algorithm;

public interface MessageDigestable {
    
    default public MessageDigestParameter.Builder createMessageDigestParameterBuilder() {
        return MessageDigestParameter.Builder.from(this);
    }
    
    default public byte[] toMessageDigest() {
        return toMessageDigest(
                createMessageDigestParameterBuilder().build());
    }
    
    default public byte[] toMessageDigest(Algorithm algorithm) {
        return toMessageDigest(
                createMessageDigestParameterBuilder().algorithm(algorithm).build());
    }
    
    default public byte[] toMessageDigest(MessageDigestParameter param) {
        return MessageDigestExtensions.toMessageDigest(param);
    }
    
    default public String toHexMessageDigest() {
        return toHexMessageDigest(
                createMessageDigestParameterBuilder().build());
    }
    
    default public String toHexMessageDigest(Algorithm algorithm) {
        return toHexMessageDigest(
                createMessageDigestParameterBuilder().algorithm(algorithm).build());
    }
    
    default public String toHexMessageDigest(MessageDigestParameter param) {
        return MessageDigestExtensions.toHexMessageDigest(param);
    }
}
