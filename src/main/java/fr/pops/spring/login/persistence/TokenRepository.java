package fr.pops.spring.login.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.pops.spring.login.business.entity.Token;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

	Token findOneByName(String name);
	
}
