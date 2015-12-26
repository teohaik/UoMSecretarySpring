/**
 * 
 */
package gr.uom.uomsecretaryspring.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import gr.uom.uomsecretaryspring.dao.UserDetailsDao;
import gr.uom.uomsecretaryspring.domain.UserDetails;

/**
 * @author Georgios Digkas <mai153@uom.edu.gr>
 *
 */
@Repository
public class UserDetailsDaoImpl implements UserDetailsDao {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void insert(UserDetails userDetails) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(userDetails);
	}

	@Override
	public void update(UserDetails userDetails) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(userDetails);
	}

	@Override
	public void delete(UserDetails userDetails) {
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(userDetails);
	}

	@Override
	public List<UserDetails> findAll() {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("UserDetails.findAll");
		return (List<UserDetails>) query.list();
	}

	@Override
	public List<UserDetails> findByRole(String role) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("UserDetails.findByRole");
		query.setString("role", role);
		return (List<UserDetails>) query.list();
	}

	@Override
	public UserDetails findByUsername(String username) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("UserDetails.findByUsername");
		query.setString("username", username);
		return  (UserDetails) query.uniqueResult();
	}

}
