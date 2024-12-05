package com.mypage.model.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collections;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "User")
public class User {

    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = " user_nickname")
    private String userNickname;

    @Column(name = "user_role")
    private UserRole userRole;

    public enum UserRole implements GrantedAuthority {
        ADMIN,
        GUEST;

        @Override
        public String getAuthority() {
            return "ROLE_" + this.name();
        }
    }
    public List<GrantedAuthority> getAuthorities() {
        return Collections.singletonList(userRole);
    }
}
