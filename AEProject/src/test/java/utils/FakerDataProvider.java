package utils;

import net.datafaker.Faker;
import org.testng.annotations.DataProvider;

import java.time.LocalDate;
import java.time.ZoneId;

public class FakerDataProvider {

    private static final Faker faker = new Faker();

    @DataProvider(name = "signupPhase1Data")
    public static Object[][] signupPhase1Data() {
        return new Object[][]{
                {faker.name().fullName(), faker.internet().emailAddress()}
        };
    }

    @DataProvider(name = "passwordData")
    public static Object[][] passwordData() {
        return new Object[][]{
                {faker.internet().password(8, 16)}
        };
    }

    @DataProvider(name = "nameData")
    public static Object[][] nameData() {
        return new Object[][]{
                {faker.name().firstName(), faker.name().lastName()}
        };
    }

    @DataProvider(name = "addressData")
    public static Object[][] addressData() {
        return new Object[][]{
                {
                        faker.company().name(),                      // company
                        faker.address().streetAddress(),             // address1
                        faker.address().secondaryAddress(),          // address2
                        faker.address().country(),                   // country
                        faker.address().state(),                     // state
                        faker.address().zipCode(),                   // zip
                        faker.address().city()                       // city
                }
        };
    }

    @DataProvider(name = "mobileData")
    public static Object[][] mobileData() {
        return new Object[][]{
                {faker.phoneNumber().cellPhone()}
        };
    }

    @DataProvider(name = "fullRegisterData")
    public static Object[][] fullRegisterData() {
        LocalDate birthday = faker.date().birthday(18, 60).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        String day = String.valueOf(birthday.getDayOfMonth());
        String month = birthday.getMonth().name().substring(0, 1).toUpperCase() + birthday.getMonth().name().substring(1).toLowerCase();
        String year = String.valueOf(birthday.getYear());

        return new Object[][]{
                {
                        faker.name().fullName(),
                        faker.internet().emailAddress(),
                        faker.internet().password(8, 16),  // Replaced aquaTeenHungerForce with proper password generation
                        faker.company().name(),
                        faker.address().streetAddress(),
                        faker.address().secondaryAddress(),
                        faker.address().country(),
                        faker.address().state(),
                        faker.address().zipCode(),
                        faker.address().city(),
                        faker.phoneNumber().cellPhone(),
                        day,
                        month,
                        year
                }
        };
    }
}