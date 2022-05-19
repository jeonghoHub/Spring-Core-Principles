package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI컨테이너")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();

        MemberService memberService = appConfig.memberService();

        MemberService memberService1 = appConfig.memberService();

        assertThat(memberService).isNotSameAs(memberService1);

    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest() {
        SingletonService instance = SingletonService.getInstance();
        SingletonService instance2 = SingletonService.getInstance();

        System.out.println(instance);
        System.out.println(instance2);

        assertThat(instance).isSameAs(instance2);
        // same == 참조비교
        // equal == 값 비교
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {
//        AppConfig appConfig = new AppConfig();
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService = annotationConfigApplicationContext.getBean("memberService", MemberService.class);

        MemberService memberService1 = annotationConfigApplicationContext.getBean("memberService", MemberService.class);

        assertThat(memberService).isSameAs(memberService1);
    }
}
