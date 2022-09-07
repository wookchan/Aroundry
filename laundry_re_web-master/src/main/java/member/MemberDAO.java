package member;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO implements MemberService {

	
	  @Override public boolean member_join(MemberVO vo) { return
	  sql.insert("member.mapper.join", vo)>0 ? true : false; }
	 
	
	@Autowired @Qualifier("ateam") private SqlSession sql;

	@Override
	public MemberVO member_login(HashMap<String, String> map) {
		return sql.selectOne("member.mapper.login", map);
	}

	@Override
	public MemberVO member_myinfo(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override public boolean member_id_check(String id) { 
		return  (Integer)sql.selectOne("member.mapper.id_check", id) == 0 ? false : true; }

	@Override
	public boolean member_update(MemberVO vo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean member_delete(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String member_salt(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean member_reset_password(MemberVO vo) {
		// TODO Auto-generated method stub
		return false;
	}

	

	/*
	 * @Override public MemberVO member_login(HashMap<String, String> map) { return
	 * sql.selectOne("member.mapper.login", map); }
	 * 
	 * @Override public MemberVO member_myinfo(String id) { // TODO Auto-generated
	 * method stub return null; }
	 * 
	 * @Override public boolean member_id_check(String id) { return
	 * (Integer)sql.selectOne("member.mapper.id_check", id) == 0 ? false : true; }
	 * 
	 * @Override public boolean member_update(MemberVO vo) { return
	 * sql.update("member.mapper.update", vo)>0 ? true : false; }
	 * 
	 * @Override public boolean member_delete(String id) { // TODO Auto-generated
	 * method stub return false; }
	 * 
	 * @Override public String member_salt(String id) { return
	 * sql.selectOne("member.mapper.salt", id); }
	 * 
	 * @Override public boolean member_reset_password(MemberVO vo) { return
	 * sql.update("member.mapper.reset_password", vo)>0 ? true : false; }
	 */
}
