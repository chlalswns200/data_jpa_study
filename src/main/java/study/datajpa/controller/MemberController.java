package study.datajpa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;
import study.datajpa.repository.MemberRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    // 도메인 클래스 컨버터 적용전
//    @GetMapping("/members/{id}")
//    public String findMembers(@PathVariable("id") Long id) {
//        Member member = memberRepository.findById(id).orElseThrow(
//                () -> new RuntimeException("NOT FOUND")
//        );
//        return member.getUsername();
//    }

    // 도메인 클래스 컨버터 적용 후
    // 조회용으로만 사용해야함
    @GetMapping("/members/{id}")
    public String findMembers(@PathVariable("id") Member member) {
        return member.getUsername();
    }

    @GetMapping("/members")
    public List<MemberDto> members(Pageable pageable) {
        return memberRepository.findAll(pageable).stream()
                .map(MemberDto::new)
                .collect(Collectors.toList());
    }
}
