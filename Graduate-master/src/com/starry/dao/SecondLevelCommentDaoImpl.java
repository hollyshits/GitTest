package com.starry.dao;

import com.starry.entity.SecondLevelComment;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.HashMap;

public class SecondLevelCommentDaoImpl extends SqlSessionDaoSupport implements SecondLevelCommentDao {
    @Override
    public void insertSlcComment(SecondLevelComment secondLevelComment) {
        this.getSqlSession().insert("insertSlcComment",secondLevelComment);
    }

    @Override
    public void deleteSlcComment(int commentId, int sayingId) {
        HashMap<String,Integer> map=new HashMap<>();
        map.put("commentId",commentId);
        map.put("sayingId",sayingId);
        this.getSqlSession().delete("deleteSlcComment",map);
    }
}
