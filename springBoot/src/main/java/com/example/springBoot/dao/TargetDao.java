package com.example.springBoot.dao;

import com.example.springBoot.bean.Target;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TargetDao extends JpaRepository<Target,Integer> {
    List<Target> findByTnameLike(String s);
}
