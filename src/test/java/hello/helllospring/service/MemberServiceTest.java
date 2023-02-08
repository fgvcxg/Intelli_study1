package hello.helllospring.service;

import hello.helllospring.domain.Member;
import hello.helllospring.repository.MemberRepository;
import hello.helllospring.repository.MemoryMEmberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMEmberRepository memoryMEmberRepository;

    @BeforeEach
    public void beforeEach(){
        memoryMEmberRepository = new MemoryMEmberRepository();
        memberService = new MemberService(memoryMEmberRepository);
    }

    @AfterEach
    public void afterEach(){
        memoryMEmberRepository.clearStore();

    }

    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("qwe");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    public void dupMem(){
        //given
        Member member1 = new Member();
        member1.setName("qwe");

        Member member2 = new Member();
        member2.setName("qwe");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        //assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원");

        /*try{
            memberService.join(member2);
            fail("예외 발생 해야합니다");
        }catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 타입입니다");
        }*/

        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}