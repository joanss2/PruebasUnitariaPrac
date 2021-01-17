package data;

import Exceptions.*;

import java.util.Arrays;

final public class DigitalSignature {

    public byte[] signature;

    public DigitalSignature(String signature) throws eSignatureException {
        if (checkSign(signature))                                               // Comprove signature to check that the format is correct, if not throw exception
            this.signature = signature.getBytes();
    }

    public boolean checkSign(String signature) throws eSignatureException {
        if (signature == null || signature.equals(""))
            throw new eSignatureException("Invalid Signature");
        for (int i = 0; i < signature.length(); i++) {
            if ((int) signature.charAt(i) > 128)                            // We don't check that is less than -127 because don't transform to negative values
                throw new eSignatureException("Invalid Signature");

        }
        return true;
    }

    // Getter
    public byte[] getsignature() {
        return this.signature;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DigitalSignature sign = (DigitalSignature) o;
        return Arrays.equals(signature, sign.signature);
    }
}
