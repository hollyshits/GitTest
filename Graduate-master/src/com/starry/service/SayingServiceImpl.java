package com.starry.service;

import com.starry.dao.SayingDao;

import com.starry.entity.Saying;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SayingServiceImpl implements SayingService {

    @Autowired
    private SayingDao mapper;

    @Override
    public Saying selectOneById(int id) {
        return mapper.selectOneById(id);
    }

    @Override
    public void upadateLikesById(int SayingId, String likes) {
        mapper.updateLikesById(SayingId,likes);
    }
}
