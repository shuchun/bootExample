package com.example.mvcdemo.demo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.mvcdemo.demo.pageModel.AccountPageModel;


/**
 * 
 * @author ysc
 *
 */
//@MyBatisRepository
@Component
public class AccountDemoDao {
	
	@Autowired
	private SqlSession sqlSession;

	/**
	 * 获取数据列表
	 * @param param
	 * @return
	 */
	public List<AccountPageModel> getAccountList(Map<String,Object> param){
		return this.sqlSession.selectList("getAccountList", param);
	}
}
