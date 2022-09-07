package user;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import customer.CustomerVO;

@Repository
public class UserDAO implements UserService {
	@Autowired @Qualifier("ateam") private SqlSession sql;

	@Override
	public UserVO user_detail(CustomerVO csvo) {
		return sql.selectOne("customer.mapper.user", csvo);
	}

}
