package jpabook.jpashop;

import jpabook.jpashop.domain.*;
import net.bytebuddy.asm.Advice;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            /*Member member = new Member();
            member.setUsername("member1");
            member.setHomeAddress(new Address("homeCity1", "street1", "1"));

            member.getFavoriteFood().add("1");
            member.getFavoriteFood().add("2");
            member.getFavoriteFood().add("3");

            member.getAddressHistory().add(new Address("old1", "oldstreet1", "old1"));
            member.getAddressHistory().add(new Address("old2", "oldstreet2", "old2"));

            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("==========start==========");
            Member findMember = em.find(Member.class, member.getId());

            Address oldAddress = findMember.getHomeAddress();*/
            //findMember.setHomeAddress(new Address("newCity", oldAddress.getStreet(), oldAddress.getZipcode()));

            //findMember.getFavoriteFood().remove("1");
            //findMember.getFavoriteFood().add("food");

//            findMember.getAddressHistory().remove(new Address("old1", "oldstreet1", "old1"));
//            findMember.getAddressHistory().add(new Address("newCity2", "oldstreet1", "old1"));
            Member member = new Member();
            member.setUsername("yerim");
            em.persist(member);

            em.flush();
            em.clear();

            List<Member> result = em.createQuery("select m from Member m", Member.class)
                    .getResultList();

            em.createQuery("select new jpql.MemberDTO(m.username, m.id) from Member as m", MemberDTO.class);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
