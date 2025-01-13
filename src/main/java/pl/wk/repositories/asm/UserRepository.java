package pl.wk.repositories.asm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wk.entities.asm.entity.Account;

import java.util.UUID;


@Repository
public interface UserRepository  extends JpaRepository<Account, UUID> {
    Account findByUsername(String username);
}
