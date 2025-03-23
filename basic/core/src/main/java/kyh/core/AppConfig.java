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

// 애플리케이션에 대한 환경 구성 설정을 모두 이 파일에서 진행
    // 설정 정보(== 구성 정보)에는 역할, 역할에 따른 구현을 한 눈에 볼 수 있어야 함
public class AppConfig {

    // 생성자를 통해 인스턴스 생성된 것을 넣어줌 = 생성자 주입
    public MemberService memberService () {
        return new MemberServiceImpl(getMemberRepository());
    }

    // 메서드명만 봐도 역할 확인 가능
    private MemberRepository getMemberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService () {
        // 어디선가 OrderService 호출 시 OrderServiceImpl 반환
        return new OrderServiceImpl(
                getMemberRepository(),
                getDiscountPolicy()
        );
    }

    private DiscountPolicy getDiscountPolicy() {
        return new RateDiscountPolicy();
    }
}
