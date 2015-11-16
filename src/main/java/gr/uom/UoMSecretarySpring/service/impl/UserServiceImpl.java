/**
 * 
 */
package gr.uom.UoMSecretarySpring.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gr.uom.UoMSecretarySpring.dao.UserDao;
import gr.uom.UoMSecretarySpring.domain.User;
import gr.uom.UoMSecretarySpring.service.UserService;

/**
 * @author Georgios Digkas <mai153@uom.edu.gr>
 *
 */
@Service
public class UserServiceImpl implements UserService {

	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	@Transactional
	public void insert(User user) {
		userDao.insert(user);
	}

	@Override
	@Transactional
	public void update(User user) {
		userDao.update(user);
	}

	@Override
	@Transactional
	public void delete(User user) {
		userDao.delete(user);
	}

	@Override
	@Transactional
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	@Transactional
	public List<User> findByRole(String role) {
		return userDao.findByRole(role);
	}

	@Override
	@Transactional
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

}
