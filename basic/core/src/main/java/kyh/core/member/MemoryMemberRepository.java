package kyh.core.member;

import java.util.HashMap;
import java.util.Map;

// 데이터베이스를 어떤 툴을 사용할지 확정 되지 않은 기획을 받아본 상황
    // 개발 하는 동안 메모리를 사용하여 개발하기로 결정
    // MemberRepository를 implements 하여 구현
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    // 동시성 이슈가 발생할 수 있으므로 ConcurrentHashMap을 사용하는 게 나음

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
