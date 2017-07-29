package dao;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class UserDAO implements IBaseDAO {
	// 使用工厂实例
	SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();

	// 不推荐用的是： new Configuration().configure().buildSessionFactory();
	public Session getSession() {
		return sessionFactory.openSession();
	}

	@Override
	public void add(Object obj) {
		Session session = null;
		try {
			session = getSession();
			session.beginTransaction();
			session.save(obj);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@Override
	public void delect(Object obj) {
		Session session = null;
		try {
			session = getSession();
			session.beginTransaction();
			session.delete(obj);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@Override
	public void update(Object obj) {
		Session session = null;
		try {
			session = getSession();
			session.beginTransaction();
			session.update(obj);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@Override
	public Object findById(String cls, Serializable key) {
		Session session = getSession();
		Object instance = session.get(cls, key);
		session.close();
		return instance;
	}
}