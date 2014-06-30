package com.github.emalock3.digest;

import com.github.emalock3.digest.MessageDigestExtensions.Algorithm;
import java.util.Arrays;
import java.util.Objects;

public class MessageDigestParameter {
    
    private final MessageDigestable input;
    private final Algorithm algorithm;
    private final Object[] additionalValues;

    MessageDigestParameter(MessageDigestable input, Algorithm algorithm, Object[] additionalValues) {
        this.input = input;
        this.algorithm = algorithm;
        this.additionalValues = additionalValues;
    }

    MessageDigestable getInput() {
        return input;
    }

    Algorithm getAlgorithm() {
        return algorithm;
    }

    Object[] getAdditionalValues() {
        return additionalValues;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.input);
        hash = 17 * hash + Objects.hashCode(this.algorithm);
        hash = 17 * hash + Arrays.deepHashCode(this.additionalValues);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MessageDigestParameter other = (MessageDigestParameter) obj;
        if (!Objects.equals(this.input, other.input)) {
            return false;
        }
        if (this.algorithm != other.algorithm) {
            return false;
        }
        if (!Arrays.deepEquals(this.additionalValues, other.additionalValues)) {
            return false;
        }
        return true;
    }
    
    public static class Builder {
        private final MessageDigestable input;
        private final Algorithm algorithm;
        private final Object[] additionalValues;
        private static final Object[] EMPTY_VALUES = new Object[0];

        public Builder(MessageDigestable input, Algorithm algorithm, Object[] additionalValues) {
            this.input = input;
            this.algorithm = algorithm;
            this.additionalValues = additionalValues == null ? EMPTY_VALUES : additionalValues;
        }
        
        public static Builder from(MessageDigestable input) {
            return new Builder(input, null, null);
        }
        
        public Builder algorithm(Algorithm algorithm) {
            return new Builder(this.input, algorithm, this.additionalValues);
        }
        
        public Builder additionalValues(Object[] additionalValues) {
            return new Builder(this.input, this.algorithm, this.additionalValues);
        }
        
        public MessageDigestParameter build() {
            return new MessageDigestParameter(input, algorithm, additionalValues);
        }
    }
}
