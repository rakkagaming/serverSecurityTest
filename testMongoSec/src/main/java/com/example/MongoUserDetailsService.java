package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Created by Sebastian Börebäck on 2016-04-22.
 */
@Component
public class MongoUserDetailsService implements UserDetailsService {


	@Autowired
	private AccountRepository accountRepository;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountRepository.findByUsername(username);
		if (account != null) {
			return new User(account.getUsername(), account.getPassword(), true, true, true, true,
					AuthorityUtils.createAuthorityList("USER"));
		} else {
			throw new UsernameNotFoundException("could not find the user '"
					+ username + "'");
		}
	}
}
