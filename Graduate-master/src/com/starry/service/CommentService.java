package com.starry.service;

import com.starry.entity.FirstLevelComment;
import com.starry.entity.SecondLevelComment;

public interface CommentService {
    public void addFlcComment(FirstLevelComment firstLevelComment);

    public void removeFlcComment(int commentId, int sayingId);

    public void addSlcComment(SecondLevelComment secondLevelComment);

    public void removeSlcComment(int commentId, int sayingId);
}
