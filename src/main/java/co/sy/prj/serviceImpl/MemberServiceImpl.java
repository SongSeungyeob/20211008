package co.sy.prj.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.sy.prj.dao.DataSource;
import co.sy.prj.service.MemberService;
import co.sy.prj.service.MemberVO;

public class MemberServiceImpl implements MemberService {
	private DataSource dao = DataSource.getInstance();
	private Connection conn;		// DB와의 연결을 위한 객체.
	private PreparedStatement psmt; // SQL 문 전달, 실행, 결과를 받는다.
	private ResultSet rs;			// SELECT 구문의 결과를 받는다.
	
	@Override
	public List<MemberVO> selectMemberList() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO vo;
		String sql = "select * from member"; 	// SQL문 그대로 입력.
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();	// SQL문 실행 후, 결과를 받음.
			while(rs.next()) {   		// 여기서 값을 읽고 담아서 전달해 준다.
				vo = new MemberVO();
				vo.setId(rs.getString("id"));	// 내가 만든 DB의 Column명과 동일하게 적어줘야 한다.
				vo.setName(rs.getString("name"));
				vo.setPassword(rs.getString("password"));
				vo.setTel(rs.getString("tel"));
				vo.setAddress(rs.getString("address"));
				vo.setAuthor(rs.getString("author"));
				list.add(vo);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	@Override
	public MemberVO selectMember(MemberVO vo) {	// 한명 조회하기.
		String sql = "SELECT * FROM member WHERE id = ?";	// ? = 넘어온 인자의 값으로 검색할 때 사용.
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getId());		// ? 에 들어갈 값 설정. ?가 여러개일 경우 앞에서부터 1, 2, 3번... 순서대로 번호가 붙음
												// setString(1, vo.getID()) = 1번 물음표에다가 vo.getID()의 값을 집어 넣겠습니다.
			rs = psmt.executeQuery();			// SELECT는 executeQuery();
			if(rs.next()) {
				vo.setName(rs.getString("name"));
				vo.setPassword(rs.getString("password"));
				vo.setTel(rs.getString("tel"));
				vo.setAddress(rs.getString("address"));
				vo.setAuthor(rs.getString("author"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return vo;
	}

	// SELECT 제외한 INSERT , UPDATE , DELETE는 마지막에 executeUpdate();
	@Override
	public int insertMember(MemberVO vo) {
		String sql = "INSERT INTO member VALUES(?,?,?,?,?,?)";
		int n = 0;
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getId());
			psmt.setString(2, vo.getName());
			psmt.setString(3, vo.getPassword());
			psmt.setString(4, vo.getTel());
			psmt.setString(5, vo.getAddress());
			psmt.setString(6, vo.getAuthor());
			n = psmt.executeUpdate();   // 성공시 1, 실패시 0 반환.
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return n;
	}

	@Override
	public int updateMember(MemberVO vo) {
		// PRIMARY KEY(ID)는 UPDATE 할 수가 없다.
		int n = 0;
		String sql = "UPDATE member SET name = ?, password = ?, tel = ?, address = ?, author = ? "
				+ "WHERE id = ?"; // Space  Enter를 하면 지가 알아서 문자열을 더해준다.
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1,  vo.getName());
			psmt.setString(2,  vo.getPassword());
			psmt.setString(3,  vo.getTel());
			psmt.setString(4,  vo.getAddress());
			psmt.setString(5,  vo.getAuthor());
			psmt.setString(6,  vo.getId());
			n = psmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return n;
	}

	@Override
	public int deleteMember(MemberVO vo) {
		int n = 0;
		String sql = "DELETE FROM member WHERE id = ?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getId());
			n = psmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return n;
	}
	
	private void close() {
		try {
			if(rs != null) rs.close();
			if(psmt != null) psmt.close();
			if(conn != null) conn.close();	
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
