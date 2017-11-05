package java.LAB7.TESTS;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import LAB7.User;
import LAB7.UserDate;
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
        User user = new User( 122, "DD-AB-123", "fdff", null );

        Set<ConstraintViolation<User>> constraintViolations =
                validator.validate( user );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals(
                "may not be null",
                constraintViolations.iterator().next().getMessage()
        );
    }

    @Test
    public void test2() {
        User user = new User( 122, "DD-AB-123", "fdff","dsd@www.com" );

        user.setBirthday(new UserDate(11,11,-1111));
        Set<ConstraintViolation<User>> constraintViolations =
                validator.validate( user );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals(
                "size must be between 0 and 9999",
                constraintViolations.iterator().next().getMessage()
        );
    }


    @Test
    public void test3() {
        User user = new User( 122, "DD-AB-123", "fdff","dsd@www.com"  );

        user.setMoney(-0.9);
        Set<ConstraintViolation<User>> constraintViolations =
                validator.validate( user );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals(
                "must be positive",
                constraintViolations.iterator().next().getMessage()
        );
    }


    @Test
    public void test4() {
        User user = new User( 122, "DD-AB-123", "fdff","dsd@www.com" );

        user.setShopping(null);
        Set<ConstraintViolation<User>> constraintViolations =
                validator.validate( user );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals(
                "may not be null",
                constraintViolations.iterator().next().getMessage()
        );
    }




}