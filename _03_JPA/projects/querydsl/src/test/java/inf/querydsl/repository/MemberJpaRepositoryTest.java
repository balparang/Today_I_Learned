package inf.querydsl.repository;

import inf.querydsl.dto.MemberSearchCondition;
import inf.querydsl.dto.MemberTeamDto;
import inf.querydsl.entity.Member;
import inf.querydsl.entity.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberJpaRepositoryTest {

	@Autowired
	EntityManager em;

	@Autowired
	MemberJpaRepository memberJpaRepository;

	@Test
	void basicTest() {
		Member member = new Member("member1", 10);
		memberJpaRepository.save(member);

		Member findMember = memberJpaRepository.findById(member.getId()).get();
		assertThat(findMember).isEqualTo(member);

		List<Member> result1 = memberJpaRepository.findAllQuerydsl();
		assertThat(result1).containsExactly(member);

		List<Member> result2 = memberJpaRepository.findByUsernameQuerydsl(member.getUsername());
		assertThat(result2).containsExactly(member);
	}

	@Test
	@DisplayName("Search By Where Parameter")
	void searchTest() {
		// given
		Team teamA = new Team("teamA");
		Team teamB = new Team("teamB");
		em.persist(teamA);
		em.persist(teamB);

		Member member1 = new Member("member1", 10, teamA);
		Member member2 = new Member("member2", 20, teamA);
		Member member3 = new Member("member3", 30, teamB);
		Member member4 = new Member("member4", 40, teamB);
		em.persist(member1);
		em.persist(member2);
		em.persist(member3);
		em.persist(member4);

		MemberSearchCondition condition = new MemberSearchCondition();
		condition.setAgeGoe(35);
		condition.setAgeLoe(40);
		condition.setTeamName("teamB");

		// when
		List<MemberTeamDto> result = memberJpaRepository.search(condition);

		// then
		assertThat(result)
				.extracting("username")
				.containsExactly("member4");
	}

	@Test
	@DisplayName("Search Member By Where Multiple Parameter")
	void searchMember() {
		// given
		Team teamA = new Team("teamA");
		Team teamB = new Team("teamB");
		em.persist(teamA);
		em.persist(teamB);

		Member member1 = new Member("member1", 10, teamA);
		Member member2 = new Member("member2", 20, teamA);
		Member member3 = new Member("member3", 30, teamB);
		Member member4 = new Member("member4", 40, teamB);
		em.persist(member1);
		em.persist(member2);
		em.persist(member3);
		em.persist(member4);

		MemberSearchCondition condition = new MemberSearchCondition();
//		condition.setAgeGoe(35);
//		condition.setAgeLoe(40);
//		condition.setTeamName("teamB");

		// when
		List<Member> result = memberJpaRepository.searchMember(condition);

		// then
		assertThat(result).hasSize(1)
				.extracting("username")
				.containsExactly("member4");
	}

}