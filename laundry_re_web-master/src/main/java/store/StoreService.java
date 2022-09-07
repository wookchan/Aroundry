package store;

import java.util.HashMap;

public interface StoreService {
	//CRUD(Create, Read, Update, Delete)
	boolean store_join(StoreVO vo); //회원가입시 회원정보저장
	void store_update(StoreVO vo); //내정보수정
	void store_delete(StoreVO vo); //회원탈퇴시
	StoreVO store_info(int storeid);
}
