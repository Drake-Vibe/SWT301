import TrinhGiaBao.example.AccountService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountServiceTest {

    AccountService service = new AccountService();

    @ParameterizedTest
    @CsvFileSource(resources = "/test-data.csv", numLinesToSkip = 1)
    public void testRegisterAccount(String username, String password, String email, boolean expected) {
        boolean result = service.registerAccount(username, password, email);
        assertEquals(expected, result);
    }
}
