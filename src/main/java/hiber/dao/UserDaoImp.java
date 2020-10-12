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
            Query query = sessionFactory.getCurrentSession().createQuery("from User u where u.car.model = :mod");//("FROM User WHERE LEFT JOIN FETCH car.model = :mod");
            query.setParameter("mod", model);
            user = (User) query.getSingleResult();

        } catch (Exception e) {
            e.printStackTrace();
          //  System.out.println("Error " + e.getMessage());
        }
        return user;
    }

}
