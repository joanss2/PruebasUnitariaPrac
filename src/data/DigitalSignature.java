package data;

import Exceptions.*;

import java.util.Arrays;

final public class DigitalSignature {

    public byte[] signature;

    public DigitalSignature(String signature) throws eSignatureException {       // FALTE COMPROBAR SI ALGUN DELLS NO ES UN BYTE --- ?? COM HO FEM PASEM UN INT?¿
        if (checkSign(signature))
            this.signature = signature.getBytes();
    }

    public boolean checkSign(String signature) throws eSignatureException {
        if (signature == null || signature.equals(""))
            throw new eSignatureException("Invalid Signature");
        for (int i = 0; i < signature.length(); i++) {
            if ((int) signature.charAt(i) > 128)                            // No comprovem que és menor que -127 perk no transforma res a valors negatius
                throw new eSignatureException("Invalid Signature");

        }
        return true;
    }

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
