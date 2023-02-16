package com.example.tobyspring.config.autoconfig;

import com.example.tobyspring.HelloBootTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@HelloBootTest
public class JdbcTemplateTest {
    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * 내장형 디비를 생성할 때는 기초 데이터를 넣는 작업을 해야 한다.
     */
    @BeforeEach
    void init() {
        jdbcTemplate.execute("create table if not exists hello(name varchar(50) primary key, count int)");
    }

    @Test
    public void insertAndQuery() {
        //given
        jdbcTemplate.update("insert into hello values(?,?)", "Toby", 3);
        jdbcTemplate.update("insert into hello values(?,?)", "Yushin", 1);
        //when

        Long count = jdbcTemplate.queryForObject("select count(1) from hello", Long.class);
        //then
        Assertions.assertThat(count).isEqualTo(2);
    }
}
