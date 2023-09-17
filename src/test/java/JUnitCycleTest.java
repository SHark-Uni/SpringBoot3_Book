import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class JUnitCycleTest {
    //전체 테스트를 시작하기 전에 실행한다. static으로 만들어야함.
    @BeforeAll
    static void beforeAll(){
        System.out.println("BeforeAll is called");
    }

    @BeforeEach
    public void beforeEach(){
        System.out.println("BeforeEach is called");
    }

    @DisplayName("첫번째 테스트 입니다.")
    @Test
    public void Test1(){
        System.out.println("첫번째 테스트 입니다.");
    }

    @DisplayName("두번째 테스트 입니다.")
    @Test
    public void Test2(){
        System.out.println("두번째 테스트 입니다.");
    }

    @DisplayName("세번째 테스트 입니다.")
    @Test
    public void Test3(){
        System.out.println("세번째 테스트 입니다.");
    }

}
