package validator;

import java.util.Arrays;
import java.util.EnumSet;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Test validator with Parameterized test.
 *
 * @author Pieter van den Hombergh {@code p.vandenhombergh@fontys.nl}
 */
public class ValidatorTest {
    private Validator validator;

    @BeforeEach
    void setUp() {
        this.validator = new Validator();
    }

    @ParameterizedTest
    @CsvSource({
            "qwertyu1?, No UPPER case letter",
            "QWERTY12?, No lower case letter",
            "QWERTu123, No special character",
            "qwerT1?, Too short",
            "QWERTYUi?, No digit"})
    void invalidPassword(String password, String expected) {
        SoftAssertions.assertSoftly(softly -> {
            ThrowingCallable code = () -> {
                this.validator.validate(password);
            };
            assertThatCode(code)
                    .as("No UPPER case letter")
                    .isExactlyInstanceOf(InvalidPasswordException.class)
                    .hasMessageContaining(expected);
        });
    }

    @ParameterizedTest
    @CsvSource({
            "qwerytySD12?!",
            "ahdknc sjskdSF76?!"
    })
    void validPassword(String password) {
        assertDoesNotThrow(() -> validator.validate(password));
    }
}
