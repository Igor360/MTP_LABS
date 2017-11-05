package java.LAB7.TESTS;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import LAB7.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class UserTest {

    private static Validator validator;

    @Before
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void manufacturerIsNull() {
        User user = new User( 122, "DD-AB-123", "fdff","dsd" );

        Set<ConstraintViolation<User>> constraintViolations =
                validator.validate( user );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals(
                "may not be null",
                constraintViolations.iterator().next().getMessage()
        );
    }


}
