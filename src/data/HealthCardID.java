package data;

import Exceptions.*;

final public class HealthCardID {
    private final String personalID;

    public HealthCardID(String code) throws FormatException {
        if (code == null)
            throw new NullPointerException();
        if (!isFormatValid(code))
            throw new FormatException("PersonalID code from HealthCardID is invalid"); //O HEALTHCARDEXCEPTIO????
        this.personalID = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HealthCardID hcardID = (HealthCardID) o;
        return personalID.equals(hcardID.personalID);
    }

    public String getPersonalID() {
        return personalID;
    }

    public boolean isFormatValid(String code) {
        char[] stringToArray = code.toCharArray();
        if (code.length() != 28)
            return false;
        if (!code.startsWith("BBBBBBBB"))
            return false;
        else if (!(Character.isLetter(stringToArray[8]) && Character.isUpperCase(stringToArray[8]))
                || !(Character.isLetter(stringToArray[9]) && Character.isUpperCase(stringToArray[9])))
            return false;
        else return AllDigits(stringToArray, 10, 28);
    }

    public boolean AllDigits(char[] finalpart, int a, int b) {
        for (int i = a; i < b; i++) {
            if (!Character.isDigit(finalpart[i]))
                return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return personalID.hashCode();
    }

    @Override
    public String toString() {
        return "HealthCardID{" + "personal code='" + personalID + '\'' + '}';
    }
}
