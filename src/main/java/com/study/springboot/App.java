package com.study.springboot;

import com.study.springboot.domain.City;
import com.study.springboot.mapper.CityMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 */
@RestController
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.study.springboot.mapper")
@ComponentScan(basePackages = {"com.study.springboot"})
// 启注解事务管理，等同于xml配置方式的 <tx:annotation-driven />
public class App {
    @RequestMapping("/")
    String index() {
        return "Hello Spring Boot";
    }


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CityMapper cityMapper;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @RequestMapping("/insert")
    @Transactional
    public void insertCity() {
        City city = City.builder().countrycode("IND").id(9999).name("daljdlas").population(23).district("ddd").build();
        cityMapper.insert(city);
        //throw new RuntimeException("xxx");

    }

    @RequestMapping("/queryCity")
    @Transactional
    public void queryCity() {
       /* jdbcTemplate.query("select * from city where id = 9999", new RowMapper<Object>() {
        });*/

    }
}
