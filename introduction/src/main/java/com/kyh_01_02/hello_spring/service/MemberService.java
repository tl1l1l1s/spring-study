package com.kyh_01_02.hello_spring.service;

import com.kyh_01_02.hello_spring.domain.Member;
import com.kyh_01_02.hello_spring.repository.MemberRepository;
import com.kyh_01_02.hello_spring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

//@Service
@Transactional
public class MemberService {

    // 서비스 클래스는 비즈니스 처리 롤, 비즈니스 언어와 비슷하게 용어 선택
    private final MemberRepository memberRepository;

//    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
    * 회원가입 
    */
    public Long join(Member member) {
        // 같은 이름의 중복 회원 금지
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        // cmd+option+v = return문을 알아서 만들어줌
        memberRepository.findByName(member.getName())
            .ifPresent(m -> { // null이 아니라 값이 존재할 때, Optional 타입일 때만 가능
                                    // get 대신 orElseGet 사용 (있으면 꺼내고 없으면 ~)
                throw new IllegalStateException("이미 존재하는 회원입니다");
            });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
