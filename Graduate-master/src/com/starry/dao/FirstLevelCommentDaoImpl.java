package com.starry.dao;

import com.starry.entity.FirstLevelComment;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.HashMap;

public class FirstLevelCommentDaoImpl extends SqlSessionDaoSupport implements FirstLevelCommentDao {
    @Override
    public void insertFlcComment(FirstLevelComment firstLevelComment) {
        this.getSqlSession().insert("insertFlcComment",firstLevelComment);
    }

    @Override
    public void deleteFlcComment(int commentId, int sayingId) {
        HashMap<String,Integer> map=new HashMap<>();
        map.put("commentId",commentId);
        map.put("sayingId",sayingId);
        this.getSqlSession().delete("deleteFlcComment",map);
    }
}
