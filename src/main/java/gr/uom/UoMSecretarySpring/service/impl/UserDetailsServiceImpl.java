/**
 * 
 */
package gr.uom.UoMSecretarySpring.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gr.uom.UoMSecretarySpring.dao.UserDetailsDao;
import gr.uom.UoMSecretarySpring.domain.UserDetails;
import gr.uom.UoMSecretarySpring.service.UserDetailsService;

/**
 * @author Georgios Digkas <mai153@uom.edu.gr>
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private UserDetailsDao userDetailsDao;

	public void setUserDetailsDao(UserDetailsDao userDetailsDao) {
		this.userDetailsDao = userDetailsDao;
	}

	@Override
	@Transactional
	public void insert(UserDetails userDetails) {
		userDetailsDao.insert(userDetails);
	}

	@Override
	@Transactional
	public void update(UserDetails userDetails) {
		userDetailsDao.update(userDetails);
	}

	@Override
	@Transactional
	public void delete(UserDetails userDetails) {
		userDetailsDao.delete(userDetails);
	}

	@Override
	@Transactional
	public List<UserDetails> findAll() {
		return userDetailsDao.findAll();
	}

	@Override
	@Transactional
	public List<UserDetails> findByRole(String role) {
		return userDetailsDao.findByRole(role);
	}

	@Override
	@Transactional
	public UserDetails findByUsername(String username) {
		return userDetailsDao.findByUsername(username);
	}

}
