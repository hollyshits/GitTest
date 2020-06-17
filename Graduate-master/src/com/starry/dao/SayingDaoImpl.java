package com.starry.dao;

import com.starry.entity.Saying;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.HashMap;

public class SayingDaoImpl extends SqlSessionDaoSupport implements SayingDao {
    @Override
    public Saying selectOneByIdSimple(int id) {
        return this.getSqlSession().selectOne("selectOneByIdSimple",id);
    }

    @Override
    public Saying selectOneById(int id) {
        return this.getSqlSession().selectOne("selectOneById",id);
    }

    @Override
    public void updateLikesById(int id, String likes) {
        HashMap<String,String> map=new HashMap<>();
        String s=String.valueOf(id);
        map.put("id",s);
        map.put("likes",likes);
        this.getSqlSession().update("updateLikesById",map);
    }
}
