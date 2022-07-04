package hello.core.order;

import hello.core.discount.DiscoutnPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;

    public OrderServiceImpl(MemberRepository memberRepository, DiscoutnPolicy discoutnPolicy) {
        this.memberRepository = memberRepository;
        this.discoutnPolicy = discoutnPolicy;
    }

    private final DiscoutnPolicy discoutnPolicy;

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discoutnPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice , discountPrice);
    }
}
