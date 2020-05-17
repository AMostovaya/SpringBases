package ru.geekbrains.server;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import ru.geekbrains.server.auth.AuthService;
import ru.geekbrains.server.auth.AuthServiceJdbcImpl;
import ru.geekbrains.server.persistance.UserRepository;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Map;

public class ChatServerApplication {
    public static void main(String[] args) throws SQLException {
        /*Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/network_chat",
                "root", "root");
        UserRepository userRepository = new UserRepository(conn);
        if (userRepository.getAllUsers().size() == 0) {
            userRepository.insert(new User(-1, "ivan", "123"));
            userRepository.insert(new User(-1, "petr", "345"));
            userRepository.insert(new User(-1, "julia", "789"));
        }
        AuthService authService = new AuthServiceJdbcImpl(userRepository);
        ChatServer chatServer = new ChatServer(authService);*/

        //ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringApp.class);



        ChatServer chatServer = context.getBean("chatServer", ChatServer.class);

        chatServer.start(7777);
    }
}
