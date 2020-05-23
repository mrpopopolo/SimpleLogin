package fr.pops.spring.login.business.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.pops.spring.login.business.entity.Token;
import fr.pops.spring.login.persistence.TokenRepository;

@Service
public class TokenService {

	@Autowired
	private TokenRepository tokenRep;
	
	public String createToken(String userEmail) {
		Token token = new Token();
		String uniqueID = UUID.randomUUID().toString();
		System.out.println(uniqueID);
		token.setName(uniqueID);
		token.setUserEmail(userEmail);
		token.setDate(java.time.LocalDateTime.now());
		this.tokenRep.save(token);
		return uniqueID;
	}
	
	public Token readToken(Long id) {
		return this.tokenRep.getOne(id);
	}
	
	public void deleteToken(Token tok) {
		this.tokenRep.delete(tok);
	}
	
	public void cleanTokenRepo() {
		
	}
	
	public Token findByName(String name) {
		return this.tokenRep.findOneByName(name);
	}
}
