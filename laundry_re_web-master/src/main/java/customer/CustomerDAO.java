package customer;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDAO implements CustomerService{
	@Autowired @Qualifier("ateam") private SqlSession sql;


	/*
	 * public List<CustomerVO> customer_detail(Integer storeid) { return
	 * sql.selectList("customer.mapper.detail", storeid); }
	 */
	
	  public List<CustomerVO> customer_detail(int storeid) { 
		  return sql.selectList("customer.mapper.detail", storeid); 
	}

	@Override
	public List<CustomerVO> customer_storelist(String ownerid) {
		return sql.selectList("customer.mapper.storelist", ownerid);
	}
	@Override
	public List<HashMap<String, Integer>> store_monthlycost(int storeid) {
		return sql.selectList("customer.mapper.graph", storeid);
	}


	@Override
	public CustomerVO customer_store(int storeid) {
		return sql.selectOne("customer.mapper.info", storeid);
	}

	 

	/*public List<CustomerVO> customer_graph(String ownerid) {
		return sql.selectOne("customer.mapper.graph", ownerid);
	}*/




	

	
}
