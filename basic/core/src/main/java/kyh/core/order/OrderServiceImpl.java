package kyh.core.order;

import kyh.core.discount.DiscountPolicy;
import kyh.core.discount.FixDiscountPolicy;
import kyh.core.discount.RateDiscountPolicy;
import kyh.core.member.Member;
import kyh.core.member.MemberRepository;
import kyh.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    private DiscountPolicy discountPolicy; // -> 💥 NullPointException 발생
                                        // 누군가 DiscountPolicy의 구현 객체를 대신 생성, 주입해주어야 함!

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
            // 할인 관련 변경이 필요하면 discountPolicy 부분을 수정 -> 단일 책임 원칙 O

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
