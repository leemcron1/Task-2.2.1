package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }


    @Override
    public User getUserCarModel(String model, int series) {
        User user = new User();
        try {
            Query<User> query = sessionFactory.getCurrentSession().createQuery("from User  where car.model = :mod and car.series = :ser");
            query.setParameter("mod", model);
            query.setParameter("ser", series);
            user = query.setMaxResults(1).uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

}
