package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService service = ac.getBean(StatefulService.class);
        StatefulService service2 = ac.getBean(StatefulService.class);

        //ThreadA: A사용자가 10000원 주문
        int priceA = service.order("userA", 10000);
        //ThreadB: B사용자가 20000원 주문
        int priceB = service2.order("userB", 20000);

        //ThreadA: 사용자A가 주문 금액 조회
        System.out.println("priceA = " + priceA);

        Assertions.assertThat(priceA == 10000).isTrue();

    }

    static class TestConfig{
        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }
}