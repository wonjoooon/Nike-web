package com.wonjun.service;

import com.wonjun.model.entity.Member;
import com.wonjun.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Member join(String username, String password, String email, String address, String name){
        Member member = new Member();
        member.setUsername(username);
        member.setPassword(passwordEncoder.encode(password));
        member.setM_address(address);
        member.setM_name(name);
        member.setM_email(email);

        memberRepository.save(member);
        return member;
    }
}
