package com.starry.dao;

import com.starry.entity.Saying;
import org.apache.ibatis.annotations.Param;

public interface SayingDao {


    public Saying selectOneByIdSimple(int id);

    public Saying selectOneById(int id);

    public void updateLikesById(@Param("id") int id, @Param("likes") String likes);

}
