package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      Car car1 = new Car("Model1", 100);
      Car car2 = new Car("Model2", 200);
      Car car3 = new Car("Model3", 300);
      Car car4 = new Car("Model4", 400);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru", car1));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", car2));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", car3));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", car4));

      List<User> users = userService.listUsers();
      if (users.size() != 0) {
         System.out.println("\n---------Таблица Users-----------");
      }

      for (User user : users) {
         System.out.println(user);
      }
      System.out.println();

      // задаем параметры машины для поиска user c такой машиной
      String model = "Model1";
      int series = 100;

      User user = userService.getByCar(model, series);

      if (user != null) {
         System.out.printf("\n User с моделью машины %s и серией %d : \n", model, series);
         System.out.println(user);
      } else {
         System.out.printf("\n User с моделью машины %s и серией %d не найден! \n", model, series);
      }

      context.close();
   }
}
