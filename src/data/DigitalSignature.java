package data;
import Exceptions.eSignatureException;

import java.util.Arrays;

final public class DigitalSignature {

    public byte[] signature;

    public DigitalSignature(String signature) throws eSignatureException {       // FALTE COMPROBAR SI ALGUN DELLS NO ES UN BYTE --- ?? COM HO FEM PASEM UN INT?¿
        if(checkSign(signature))
            this.signature = signature.getBytes();
        throw new eSignatureException("Invalid Signature");
    }

    public boolean checkSign(String signature){
        if(signature == null)
            return false;
        if(signature.equals(""))
            return false;

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
