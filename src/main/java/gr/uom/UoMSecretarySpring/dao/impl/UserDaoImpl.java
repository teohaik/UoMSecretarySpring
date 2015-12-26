/**
 * 
 */
package gr.uom.uomsecretaryspring.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import gr.uom.uomsecretaryspring.dao.UserDao;
import gr.uom.uomsecretaryspring.domain.User;

/**
 * @author Georgios Digkas <mai153@uom.edu.gr>
 *
 */
@Repository
public class UserDaoImpl implements UserDao {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void insert(User user) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(user);
	}

	@Override
	public void update(User user) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(user);
	}

	@Override
	public void delete(User user) {
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(user);
	}

	@Override
	public List<User> findAll() {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("User.findAll");
		return (List<User>) query.list();
	}

	@Override
	public List<User> findByRole(String role) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("User.findByRole");
		query.setString("role", role);
		return (List<User>) query.list();
	}

	@Override
	public User findByUsername(String username) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("User.findByUsername");
		query.setString("username", username);
		return (User) query.uniqueResult();
	}

}
