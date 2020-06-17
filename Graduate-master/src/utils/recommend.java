package utils;

import com.starry.dao.UserDao;
import com.starry.entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class recommend {
    /*List<User> userDOList = UserDao.listAllUsers();
    List<orderDO> bookDOList = bookDao.listAllBooks();
    List<orderDO> borrowDOList = borrowDao.listAllBorrows();
    List<RecommendDOc> recommendDOList = Lists.newArrayList();

    //记录每个医生被预约的次数
    Map<String, Integer> bookCountMap = new HashMap();

    //记录某一个人是否看过某一医生,如果看过则分数使用最近的评分.
    //同时还记录上一次修改的位置
    Map<String,Integer> hasSeenTheBook=new HashMap();

        recommendDao.deleteBefore(0);
    *//**
     * 进行数据清洗，生成处理后的预约列表
     *//*
        for (BorrowDO borrowDO : borrowDOList) {
        BookDO bookDO = bookDao.queryBookByBookId(borrowDO.getBookId());
        UserDO userDO = userDao.queryByUserId(borrowDO.getUserId());
        //不通过校验
        if (bookDO == null || userDO == null) {
            continue;
        }

        String key = Joiner.on("-").skipNulls().join(bookDO.getBookName(), bookDO.getAuthor());
        if (bookCountMap.get(key) == null) {
            //医生第一次被预约
            bookCountMap.put(key, 1);
            //患者预约过该医生，并且记下这条记录的下标
            hasSeenTheBook.put(Joiner.on("-").skipNulls().join(userDO.getUserId(),key),recommendDOList.size());
        } else {
            //不是第一次预约医生
            int count = bookCountMap.get(key);
            bookCountMap.put(key, count + 1);
            //多次预约同一个医生评论使用最后一次评分为准
            if (hasSeenTheBook.get(Joiner.on("-").skipNulls().join(userDO.getUserId(),key))!=null) {
                int index=hasSeenTheBook.get(Joiner.on("-").skipNulls().join(userDO.getUserId(),key));
                System.out.println("index:"+index);
                recommendDOList.get(index).setRate(borrowDO.getGoal());
            }
        }
        RecommendDO recommendDO = new RecommendDO();
        recommendDO.setUserId(borrowDO.getUserId());
        recommendDO.setBookId(key);
        recommendDO.setRate(borrowDO.getGoal());
        recommendDOList.add(recommendDO);
    }
        System.out.println("recommendDOList:"+recommendDOList);
        System.out.println("map:" + bookCountMap);
    //一个预约量的最大堆
    Queue<Map.Entry<String,Integer>> priorityQueue=new PriorityQueue<>((a,b)->(b.getValue().compareTo(a.getValue())));
        priorityQueue.addAll(bookCountMap.entrySet());
        System.out.println("priorityQueue:"+priorityQueue);
    //针对每个用户对每个以上评分的最大堆
    Queue<Map.Entry<String,Double>>bookDOQueue=new PriorityQueue<>((a,b)->((b.getValue().compareTo(a.getValue()))));
        for (UserDO userDO : userDOList) {
        //对于每一个以上,求出有哪些人预约过该医生,再分别求该用户与每一个人的相似度
        priorityQueue.clear();
        //兜底，每次都重新计算最大堆，防止为这个用户推荐的以上不够三人
        priorityQueue.addAll(bookCountMap.entrySet());
        Map<String, Double> bookCompare = Maps.newHashMap();
        for (BookDO bookDO : bookDOList) {

            if (neednot(userDO,bookDO,recommendDOList)){
                continue;
            }
            //求出有哪些人预约过此医生
            List<RecommendDO> SeenTheBook = find(bookDO, recommendDOList);
            System.out.println("SeenTheBook:" + SeenTheBook+"bookName:"+bookDO.getBookName());
            if (CollectionUtils.isEmpty(SeenTheBook)) {
                continue;
            }

            //recommend列表代表每一个人
            double rate = 0;
            double weightSum = 0;
            for (RecommendDO recommendDO : SeenTheBook) {
                //对于每一个人计算我和他的相似度
                double weight = 0;
                try {
                    weight = calUserSimilarity(userDO, recommendDO, recommendDOList);
                } catch (BusinessException be) {
                    continue;
                }
                System.out.println("weight:" + weight);
                //计算相似度
                rate = rate + weight * recommendDO.getRate();
                System.out.println("用户"+userDO.getUserId()+"对于"+bookDO.getBookName()+"rate:"+rate);
                weightSum = weightSum + weight;
            }
            if (weightSum!=0) {
                //获取该用户对于每一医生的评价得分
                double score = rate / weightSum;
                System.out.println("用户" + userDO.getUserId() + "对于" + bookDO.getBookName() + score);
                //最后所有书的得分求出最大值
                bookCompare.put(Joiner.on("-").skipNulls().join(bookDO.getBookName(), bookDO.getAuthor()), score);
            }
        }
        bookDOQueue.clear();
        bookDOQueue.addAll(bookCompare.entrySet());
        System.out.println("最后推荐列表:,userId"+userDO.getUserId()+","+"bookDOQueue:" + bookDOQueue);
        //取前三放置在数据库里面
        int count = 0;
        //判断推荐了那些医生
        List<String>recommendList=Lists.newArrayList();
        while (!bookDOQueue.isEmpty() && count < 3) {
            //从推荐最大堆中取出键值对
            String mix = bookDOQueue.poll().getKey();
            //分割出bookName，author
            Iterator<String> iterator = Splitter.on("-").split(mix).iterator();
            List<String> stringList = Lists.newArrayList();
            while (iterator.hasNext()) {
                stringList.add(iterator.next());
            }
            //添加进入数据库
            pushToDataBase(stringList.get(0), stringList.get(1), userDO.getUserId());
            recommendList.add(Joiner.on("-").skipNulls().join(stringList.get(0),stringList.get(1)));
            count++;
        }
        System.out.println("the rest:"+count);
        //不够的使用热销补齐
        while (count < 3&&!priorityQueue.isEmpty()) {
            Map.Entry<String, Integer> entry = priorityQueue.poll();
            System.out.println(entry);
            String mix = entry.getKey();
            System.out.println(mix);
            Iterator<String> iterator = Splitter.on("-").split(mix).iterator();
            List<String> stringList = Lists.newArrayList();
            while (iterator.hasNext()) {
                stringList.add(iterator.next());
            }
            //如果预约最大堆中取出的医生已经预约过，则跳过
            if (hotFilter(userDO,stringList.get(0),stringList.get(1),recommendDOList)){
                continue;
            }
            //如果医生已经在推荐列表中，则跳过
            if (recommendList.contains(Joiner.on("-").skipNulls().join(stringList.get(0),stringList.get(1)))){
                continue;
            }
            recommendList.add(Joiner.on("-").skipNulls().join(stringList.get(0),stringList.get(1)));
            pushToDataBase(stringList.get(0), stringList.get(1), userDO.getUserId());
            count++;
        }

    }
    private boolean hotFilter(UserDO userDO, String s, String s1, List<RecommendDO> recommendDOListBase) {
        for (RecommendDO recommendDO : recommendDOListBase) {
            if (recommendDO.getUserId() == userDO.getUserId() && Joiner.on("-").skipNulls().join(s,s1).equals(recommendDO.getBookId())) {
                return true;
            }
        }
        return false;
    }*/

}
