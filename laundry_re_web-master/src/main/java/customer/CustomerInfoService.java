package customer;

import java.util.List;

public interface CustomerInfoService {
	
	/* List<CustomerInfoVO> customer_info(Integer storeid); */
	
	List<CustomerInfoVO> customer_info(String ownerid);
	 
}
