package kyh.core;

import kyh.core.member.Grade;
import kyh.core.member.Member;
import kyh.core.member.MemberService;
import kyh.core.order.Order;
import kyh.core.order.OrderService;

public class OrderApp {

    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();

        Long memberId = 1L;
        Member member = new Member(memberId, "A", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(1L, "itemA", 10000);
        System.out.println("Order : " + order.toString());
        System.out.println("Order : " + order.calculatePrice());
    }
}
