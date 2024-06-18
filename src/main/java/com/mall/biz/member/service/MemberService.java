package com.mall.biz.member.service;

import com.mall.biz.member.dto.ReqSaveMemberDto;
import com.mall.biz.member.entity.Member;
import com.mall.biz.member.repository.MemberRepository;
import com.mall.common.utils.CommonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class MemberService  {
    private final MemberRepository memberRepository;

    @Transactional
    public void saveMember(ReqSaveMemberDto reqSaveMemberDto) {
        while (true) {
            // 회원 id 생성
            LocalDate now = LocalDate.now();
            // 포맷 정의
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            // 포맷을 적용하여 문자열로 변환
            String formattedDate = now.format(formatter);
            // 회원 아이디 정의
            String id = CommonUtils.getUUID().substring(0, 24) + formattedDate;

            if (memberRepository.countById(id) == 0) {
                reqSaveMemberDto.registMemberId(id);
                break;
            }
        }

        Member member = reqSaveMemberDto.dtoToEntity();
        memberRepository.save(member);
    }
}
