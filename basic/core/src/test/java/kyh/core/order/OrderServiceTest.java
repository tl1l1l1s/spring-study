package kyh.core.order;

import kyh.core.member.Grade;
import kyh.core.member.Member;
import kyh.core.member.MemberService;
import kyh.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    MemberService memberService = new MemberServiceImpl();
    OrderService orderService = new OrderServiceImpl();

    @Test
    void createOrder() {
        Long memberId = 1L;
        Member member = new Member(memberId, "A", Grade.VIP);

        memberService.join(member);
        Order order = orderService.createOrder(memberId, "ItemA", 10000);

        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
