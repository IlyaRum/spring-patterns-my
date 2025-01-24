package my.example.springpatternsmy.decorator.repository;

import my.example.springpatternsmy.decorator.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
