package com.koowin.multiplayer.service;

import com.koowin.multiplayer.dto.request.MemberRegisterDto;
import com.koowin.multiplayer.entity.Member;
import com.koowin.multiplayer.repository.MemberRepository;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

  private final MemberRepository memberRepository;
  private final PasswordEncoder passwordEncoder;
  private final HttpSession session;

  public void join(MemberRegisterDto registerDto) {
    memberRepository.save(
        Member.builder()
            .name(registerDto.getName())
            .encryptedPassword(passwordEncoder.encode(registerDto.getPassword()))
            .build()
    );
  }


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<Member> memberOptional = memberRepository.findByName(username);
    if (memberOptional.isEmpty()) {
      throw new UsernameNotFoundException(username);
    }

    Member member = memberOptional.get();
    session.setAttribute("uid", member.getId());
    return User.builder()
        .username(member.getName())
        .password(member.getEncryptedPassword())
        .roles("ROLE")
        .build();
  }
}
