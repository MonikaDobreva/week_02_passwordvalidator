package validator;

//import static java.lang.Character.;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.stream.IntStream;
import static java.lang.Character.isAlphabetic;
import static java.lang.Character.isDigit;

import static java.util.stream.Collectors.joining;
import static validator.Flaw.*;

/**
 * Password validator using lambdas and maps.
 *
 * @author Pieter van den Hombergh {@code p.vandenhombergh@fontys.nl}
 */
public class Validator {

    void validate( String password ) {
        EnumSet<Flaw> flaws = EnumSet.allOf( Flaw.class );

        String totalFlaws = "";
        totalFlaws += noUpperCaseLetterCheck(password);
        totalFlaws += noLowerCaseLetterCheck(password);
        totalFlaws += noDigitCheck(password);
        totalFlaws += noSpecialCharCheck(password);
        totalFlaws += tooShortCheck(password);
        //add rest of the cases

        String exceptionMessage = "";
        if (totalFlaws.contains("U")) {
            exceptionMessage += NOUPPER.getDescription();
        }
        if (totalFlaws.contains("l")) {
            exceptionMessage += NOLOWER.getDescription();
        }
        if (totalFlaws.contains("8")) {
            exceptionMessage += NODIGIT.getDescription();
        }
        if (totalFlaws.contains("#")) {
            exceptionMessage += NOSPECIAL.getDescription();
        }
        if (totalFlaws.contains("s")) {
            exceptionMessage += TOO_SHORT.getDescription();
        }

        if (exceptionMessage.length() > 0) {
            throw new InvalidPasswordException(exceptionMessage);
        }
    }

    private String tooShortCheck(String password) {
        if (password.length() < 10) {
            return "s";
        }

        return "";
    }

    private String noSpecialCharCheck(String password) {
        String totalFlawsFound = "";

        ArrayList<Character> specialChars = new ArrayList<>();

        for (int j : SpecialChars.getSpecialChars()) {
            specialChars.add((char) j);
        }

        for (Character specialChar : specialChars) {
            if (password.indexOf(specialChar) != -1) {
                return "";
            }
        }

        totalFlawsFound += '#';
        return totalFlawsFound;
    }

    private String noDigitCheck(String password) {
        String totalFlawsFound = "";
        char[] passwordCharacters = password.toCharArray();

        for (int i = 0; i < password.length(); i++) {
            if (Character.isDigit(passwordCharacters[i])) {
                return "";
            }
        }

        totalFlawsFound += '8';
        return totalFlawsFound;
    }

    private String noLowerCaseLetterCheck(String password) {
        String totalFlawsFound = "";
        char[] passwordCharacters = password.toCharArray();

        for (int i = 0; i < password.length(); i++) {
            if (Character.isLowerCase(passwordCharacters[i])) {
                return "";
            }
        }

        totalFlawsFound += 'l';
        return totalFlawsFound;
    }

    private String noUpperCaseLetterCheck(String password) {
        String totalFlawsFound = "";
        char[] passwordCharacters = password.toCharArray();

        for (int i = 0; i < password.length(); i++) {
            if (Character.isUpperCase(passwordCharacters[i])) {
                return "";
            }
        }

        totalFlawsFound += 'U';
        return totalFlawsFound;
    }

}
