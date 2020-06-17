package com.starry.service;

import com.starry.dao.FirstLevelCommentDao;
import com.starry.dao.SecondLevelCommentDao;
import com.starry.entity.FirstLevelComment;
import com.starry.entity.SecondLevelComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private FirstLevelCommentDao flcmapper;
    @Autowired
    private SecondLevelCommentDao slcmapper;

    @Override
    public void addFlcComment(FirstLevelComment firstLevelComment) {
        flcmapper.insertFlcComment(firstLevelComment);
    }

    @Override
    public void removeFlcComment(int commentId, int sayingId) {
            flcmapper.deleteFlcComment(commentId,sayingId);
    }

    @Override
    public void addSlcComment(SecondLevelComment secondLevelComment) {
        slcmapper.insertSlcComment(secondLevelComment);
    }

    @Override
    public void removeSlcComment(int commentId, int sayingId) {
            slcmapper.deleteSlcComment(commentId,sayingId);
    }
}
