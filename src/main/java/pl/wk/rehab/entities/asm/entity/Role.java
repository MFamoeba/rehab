package pl.wk.rehab.entities.asm.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.wk.rehab.entities.util.AbstractEntity;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Role extends AbstractEntity {

    @Column(nullable = false, unique = true, updatable = false, length = 32)
    @NotBlank
    @Size(max = 32)
    private String name;


    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role role)) return false;
        return name != null && name.equals(role.getName());
    }

    @Override
    public final int hashCode() {
        if (name != null) return name.hashCode();
        return super.hashCode();
    }
}
