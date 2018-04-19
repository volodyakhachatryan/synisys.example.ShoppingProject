package config;

import dao.Dao;
import models.User;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by volodya.khachatryan on 4/13/2018.
 */

@Configuration
@ComponentScan(basePackageClasses = {Dao.class, User.class})
public class Config {

}
