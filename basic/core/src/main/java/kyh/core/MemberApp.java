package kyh.core;

import kyh.core.member.Grade;
import kyh.core.member.Member;
import kyh.core.member.MemberService;
import kyh.core.member.MemberServiceImpl;

public class MemberApp {

    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();

        Member member = new Member(1L, "A", Grade.VIP);
        memberService.join(member);

        Member foundMember = memberService.findMember(1L);

        System.out.println("New member : " + member.getName());
        System.out.println("Found member : " + foundMember.getName());
    }
}
