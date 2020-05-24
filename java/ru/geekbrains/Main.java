package ru.geekbrains;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import ru.geekbrains.persist.Client;
import ru.geekbrains.persist.Contact;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class Main {

    public static  EntityManagerFactory emFactory = new Configuration()
            .configure("hibernate.cfg.xml")
            .buildSessionFactory();

    public static void main(String[] args) {

        //insertProduct();
        //insertClient();
        infoProductsList(3L);
        infoClientsList(5L);
        deleteProductById(5L);

    }

    // добавим информацию о клиентах
    private static void insertClient() {
        EntityManager entityManager = emFactory.createEntityManager();

        Client client1 = new Client(null, "Клиент1");
        Client client2 = new Client(null, "Клиент2");
        Client client3 = new Client(null, "Клиент3");

        entityManager.getTransaction().begin();

        entityManager.persist(client1);
        entityManager.persist(client2);
        entityManager.persist(client3);

        entityManager.getTransaction().commit();
    }

    // добавим инфо по продуктам
    private static void insertProduct() {
        EntityManager entityManager = emFactory.createEntityManager();

        Product product1 = new Product(null, "Milk", 55);
        Product product2 = new Product(null, "Bread", 23);
        Product product3 = new Product(null, "Lemon", 44);

        entityManager.getTransaction().begin();

        entityManager.persist(product1);
        entityManager.persist(product2);
        entityManager.persist(product3);

        entityManager.getTransaction().commit();
    }

    // Удаление продукта по id
    private static Product deleteProductById(Long id){
        Product product = null;
        EntityManager entityManager = emFactory.createEntityManager();
        entityManager.getTransaction().begin();
        product = entityManager.find(Product.class, id);
        try {
            entityManager.remove(product);
        } catch (IllegalArgumentException e){
            System.out.println("No such element in database!");
        }
        entityManager.getTransaction().commit();
        System.out.println("Deleted product: " + product);
        return product;
    }

    // Информацию о всех покупках клиента
    private static void infoProductsList(Long clientId){
        EntityManager entityManager = emFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Client client = entityManager.find(Client.class, clientId);
        System.out.println(client);
        System.out.println("Products: ");
        for (Product p : client.getProducts()) {
            System.out.println(p.getName());
        }
        entityManager.getTransaction().commit();
    }

    // Информацию о всех пользователях, которые купили товар
    private static void infoClientsList(Long productId){
        EntityManager entityManager = emFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Product product = entityManager.find(Product.class, productId);
        System.out.println(product);
        System.out.println("Clients: ");
        for (Client c : product.getClients()) {
            System.out.println(c.getName());
        }

    }


    // insert user

        /*EntityManager entityManager = emFactory.createEntityManager();
        //User user = new User(null, "Alex", "123");
        User user1 = new User(null, "Ivan", "111");
        User user2 = new User(null, "Petr", "222");
        User user3 = new User(null, "Fedor", "333");

        entityManager.getTransaction().begin();

        entityManager.persist(user1);
        entityManager.persist(user2);
        entityManager.persist(user3);

        entityManager.getTransaction().commit();*/

    // select user
        /*EntityManager entityManager = emFactory.createEntityManager();
        User user = entityManager.find(User.class, 1L);
        System.out.println(user);*/

    // update user
        /*EntityManager entityManager = emFactory.createEntityManager();
        User user = entityManager.find(User.class, 1L);

        System.out.println(user);
        entityManager.getTransaction().begin();
        user.setName("Alexey");
        entityManager.getTransaction().commit();*/

    // delete user
       /* EntityManager entityManager = emFactory.createEntityManager();
        User user = entityManager.find(User.class, 1L);
        entityManager.getTransaction().begin();
        entityManager.remove(user);
        entityManager.getTransaction().commit();*/

    // Select by name
        /*EntityManager entityManager = emFactory.createEntityManager();
        // List<User> users =  entityManager.createQuery("from User u where u.name=:name", User.class)
        List<User> users = entityManager.createNamedQuery("getByName", User.class)
                .setParameter("name", "Ivan")
                .getResultList();
        users.forEach(System.out::println);

        User user = users.get(0);

        entityManager.getTransaction().begin();
        entityManager.persist(new Contact(null, "phone", "55-25-75", user));
        entityManager.persist(new Contact(null, "email", "ggg@mail.com", user));
        entityManager.persist(new Contact(null, "mobil", "+7705-123-25-75", user));
        entityManager.getTransaction().commit();

        entityManager.refresh(user);
        System.out.println(user);

        entityManager.close();

        EntityManager entityManager = emFactory.createEntityManager();
        entityManager.createNamedQuery("withUserName")
            .setParameter("id", 1L).getResultList()
            .forEach(System.out::println);*/


}
