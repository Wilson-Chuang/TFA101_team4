package com.member.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.article.model.ArticleDAO;
import com.article.model.ArticleDAO_interface;
import com.article.model.ArticleVO;
import com.article_favorite.model.Article_FavoriteDAO;
import com.article_favorite.model.Article_FavoriteDAO_interface;
import com.article_favorite.model.Article_FavoriteVO;
import com.member_follower.model.Member_FollowerDAO;
import com.member_follower.model.Member_FollowerDAO_Interface;
import com.member_follower.model.Member_FollowerVO;
import com.shop.model.ShopDAO;
import com.shop.model.ShopDAO_interface;
import com.shop.model.ShopVO;
import com.shop_favorites.model.Shop_FavoritesDAO;
import com.shop_favorites.model.Shop_FavoritesDAO_Interface;
import com.shop_favorites.model.Shop_FavoritesVO;



public class MemberService {
	private MemberDAO_Interface dao;
	private Member_FollowerDAO_Interface dao_fol;
	private ShopDAO_interface dao_shop;
	private Shop_FavoritesDAO_Interface dao_shop_fav;
	private ArticleDAO_interface dao_article;
	private Article_FavoriteDAO_interface dao_article_fav;
	
	public MemberService() {
		dao= new MemberDAO();
		dao_fol=new Member_FollowerDAO();
		dao_shop=new ShopDAO();
		dao_shop_fav=new Shop_FavoritesDAO();
		dao_article=new ArticleDAO();
		dao_article_fav=new Article_FavoriteDAO();
	}
	public MemberVO insert(String Member_email,String Member_password){
		MemberVO memberVO=new MemberVO();
		memberVO.setMember_email(Member_email);
		memberVO.setMember_password(Member_password);
		dao.insert(memberVO);
		
		return memberVO;
	}

	public MemberVO update(String Member_name,Integer Member_gender,Date Member_birth,
		String Member_phone,String Member_address,String Member_pic,Timestamp Member_update_time,
		Integer Member_age,String Member_email) {
		MemberVO memberVO=new MemberVO();
		memberVO.setMember_name(Member_name);
		memberVO.setMember_gender(Member_gender);
		memberVO.setMember_birth(Member_birth);
		memberVO.setMember_phone(Member_phone);
		memberVO.setMember_address(Member_address);
		memberVO.setMember_pic(Member_pic);
		memberVO.setMember_update_time(Member_update_time);
		memberVO.setMember_age(Member_age);
		memberVO.setMember_email(Member_email);
		dao.update(memberVO);

		return memberVO;
	}
	public ShopVO update_shop(String Shop_name, Integer Shop_price_level, String Shop_opening_time,
			String Shop_address,String Shop_city, String Shop_website, String Shop_phone, String Shop_email, 
			String Shop_description, String Shop_tag,Timestamp Shop_update_time,String filename,String shop_gallery,Integer id) 
	{
		ShopVO ShopVO=new ShopVO();
		ShopVO.setShop_name(Shop_name);
		ShopVO.setShop_price_level(Shop_price_level);
		ShopVO.setShop_opening_time(Shop_opening_time);
		ShopVO.setShop_address(Shop_address);
		ShopVO.setShop_city(Shop_city);
		ShopVO.setShop_website(Shop_website);
		ShopVO.setShop_phone(Shop_phone);
		ShopVO.setShop_email(Shop_email);
		ShopVO.setShop_description(Shop_description);
		ShopVO.setShop_tag(Shop_tag);
		ShopVO.setShop_update_time(Shop_update_time);
		ShopVO.setShop_main_img(filename);
		ShopVO.setShop_gallery(shop_gallery);
		ShopVO.setShop_id(id);
		dao_shop.update_shop(ShopVO);
		System.out.println("update_shop in svc");
		return ShopVO;
	}

	public void delete_fol(Integer MEMBER_ID,Integer MEMBER_ID_FOLLOWER) {
		Member_FollowerVO Member_FollowerVO=new Member_FollowerVO();
		Member_FollowerVO.setMEMBER_ID(MEMBER_ID_FOLLOWER);
		Member_FollowerVO.setMEMBER_ID_FOLLOWER(MEMBER_ID);
		dao_fol.delete(Member_FollowerVO);
	}
	
public void delete_sf(int member_ID, int shop_ID) {
		Shop_FavoritesVO Shop_Favorites=new Shop_FavoritesVO();
		Shop_Favorites.setMEMBER_ID(member_ID);
		Shop_Favorites.setSHOP_ID(shop_ID);
		dao_shop_fav.delete_sf(Shop_Favorites);
		
	}
	public void delete(String MEMBER_EMAIL) {
		dao.delete(MEMBER_EMAIL);
	}

	public MemberVO getOneMem(String MEMBER_EMAIL) {
		return dao.getOneStmt(MEMBER_EMAIL);
	}
	public MemberVO GET_ONE_BY_ID(Integer MEMBER_ID) {
		return dao.GET_ONE_BY_ID(MEMBER_ID);
	}
	public ShopVO GET_ONE_BY_MEMBER(Integer MEMBER_ID) {
		return dao_shop.findByMemberId(MEMBER_ID);
	}
	public ShopVO GET_ONE_BY_SHOP(Integer SHOP_ID) {
		return dao_shop.findByPrimaryKey(SHOP_ID);
	}
	public List<Shop_FavoritesVO> getAllByMember(Integer Member_ID) {
		return dao_shop_fav.getAllByMember(Member_ID);
	}
	public List<MemberVO> getAll() {
		return dao.getAll();
	}
	 public List<String> check() {
		 return dao.accountCheck();
		 
	 }
	 public List<ArticleVO> getMyArticle(Integer member_id){
		 return dao_article.getMyArticle(member_id);
	 }
	 public ArticleVO getArticleFollowed(Integer article_no){
		 return dao_article.getArticleFollowed(article_no);
	 }
	 public List<Article_FavoriteVO> getAllArticleFavByMem(Integer member_id) {
			return dao_article_fav.getAllByMember(member_id);
		}
	
}