package idv.david.websocketchat.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.MemberVO;

import RedisTest.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class NameServlet extends HttpServlet {
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession session=req.getSession();
		JedisPool pool = null;
		String action = req.getParameter("action");
	if("chat".equals(action)) {
		String userName = ((MemberVO)(session.getAttribute("login"))).getMember_name();
		Integer userID =((MemberVO)(session.getAttribute("login"))).getMember_id();
		String name = userID+":"+userName;
		String reciever=req.getParameter("recieverName");
		req.setAttribute("userName", name);
		req.setAttribute("recieverName", reciever);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/chat.jsp");
		dispatcher.forward(req, res);}
	
	if("record".equals(action)){
		
		String userName = ((MemberVO)(session.getAttribute("login"))).getMember_name();
		Integer userID =((MemberVO)(session.getAttribute("login"))).getMember_id();
		String name = userID+":"+userName;
		req.setAttribute("userName", name);
		
		pool = JedisUtil.getJedisPool();
		Jedis jedis = pool.getResource();
		jedis.auth("123456");
		Set<String> keys = jedis.keys("*"); 
        Iterator<String> it=keys.iterator() ;   
        StringBuffer keynames=new StringBuffer();
        while(it.hasNext()){   
            String key = it.next();   
            if(key.contains(userName+":")) {
            int strlong=name.length()+1;
            String friend=key.substring(strlong);
            keynames.append(friend).append(',');
            }
            
        }
        System.out.println(keynames); 
		jedis.close();
		JedisUtil.shutdownJedisPool();
		req.setAttribute("friends", keynames);
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/chatrecord.jsp");
		dispatcher.forward(req, res);}
	}
	
}
