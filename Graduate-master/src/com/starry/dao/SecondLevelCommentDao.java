package com.starry.dao;

import com.starry.entity.SecondLevelComment;
import org.apache.ibatis.annotations.Param;

public interface SecondLevelCommentDao {

    public void insertSlcComment(SecondLevelComment secondLevelComment);
    public void deleteSlcComment(@Param("commentId") int commentId, @Param("sayingId") int sayingId);
}
