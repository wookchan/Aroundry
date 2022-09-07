package com.hanul.laundry.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.hanul.laundry.dto.MainMyWashDTO;
import com.hanul.laundry.dto.MainWashAllDTO;
import com.hanul.laundry.dto.StoreDTO;
import com.hanul.laundry.dto.UserDTO;

import oracle.jdbc.internal.OracleTypes;


public class KangDao {
	
	// 데이터 베이스와 연동 : 데이터베이스 초기화 해줌
	DataSource dataSource;
	
	// 생성자를 통해서 데이터베이스 드라이버를 선언해준다
	public KangDao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:/comp/env/teamAll");			
			
		}catch(NamingException e) {
			e.getMessage();
		}
		
	}	
	
	
	// 8. 회원가입 : anJionCommand에서 값을 넘겨받는다
	public UserDTO kangJoin(String id, String name, String email, String phone,  String profile) {
		// 데이터베이스와 연동하여 원하는 결과물을 얻는다.
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int state = -100;
		UserDTO dto = null;
		
		try {
			connection = dataSource.getConnection();
			/*
			String query = "insert into userinfo(userid, name, email, profile, phone) " 
						+ " values('" + id + "', '" + name + "', '" + email  + "', '" 
						+ profileimage + "', '" + phonenumber +"' )";
			preparedStatement = connection.prepareStatement(query);
			state = preparedStatement.executeUpdate();
			
			if(state > 0) {
				System.out.println("회원가입성공!!!");
			}else {
				System.out.println("회원가입실패 ㅜㅜ");
			}
			*/
			//2022.08.18 로그인사용자 정보 조회 프로시저호출로 변경 by 조순섭
			String query = "{call pro_userselect(?,?, ?, ?, ?, ?)}";
            CallableStatement statement = connection.prepareCall(query);
            statement.setString(1, id);
            statement.setString(2, name);
            statement.setString(3, email);
            statement.setString(4, phone);
            statement.setString(5, profile);
            statement.registerOutParameter(6, OracleTypes.CURSOR);
            statement.execute();
            ResultSet rs = (ResultSet) statement.getObject(6);
            if( rs.next() ){
            	dto = new UserDTO(rs.getString("userid"), rs.getString("name"), rs.getString("email")
            			,rs.getString("phone"), rs.getString("profile"),  rs.getString("point"));
            }			
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(connection != null) {
					connection.close();
				}
				
			}catch(Exception e) {
				e.getMessage();
			}
		}
		
		// 9. 원하는 값을 넘겨줌
		return dto;
		
	}
	
	public UserDTO moneycharge(String userid, String moneyin) {
		// 데이터베이스와 연동하여 원하는 결과물을 얻는다.
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int state = -100;
		UserDTO dto = null;
		
		int money = Integer.parseInt(moneyin);
		
		try {
			connection = dataSource.getConnection();
			
			//2022.08.18 로그인사용자 정보 조회 프로시저호출로 변경 by 조순섭
			String query = "{call pro_moneycharge(?,?, ?)}";
            CallableStatement statement = connection.prepareCall(query);
            statement.setString(1, userid);
            statement.setInt(2, money);
            statement.registerOutParameter(3, OracleTypes.CURSOR);
            statement.execute();
            ResultSet rs = (ResultSet) statement.getObject(3);
            if( rs.next() ){
            	dto = new UserDTO(rs.getString("userid"), rs.getString("name"), rs.getString("email")
            			,rs.getString("phone"), rs.getString("profile"),  rs.getString("point"));
            }			
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(connection != null) {
					connection.close();
				}
				
			}catch(Exception e) {
				e.getMessage();
			}
		}
		
		// 9. 원하는 값을 넘겨줌
		return dto;
		
	}
	
	public HashMap<String, String> bookmark_store(String userid){
		HashMap<String, String> bookmark = new HashMap<String, String>();
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs;
		try {
			conn = dataSource.getConnection();
			/*
			String query = "insert into userinfo(userid, name, email, profile, phone) " 
						+ " values('" + id + "', '" + name + "', '" + email  + "', '" 
						+ profileimage + "', '" + phonenumber +"' )";
			preparedStatement = connection.prepareStatement(query);
			state = preparedStatement.executeUpdate();
			
			if(state > 0) {
				System.out.println("회원가입성공!!!");
			}else {
				System.out.println("회원가입실패 ㅜㅜ");
			}
			*/
			//2022.08.18 로그인사용자 정보 조회 프로시저호출로 변경 by 조순섭
			String query = "select storeid from bookmark where userid=?";
			ps = conn.prepareStatement(query);            
			ps.setString(1, userid);
			rs = ps.executeQuery();
            while( rs.next() ){
            	bookmark.put(String.valueOf(rs.getInt("storeid")), String.valueOf(rs.getInt("storeid")) );
            }			
            rs.close();
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(ps != null) {
					ps.close();
				}
				if(conn != null) {
					conn.close();
				}
				
			}catch(Exception e) {
				e.getMessage();
			}
		}
		
		return bookmark;
	}


	public ArrayList<MainWashAllDTO> mainwashall(String storeidin) {

		// 데이터베이스와 연동하여 원하는 결과물을 얻는다.
		ArrayList<MainWashAllDTO> dtos = new ArrayList<MainWashAllDTO>();
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;		
		
		try {
			connection = dataSource.getConnection();
			String query = "select m.storeid, m.machineid, machineseq, m.cost, s.resttime, userid, u.name, ss.location "
					+ " from storemachine m left outer join storestate s using(machineseq) "
					+ " left outer join userinfo u using(userid) left outer join store ss on ss.storeid = m.storeid "
					+ " where m.storeid=" + storeidin 
					+ " order by m.machineid";
			prepareStatement = connection.prepareStatement(query);
			resultSet = prepareStatement.executeQuery();
			
			while (resultSet.next()) {
				//여기에서 위도 경도 계산함수(xxx.java or dao 에서 만들기) 돌리고 건네줌
				//2020.08.18 Store테이블의 전체컬럼을 StoreDTO 에 담아 넣는 것으로 처리 변경 by조순섭
				String storeid = resultSet.getString("storeid");
				String machineid = resultSet.getString("machineid");
				String machineseq = resultSet.getString("machineseq");
				String cost = resultSet.getString("cost");
				String resttime = resultSet.getString("resttime");
				String userid = resultSet.getString("userid");
				
				dtos.add(new MainWashAllDTO(storeid, machineid, machineseq, cost, resttime, userid));						
			}	
			
			//연결되었는지 확인용 print
			System.out.println("dtosSize : " + dtos.size());
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
		} finally {
			try {			
				
				if (resultSet != null) {
					resultSet.close();
				}
				if (prepareStatement != null) {
					prepareStatement.close();
				}
				if (connection != null) {
					connection.close();
				}	

			} catch (Exception e) {
				e.printStackTrace();
			} finally {

			}
		}		
		
		return dtos;
	}


	public ArrayList<MainMyWashDTO> mainwashmy(String useridin) {
		// 데이터베이스와 연동하여 원하는 결과물을 얻는다.
		ArrayList<MainMyWashDTO> dtos = new ArrayList<MainMyWashDTO>();
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;		
		
		try {
			connection = dataSource.getConnection();
			String query = "select s.userid, s.machineseq, s.resttime, m.machineid, m.storeid, ss.address, ss.location"
					+ " from storestate s left outer join storemachine m on s.machineseq = m.machineseq"
					+ " left outer join store ss on ss.storeid = m.storeid"
					+ " where s.userid ='" + useridin + "'" 
					+ " order by s.machineseq ";
			prepareStatement = connection.prepareStatement(query);
			resultSet = prepareStatement.executeQuery();
			
			while (resultSet.next()) {
				//여기에서 위도 경도 계산함수(xxx.java or dao 에서 만들기) 돌리고 건네줌
				//2020.08.18 Store테이블의 전체컬럼을 StoreDTO 에 담아 넣는 것으로 처리 변경 by조순섭
				String userid = resultSet.getString("userid");
				String machineseq = resultSet.getString("machineseq");
				String resttime = resultSet.getString("resttime");
				String machineid = resultSet.getString("machineid");
				String storeid = resultSet.getString("storeid");
				String address = resultSet.getString("address");
				String location = resultSet.getString("location");
				
				
				dtos.add(new MainMyWashDTO(userid, machineseq, resttime, machineid, storeid, address, location));						
			}	
			
			//연결되었는지 확인용 print
			System.out.println("dtosSize : " + dtos.size());
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
		} finally {
			try {			
				
				if (resultSet != null) {
					resultSet.close();
				}
				if (prepareStatement != null) {
					prepareStatement.close();
				}
				if (connection != null) {
					connection.close();
				}	

			} catch (Exception e) {
				e.printStackTrace();
			} finally {

			}
		}		
		
		return dtos;
	}
	
			

}












