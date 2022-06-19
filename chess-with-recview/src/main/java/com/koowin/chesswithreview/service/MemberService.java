package com.koowin.chesswithreview.service;

import com.koowin.chesswithreview.dto.request.MemberRegisterDto;
import com.koowin.chesswithreview.entity.Member;
import com.koowin.chesswithreview.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void join(MemberRegisterDto memberRegisterDto) {
        Member member = Member.builder()
                .name(memberRegisterDto.getName())
                .encryptedPassword(passwordEncoder.encode(memberRegisterDto.getPassword()))
                .build();

        memberRepository.save(member);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> memberOptional = memberRepository.findByName(username);
        if(memberOptional.isEmpty()){
            throw new UsernameNotFoundException(username);
        }

        Member member = memberOptional.get();
        return User.builder()
                .username(member.getName())
                .password(member.getEncryptedPassword())
                .build();
    }
}
