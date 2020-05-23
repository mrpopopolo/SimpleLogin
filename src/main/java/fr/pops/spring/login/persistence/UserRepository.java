package fr.pops.spring.login.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.pops.spring.login.business.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	User findOneByEmail(String email);
	
}
