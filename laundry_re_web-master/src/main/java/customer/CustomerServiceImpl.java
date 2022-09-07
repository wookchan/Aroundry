package customer;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {


	@Autowired private CustomerDAO dao;

	@Override
	  public List<CustomerVO> customer_detail(int storeid) { 
		return dao.customer_detail(storeid); 
		}
	

	@Override
	public List<CustomerVO> customer_storelist(String ownerid) {
		return dao.customer_storelist(ownerid);
	}

	@Override
	public List<HashMap<String, Integer>> store_monthlycost(int storeid) {
		return dao.store_monthlycost(storeid);
	}

	@Override
	public CustomerVO customer_store(int storeid) {
		return dao.customer_store(storeid);
	}




	
	
		/*
		 * public List<CustomerVO> customer_graph(String ownerid) { return
		 * dao.customer_graph(ownerid); }
		 */




}
