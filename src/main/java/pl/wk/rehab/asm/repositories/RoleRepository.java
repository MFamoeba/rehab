package pl.wk.rehab.asm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wk.rehab.entities.asm.entity.Role;

import java.util.UUID;

@Repository
public interface RoleRepository  extends JpaRepository<Role, UUID> {
    Role findByName(String name);
}