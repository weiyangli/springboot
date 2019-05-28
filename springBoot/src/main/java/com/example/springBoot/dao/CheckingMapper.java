package com.example.springBoot.dao;

import com.example.springBoot.bean.Checking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 针对考勤对象的操作
 * nativeQuery = true 使用原生的sql
 * **/
public interface CheckingMapper extends JpaRepository<Checking,Integer> {
    @Query(value = "select * from checking limit ?1, ?2", nativeQuery = true)
    List<Checking> findCheckingToPage(int offset, int pageSize);
}


