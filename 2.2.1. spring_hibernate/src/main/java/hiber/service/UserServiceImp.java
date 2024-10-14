package hiber.service;

import hiber.dao.UserDao;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

   @Autowired
   private UserDao userDao;

   @Transactional
   @Override
   public void add(User user) {
      userDao.add(user);
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> listUsers() {
      List<User> users = userDao.listUsers();
      if (users.size() == 0) {
         System.out.println("\n Таблица не заполнена!");
      }
      return users;
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> getByCarData(String model, int series) {
      List<User> users = userDao.getByCarData(model, series);
      if (users.size() == 0) {
         System.out.println("\n В таблице нет пользователей (user) с таким типом машины (model и series)!");
      }
      return users;
   }

}
