package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.ArrayList;
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
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }
   @Override
   @SuppressWarnings("unchecked")
   public List<User> getByCarData(String model, int series) {
      String hql1 = String.format("from Car where model='%s' and series=%d", model, series);
      TypedQuery<Car> query1 = sessionFactory.getCurrentSession().createQuery(hql1);
      List<Car> cars = query1.getResultList();

      if (cars == null) {
         System.out.println("User с таким car не найдено!");
      }

      String hql2 = "from User where car IN (:cars_list)";
      TypedQuery<User> query2 = sessionFactory.getCurrentSession().createQuery(hql2).setParameterList("cars_list", cars);
      List<User> users = query2.getResultList();

      return users;
   }
}
