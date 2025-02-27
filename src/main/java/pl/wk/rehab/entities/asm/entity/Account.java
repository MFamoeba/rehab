package pl.wk.rehab.entities.asm.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.wk.rehab.entities.util.AbstractEntity;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "account")
@SecondaryTable(name = "personal_data", pkJoinColumns = @PrimaryKeyJoinColumn(name = "id"))
public class Account extends AbstractEntity implements UserDetails {

    @Setter(AccessLevel.NONE)
    @Column(unique = true, updatable = false, nullable = false)
    private String username;

    @Column(nullable = false)
    @ToString.Exclude
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @ToString.Exclude
    private List<Role> roles = new ArrayList<>();

    private LocalDateTime lastSuccessfulLogin;

    private LocalDateTime lastFailedLogin;

    @Column(name = "active", nullable = false)
    private Boolean active = true;

    @Column(name = "verified", nullable = false)
    private Boolean verified = false;

    @Column(name = "email", table = "personal_data", unique = true, nullable = false)
    private String email;

    @Column(name = "gender", table = "personal_data")
    private Integer gender;

    @Column(name = "first_name", table = "personal_data")
    private String firstName;

    @Column(name = "last_name", table = "personal_data")
    private String lastName;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> (GrantedAuthority) role::getName)
                .toList();
    }

    @Override
    public boolean isAccountNonExpired() {
        return active;
    }

    @Override
    public boolean isAccountNonLocked() {
        return active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return active;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }



    public Account(String username, String password, String email, Integer gender, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account account)) return false;
        return username != null && username.equals(account.getUsername());
    }

    @Override
    public final int hashCode() {
        if (username != null) return username.hashCode();
        return super.hashCode();
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    public void removeRole(Role role){
        roles.remove(role);
    }

}
