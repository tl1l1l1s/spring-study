package kyh.core;

import kyh.core.discount.DiscountPolicy;
import kyh.core.discount.FixDiscountPolicy;
import kyh.core.discount.RateDiscountPolicy;
import kyh.core.member.MemberRepository;
import kyh.core.member.MemberService;
import kyh.core.member.MemberServiceImpl;
import kyh.core.member.MemoryMemberRepository;
import kyh.core.order.OrderService;
import kyh.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 애플리케이션에 대한 환경 구성 설정을 모두 이 파일에서 진행 == 설정 정보, 구성 정보
@Configuration
public class AppConfig {

    // @Bean을 붙이면 Spring Container에 등록
    @Bean
    public MemberService memberService () {
        return new MemberServiceImpl(memberRepository());
        // key는 함수명, memberService, value는 아래 return ... 부분 객체 인스턴스로 등록
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService () {
        // 어디선가 OrderService 호출 시 OrderServiceImpl 반환
        return new OrderServiceImpl(
                memberRepository(),
                discountPolicy()
        );
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}
