import org.junit.jupiter.api.*;

public class JUnitTest {

    @DisplayName("1+2=3")
    @Test
    public  void junitTest() {
        int a = 1;
        int b = 2;
        int sum = 3;

        Assertions.assertEquals(sum, a+b);
    }
    @DisplayName("1+3=4")
    @Test
    public  void junitFailTest() {
        int a = 1;
        int b = 3;
        int sum = 4;

        System.out.println("1+3 = 4");
        Assertions.assertEquals(sum, a+b);
    }

    @BeforeEach
    public void prepare() {
        System.out.println("test ready");
    }

    @AfterEach
    public void clean() {
        System.out.println("after test ");
    }

    @BeforeAll
    public static void prepareAll() {
        System.out.println("cancel ready");
    }

    @AfterAll
    public static void cleanAll() {
        System.out.println("finally result");
    }
}
