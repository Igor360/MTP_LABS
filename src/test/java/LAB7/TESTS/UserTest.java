package LAB7.TESTS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void manufacturerIsNull() {
        User user = new User( 122, "DD-AB-123", "fffffdff", null );
        user.setMoney(5.5);
        user.setShopping(new ArrayList<>());
        user.setBirthday(new UserDate(11,11,2017));
        user.setInSystem(true);
        user.setNumVisit(22);
        user.setThing(new HashMap<>());
        Set<ConstraintViolation<User>> constraintViolations =
                validator.validate( user );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals(
                "must not be null",
                constraintViolations.iterator().next().getMessage()
        );
    }

    @Test
    public void test2() {
        User user = new User( 122, "DD-AB-123", "fdffffff","dsd@www.com" );

        user.setMoney(5.5);
        user.setShopping(new ArrayList<>());
        user.setInSystem(true);
        user.setNumVisit(22);
        user.setThing(new HashMap<>());
        user.setBirthday(new UserDate(11,11,-1111));
        Set<ConstraintViolation<User>> constraintViolations =
                validator.validate( user );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals(
                "must be greater than or equal to 0",
                constraintViolations.iterator().next().getMessage()
        );
    }


    @Test
    public void test3() {
        User user = new User( 122, "DD-AB-123", "fdfffffff","dsd@www.com"  );
        user.setShopping(new ArrayList<>());
        user.setBirthday(new UserDate(11,11,2017));
        user.setInSystem(true);
        user.setNumVisit(22);
        user.setThing(new HashMap<>());
        user.setMoney(-0.9);
        Set<ConstraintViolation<User>> constraintViolations =
                validator.validate( user );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals(
                "must be greater than 0",
                constraintViolations.iterator().next().getMessage()
        );
    }


    @Test
    public void test4() {
        User user = new User( 122, "DD-AB-123", "fdffffff","dsd@www.com" );

        Object test = null;
        user.setShopping(test);
        user.setMoney(5.5);
        user.setBirthday(new UserDate(11,11,2017));
        user.setInSystem(true);
        user.setNumVisit(22);
        user.setThing(new HashMap<>());

        Set<ConstraintViolation<User>> constraintViolations =
                validator.validate( user );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals(
                "must not be null",
                constraintViolations.iterator().next().getMessage()
        );
    }




}
