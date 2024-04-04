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

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      User user5 = new User("User5", "Lastname5", "user5@mail.ru");
      Car car1 = new Car("model1",1111);
      user5.setCar(car1);

      userService.add(user5);
      User user6 = new User("User6", "Lastname6", "user6@mail.ru");
      Car car2 = new Car("model2",2222);
      user6.setCar(car2);

      userService.add(user6);


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("========================================================================================");
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         Car userCar = user.getCar();
         if (userCar != null) {
            System.out.println("Model = " + userCar.getModel());
            System.out.println("Series = " + userCar.getSeries());
         }
         System.out.println("========================================================================================");
      }

      System.out.println(userService.getUserOutOfCar("model1",1111).toString());

      context.close();
   }
}
