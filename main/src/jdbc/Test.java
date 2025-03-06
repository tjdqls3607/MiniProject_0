package jdbc;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Test {
    public static void main(String[] args) {

        List <MemberDto> list = listmember();
        for (MemberDto dto : list) {
            System.out.println(dto);
        }

        MemberDto dto = detailMember(2);
        System.out.println(dto);

    }
    static int insertMember (int member_id, String name, String phone_number, Date birth_date) {
        Connection con = null;
        PreparedStatement pstmt = null;
        String insertSql = "insert into member values (?, ?, ?, ?); ";

        int ret = -1;
        try {
            con = DBManager.getConnection();
            pstmt = con.prepareStatement(insertSql);
            pstmt.setInt(1,member_id);
            pstmt.setString(2,name);
            pstmt.setString(3,phone_number);
            pstmt.setDate(4, (java.sql.Date) birth_date);

            ret = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.releaseConnection(pstmt, con);
        }
        return ret;
    }

    static int updateMember (int member_id, String name, String phone_number) {
        Connection con = null;
        PreparedStatement pstmt = null;

        String updateSql = "update member set name = ?, phone_number = ? where member_id = ?";

        int ret = -1;
        try {
            con = DBManager.getConnection();
            pstmt = con.prepareStatement(updateSql);
            pstmt.setString(1, name);
            pstmt.setString(2, phone_number);
            pstmt.setInt(3, member_id);

            ret = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.releaseConnection(pstmt, con);
        }
        return ret;
    }

    static int deleteMember (int member_id) {
        Connection con = null;
        PreparedStatement pstmt = null;

        String deleteSql = "delete from member where member_id = ?";

        int ret = -1;
        try {
            con = DBManager.getConnection();
            pstmt = con.prepareStatement(deleteSql);
            pstmt.setInt(1,member_id);

            ret = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.releaseConnection(pstmt, con);
        }

        return ret;
    }
    static List<MemberDto> listmember() {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<MemberDto> list = new ArrayList<>();

        String selectSql = "select * from member";

        try {
            con = DBManager.getConnection();
            pstmt = con.prepareStatement(selectSql);

            rs = pstmt.executeQuery();
            while(rs.next()) {
                MemberDto dto = new MemberDto();
                dto.setId(rs.getInt("member_id"));
                dto.setName(rs.getString("name"));
                dto.setPhoneNumber(rs.getString("phone_number"));
                dto.setBirthDate(rs.getDate("birth_date"));

                list.add(dto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.releaseConnection(pstmt, con);
        }
        return list;
    }

    static MemberDto detailMember(int member_id) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        MemberDto dto = null;

        String selectSql = "select * from member where member_id = ?";

        try {
            con = DBManager.getConnection();
            pstmt = con.prepareStatement(selectSql);
            pstmt.setInt(1, member_id);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                dto = new MemberDto();
                dto.setId(rs.getInt("member_id"));
                dto.setName(rs.getString("name"));
                dto.setPhoneNumber(rs.getString("phone_number"));
                dto.setBirthDate(rs.getDate("birth_date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.releaseConnection(pstmt, con);
        }
        return dto;
    }
}
