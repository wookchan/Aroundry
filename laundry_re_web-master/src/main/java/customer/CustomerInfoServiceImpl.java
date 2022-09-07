package customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerInfoServiceImpl implements CustomerInfoService {

	@Autowired private CustomerInfoDAO dao;
	
	
	  @Override 
	  public List<CustomerInfoVO> customer_info(String ownerid) { 
		  return dao.customer_info(ownerid); 
		  }
	 
		/*
		 * @Override public List<CustomerInfoVO> customer_info(Integer storeid) { return
		 * dao.customer_info(storeid); }
		 */

}
