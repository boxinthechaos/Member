package com.example.Member.entity;

import com.example.Member.dto.MemberDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Table(name = "member_table")
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String memberEmail;

    @Column
    private String memberPassword;

    @Column
    private String memberName;

    private MemberEntity(Builder builder) {
        this.id = builder.id;
        this.memberEmail = builder.memberEmail;
        this.memberPassword = builder.memberPassword;
        this.memberName = builder.memberName;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String memberEmail;
        private String memberPassword;
        private String memberName;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder memberEmail(String memberEmail) {
            this.memberEmail = memberEmail;
            return this;
        }

        public Builder memberPassword(String memberPassword) {
            this.memberPassword = memberPassword;
            return this;
        }

        public Builder memberName(String memberName) {
            this.memberName = memberName;
            return this;
        }

        public MemberEntity build() {
            return new MemberEntity(this);
        }
    }

    public static MemberEntity toMemberEntity(MemberDto memberDTO) {
        return MemberEntity.builder()
                .id(memberDTO.getId())
                .memberEmail(memberDTO.getMemberEmail())
                .memberName(memberDTO.getMemberName())
                .memberPassword(memberDTO.getMemberPassword())
                .build();
    }
    protected MemberEntity() {
    }
}