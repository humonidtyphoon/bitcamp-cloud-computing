package bitcamp.assignment.service;

import bitcamp.assignment.domain.Member;

public interface memberService {
    int add(Member member);

    Member getMember(String email, String password);

}
