package com.starry.web;


import com.starry.entity.FirstLevelComment;
import com.starry.entity.SecondLevelComment;
import com.starry.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RequestMapping("/comment")
@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/add/first", method = RequestMethod.POST)
    public FirstLevelComment addFirst(FirstLevelComment flc) {
        System.out.println("增加一级评论！！！");

        //修复新评论无法显示时间的bug
        flc.setCommentTime(new Date());
        commentService.addFlcComment(flc);
        return flc;
    }

    @RequestMapping(value="/delete/first/{sayingId}/{commentId}")
    public void removeFirstLevelCommment(@PathVariable int sayingId, @PathVariable int commentId) {

        System.out.println("进入删除一级评论的控制器");
        System.out.println("sayingid"+sayingId+"===commentid"+commentId);
        commentService.removeFlcComment(commentId, sayingId);
    }

    @RequestMapping(value="/add/second", method= RequestMethod.POST)
    public SecondLevelComment addSecondLevelCommment(SecondLevelComment secondLevelComment) {
        //修复无法插入二级评论的bug
        //原因很简单：无法插入数据库，因为时间字段要求不为空
        //时间字段的插入在后端部分解决就好
        System.out.println("来到了插入二级评论的控制器！！");
        secondLevelComment.setReplyTime(new Date());
        commentService.addSlcComment(secondLevelComment);
        System.out.println(secondLevelComment);
        return secondLevelComment;
    }
    @RequestMapping(value="/delete/second/{sayingId}/{commentId}")
    public void removeSecondLevelCommment(@PathVariable int sayingId, @PathVariable int commentId) {
        System.out.println("来到删除二级评论的控制器");
        System.out.println("sayingid="+sayingId+"======slcid="+commentId);
        commentService.removeSlcComment(commentId, sayingId);
    }

}
