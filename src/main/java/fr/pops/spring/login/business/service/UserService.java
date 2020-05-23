package fr.pops.spring.login.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import fr.pops.spring.login.business.entity.Role;
import fr.pops.spring.login.business.entity.User;
import fr.pops.spring.login.persistence.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRep;
	
	@Autowired
	private TokenService tokenServ;
	
	@Autowired
	private MailService mailServ;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public void createUser(User user) {
		if(this.userRep.findOneByEmail(user.getEmail()) == null) {
			user.setPassword(this.encoder.encode(user.getPassword()));
			Role role = new Role(1);
			user.setRole(role);
			user.setEnabled(false);
			String tok = tokenServ.createToken(user.getEmail());
			this.mailServ.sendMail(user.getEmail(), "Welcome", 
					"http://localhost:8080/valid/"+tok);
			this.userRep.save(user);
		}
	}
	
	public User readUser(Integer id) {
		return this.userRep.getOne(id);
	}
	
	public void updateUser(User user) {
		this.userRep.save(user);
	}
	
	public void deleteUser(Integer id) {
		this.userRep.deleteById(id);
	}
	
	public void validateUser(String token) {
		User user = this.userRep.findOneByEmail(this.tokenServ.findByName(token).getUserEmail());
		user.setEnabled(true);
		this.tokenServ.deleteToken(this.tokenServ.findByName(token));
		this.userRep.save(user);
	}
	
	public boolean login(String email, String password) {
		User user = this.loadUserByUsername(email);
		if(user != null && this.encoder.matches(password, user.getPassword())) {
			return true;
		}
		else {return false;}
	}

	@Override
	public User loadUserByUsername(String email) throws UsernameNotFoundException {
		return this.userRep.findOneByEmail(email);
	}
}
