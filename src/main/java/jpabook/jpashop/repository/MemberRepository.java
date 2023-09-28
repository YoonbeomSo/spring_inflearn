package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor //생성자 인젝션을 위해
public class MemberRepository {

    //@PersistenceContext
    //@Autowired //스프링부트+jpa에서는  @PersistenceContext를 @Autowired로 변경할 수 있다.

    @PersistenceContext
    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id); //(type, pk)
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class) //(jpql문, 반환타입)
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
