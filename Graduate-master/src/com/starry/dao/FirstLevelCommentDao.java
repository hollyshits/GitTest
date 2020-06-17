package com.starry.dao;

import com.starry.entity.FirstLevelComment;
import org.apache.ibatis.annotations.Param;

public interface FirstLevelCommentDao {

    public void insertFlcComment(FirstLevelComment firstLevelComment);
    public void deleteFlcComment(@Param("commentId") int commentId, @Param("sayingId") int sayingId);
}
