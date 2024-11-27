package com.example.Member.dto;

import com.example.Member.entity.MemberEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MemberDto {
    private Long id;
    private String memberEmail;
    private String memberPassword;
    private String memberName;

    public static MemberDto toMemberDto(MemberEntity memberEntity){
        MemberDto memberDto = new MemberDto();
        memberDto.setId(memberEntity.getId()); // 수정
        memberDto.setMemberEmail(memberEntity.getMemberEmail()); // 수정
        memberDto.setMemberName(memberEntity.getMemberName()); // 수정
        memberDto.setMemberPassword(memberEntity.getMemberPassword()); // 수정

        return memberDto;
    }
}
