package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor  //final이 있는 필드를 가지고 생성자를 만들어준다. + Autowired
@Transactional
public class MemberService {

    //@Autowired
    private final MemberRepository memberRepository; //compile 시점에 생성자를 만들라고 체크해주기 때문에 final 권장

    /*
    //Setter Injection
    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }*/

    /*
    //Constructor Injection -> 생성자가 1개만 있는경우 자동으로 Autowired가 생성됨 (권장)
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }*/


    /*회원 가입*/

    public Long join(Member member) {
        validateDuplicateMember(member); //회원 중복 회원 검증
        memberRepository.save(member);
        return member.getId(); //값이 있는게 보장됨 영속성에 의해
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
        //Exception


    }


    /*회원 전체 조회*/
    @Transactional(readOnly = true) //조회하는 로직에서 readOnly = true 는 성능을 최적화 시켜 줌
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /*회원 단건 조회*/
    @Transactional(readOnly = true)
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }


}
