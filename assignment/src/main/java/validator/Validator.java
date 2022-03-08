package validator;

//import static java.lang.Character.;
import java.util.EnumSet;
import static java.util.stream.Collectors.joining;
import static validator.Flaw.NODIGIT;
import static validator.Flaw.NOLOWER;
import static validator.Flaw.NOUPPER;
import static validator.Flaw.TOO_SHORT;

/**
 * Password validator using lambdas and maps.
 *
 * @author Pieter van den Hombergh {@code p.vandenhombergh@fontys.nl}
 */
public class Validator {

    void validate( String password ) {
        EnumSet<Flaw> flaws = EnumSet.allOf( Flaw.class );
        
    }

}
