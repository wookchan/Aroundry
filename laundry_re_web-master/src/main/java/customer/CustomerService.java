package customer;

import java.util.HashMap;
import java.util.List;

public interface CustomerService {
	//CRUD
	

	//고객상세조회(Read:select)
	/* List<CustomerVO> customer_detail(Integer storeid); */
		
	 
	//가맹점주 매장 목록
	List<CustomerVO> customer_storelist(String ownerid);
	
	//가맹점주 매장  
	CustomerVO customer_store(int storeid);
	
	
	//매장 월별 매출
	List<HashMap<String, Integer>> store_monthlycost(int storeid);
	
	//매장이용고객목록
	List<CustomerVO> customer_detail(int storeid);

	/* List<CustomerVO> customer_graph(String ownerid); */


	//고객상세정보
	

}
