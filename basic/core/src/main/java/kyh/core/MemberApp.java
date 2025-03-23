package kyh.core;

import kyh.core.member.Grade;
import kyh.core.member.Member;
import kyh.core.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {
        // Annotation 기반 Config, AppConfig를 사용하여 annotation 붙은 것들을 관리해줌
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class); // 메서드 명으로 등록되므로 memberService로 꺼낼 것

        Member member = new Member(1L, "A", Grade.VIP);
        memberService.join(member);

        Member foundMember = memberService.findMember(1L);

        System.out.println("New member : " + member.getName());
        System.out.println("Found member : " + foundMember.getName());
    }
}
