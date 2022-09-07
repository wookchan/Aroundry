package user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import customer.CustomerVO;

@Service
public class UserServiceImpl implements UserService {
	@Autowired private UserDAO dao;

	@Override
	public UserVO user_detail(CustomerVO csvo) {
		return dao.user_detail(csvo);
	}

}
