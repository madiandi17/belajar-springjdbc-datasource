package com.wordpress.bmadi.belajar.springjdbc.datasource.main;

import com.wordpress.bmadi.belajar.springjdbc.datasource.dao.ProdukDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainSpringJDBC {

    public static void main(String[] args) {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("classpath:springjdbc.xml");
        ProdukDao pd = (ProdukDao) ctx.getBean("produkDao");

        // untuk test, silahkan buka skema-mysql
    }

}
