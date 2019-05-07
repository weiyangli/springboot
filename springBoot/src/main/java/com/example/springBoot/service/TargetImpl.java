package com.example.springBoot.service;

import com.example.springBoot.bean.Target;
import com.example.springBoot.dao.TargetDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TargetImpl implements TargetService{

    @Autowired
    private TargetDao targetDao;


    @Override
    public void save(Target target) {
        targetDao.save(target);
    }

    @Override
    public void delete(Integer id) {
        targetDao.deleteById(id);
    }

    @Override
    public List<Target> findAll() {
        return targetDao.findAll();
    }

    @Override
    public List<Target> findByTnameLike(String tname) {
        return targetDao.findByTnameLike("%" + tname + "%");
    }
}
