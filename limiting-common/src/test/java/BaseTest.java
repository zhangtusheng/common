import com.zts.limit.annotation.CounterRateLimiter;
import org.junit.jupiter.api.Test;

/**
 * @Author zhangtusheng
 * @Date 2024 07 06 16 24
 * @describe：
 **/
public class BaseTest {

    @Test
    public void test() {

    }

    @CounterRateLimiter(limit = 10, windowSize = 3000)
    public void limitTest() throws InterruptedException {
        System.out.println("aa");
        Thread.sleep(1000);
    }
}
