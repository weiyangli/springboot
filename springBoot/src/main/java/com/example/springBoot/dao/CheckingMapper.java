package com.example.springBoot.dao;

import com.example.springBoot.bean.Checking;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 针对考勤对象的操作
 *
 * **/
public interface CheckingMapper extends JpaRepository<Checking,Integer> {
}


