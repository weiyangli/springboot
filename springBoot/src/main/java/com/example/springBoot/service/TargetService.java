package com.example.springBoot.service;

import com.example.springBoot.bean.Target;
import java.util.List;

public interface TargetService {
    void save(Target target);

    void delete(Integer id);

    List<Target> findAll();

    List<Target> findByTnameLike(String s);
}
