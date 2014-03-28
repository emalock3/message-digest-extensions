package com.github.emalock3.digest;

import com.github.emalock3.digest.MessageDigestExtensions.Algorithm;

public interface MessageDigestable {
    
    default public byte[] toMessageDigest() {
        return MessageDigestExtensions.toMessageDigest(this);
    }
    
    default public byte[] toMessageDigest(Algorithm algorithm) {
        return MessageDigestExtensions.toMessageDigest(this, algorithm);
    }
    
    default public String toHexMessageDigest() {
        return MessageDigestExtensions.toHexMessageDigest(this);
    }
    
    default public String toHexMessageDigest(Algorithm algorithm) {
        return MessageDigestExtensions.toHexMessageDigest(this, algorithm);
    }
}
