package testdatasource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.vote.model.VoteVO;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Tuple;

public class testVote {
	//準備的常量資訊、Jedis連線池、發表的文章集合
		private static JedisPool JEDIS_POOL = new JedisPool("localhost", 6379);
		private final static String AUTHOR_ID = "author:id:incr";
		private final static String AUTHOR_PREFIX = "author:";
		private final static String AUTHOR_QUEUE = "author:queue";
		private final static String AUTHOR_VOTE = "author:vote:";
		public static List<Long> authors = new ArrayList<>();
		static VoteVO votevo = new VoteVO();
		// 發表文章
		public static void publish(Map<String, String> author) {
		    Jedis resource = JEDIS_POOL.getResource();
		    try {
		        //自增生成ID(使用str)
		        Long id = resource.incr(AUTHOR_ID);
		        authors.add(id);
		        author.put("id", id.toString());

		        //儲存文章資訊(使用str)
		        resource.hmset(AUTHOR_PREFIX + id, author);
		        //將文章加入排行榜中(使用zset)
		        resource.zadd(AUTHOR_QUEUE, 0D, AUTHOR_PREFIX + id);
		    } catch (Exception e) {
		        e.printStackTrace();
		    } finally {
		        resource.close();
		    }
		}
		
		public static void vote(Long authorId, Long userId) {
		    Jedis resource = JEDIS_POOL.getResource();
		    try {
		        //檢查當前使用者是否已經投過票(使用set)
		        Long addResult = resource.sadd(AUTHOR_VOTE + authorId, userId.toString());
		        
		        
		        votevo.setAddResult(addResult);
		        
		        if (addResult == 0) {
		            System.out.println("此使用者已為此作家投過票，請勿重複投票！");
		            return;
		        }
		        
		        
		        resource.zincrby(AUTHOR_QUEUE, 1D, AUTHOR_PREFIX + authorId);
		        System.out.println(String.format("投票成功，會員：【%s】,投給作家：【%s】", userId, authorId));
		    } catch (Exception e) {
		        e.printStackTrace();
		    } finally {
		        resource.close();
		    }
		}
		
		//獲取排行榜資訊
		public static void rank() {
		    Jedis resource = JEDIS_POOL.getResource();
		    
		    
		    List list = new ArrayList();
		    try {
		        //獲取排行榜
		        Set<Tuple> tuples = resource.zrevrangeWithScores(AUTHOR_QUEUE, 0L, -1L);
		        
		        for (Tuple tuple : tuples) {
//		            System.out.println("作家編號：" + tuple.getElement() + ",投票數：" + tuple.getScore());
		        	tuple.getScore();
		        	tuple.getElement();
//		        	System.out.println(resource.hget(tuple.getElement(),"name"));
		        	resource.hget(tuple.getElement(),"img_name");
		        	
		        	list.add(resource.hmget(tuple.getElement(),"name","img_name","id"));
		  
//		        	for(Object a : list) {
//		        		System.out.println(a);
//		        	}
		       
		        }
		        
		        
//		        System.out.println(list); 
		        
	        	for(Object a : list) {
        		System.out.println(a);
        	}
		        
//		        System.out.println(resource.hget("author:1", "name"));
		        
//		        Set<String> zrange = resource.zrange("author:queue", 0, -1);
//		        System.out.println("zrange = " + zrange);
		        
//		        Double author_1_score = resource.zscore("author:queue", "author:1");
//		        Double author_2_score = resource.zscore("author:queue", "author:2");
//		        Double author_3_score = resource.zscore("author:queue", "author:3");
//		        Double author_4_score = resource.zscore("author:queue", "author:4");
//		        Double author_5_score = resource.zscore("author:queue", "author:5");
//		        Double author_6_score = resource.zscore("author:queue", "author:6");
//		        System.out.println("zscore = " + author_1_score);
//		        votevo.setAuthor_1_score(author_1_score);
//		        votevo.setAuthor_2_score(author_2_score);
//		        votevo.setAuthor_3_score(author_3_score);
//		        votevo.setAuthor_4_score(author_4_score);
//		        votevo.setAuthor_5_score(author_5_score);
//		        votevo.setAuthor_6_score(author_6_score);
		        
		        
		        
		        
		    } catch (Exception e) {
		        e.printStackTrace();
		    } finally {
		        resource.close();
		    }
		}
		
		public static void main(String[] args) {
			Jedis resource = JEDIS_POOL.getResource();
			
		    //建立文章(建立10篇文章，id：1~10)
//		    for (int i = 1; i <= 6; i++) {
//		        Map<String, String> author = new HashMap<>();
//		        author.put("name", "Leslie");
//		        publish(author);
//		    }
		    //投票(隨機1000個使用者，總共投5000票)
		    for (long i = 0; i < 5000; i++) {
		        vote((long)(new Random().nextInt(12)+1), (long) (new Random().nextInt(1000)+1000));
		    }
		    //獲取排行榜

//			vote(3L, 1002L);
			
			
			//新增用
			Map<String, String> author = new HashMap<>();
//	        author.put("name", "Saria");
//	        author.put("img_name","model1.jpeg");
//	        publish(author);
//	        
//	        author.put("name", "唐熒霜");
//	        author.put("img_name","model2.jpeg");
//	        publish(author);
//	        
//	        author.put("name", "喬夏");
//	        author.put("img_name","model4.jpeg");
//	        publish(author);
//	   
//	        author.put("name", "Passion Tsai");
//	        author.put("img_name","model7.jpeg");
//	        publish(author);
//	   
//	        author.put("name", "Tomo");
//	        author.put("img_name","model8.jpeg");
//	        publish(author);
//	   
//	        author.put("name", "Leslie");
//	        author.put("img_name","model9.jpeg");
//	        publish(author);
//		
//	        author.put("name", "Hank");
//	        author.put("img_name","model10.jpg");
//	        publish(author);
//	        
//	        author.put("name", "Lisa");
//	        author.put("img_name","model11.jpg");
//	        publish(author);
//	        
//	        author.put("name", "泰勒");
//	        author.put("img_name","model12.jpg");
//	        publish(author);
//	        
//	        author.put("name", "William");
//	        author.put("img_name","model13.jpg");
//	        publish(author);
//	        
//	        author.put("name", "星哥");
//	        author.put("img_name","model14.jpg");
//	        publish(author);
//	        
//	        author.put("name", "Jasmine");
//	        author.put("img_name","model15.jpg");
//	        publish(author);
	        
		    rank();
		}
		
	
}
