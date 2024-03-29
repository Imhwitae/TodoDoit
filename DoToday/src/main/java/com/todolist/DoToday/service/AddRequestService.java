package com.todolist.DoToday.service;

import com.todolist.DoToday.dto.request.AddRequest;
import com.todolist.DoToday.dto.request.FriendInfoDto;
import com.todolist.DoToday.dto.response.MemberDetailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddRequestService {
    private final JdbcTemplate jdbcTemplate;
    private final MemberService memberService;

//    public AddRequestService(DataSource dataSource) {
//        this.jdbcTemplate = new JdbcTemplate(dataSource);
//    }

    // 친구신청 수락대기 리스트
    public List<FriendInfoDto> selectRequestList(String userId) {
        String sql = "select * from add_request where receiver_id = ? and req_status = 1";
        List<FriendInfoDto> flist = jdbcTemplate.query(sql, new Object[]{userId},
                new RowMapper<FriendInfoDto>() {
                    @Override
                    public FriendInfoDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                        MemberDetailDto md = memberService.findById(rs.getString("sender_id"));
                        FriendInfoDto fid = new FriendInfoDto();
                        fid.setMemberId(md.getMemberId());
                        fid.setMemberName(md.getMemberName());
                        fid.setMemberImage(md.getMemberImage());
                        return fid;
                    }
                }
        );
        return flist;
    }

    // 친구신청 삭제(거부)
    public int deniedRequest(AddRequest addRequest) {
        String sql = "delete from add_request where receiver_id = ? and sender_id = ?";
        return jdbcTemplate.update(sql, addRequest.getReceiverId(), addRequest.getSenderId());
    }

    // 친구신청 수락
    public int acceptRequest(AddRequest addRequest) {
        String sql = "delete from add_request where receiver_id = ? and sender_id = ?";
        jdbcTemplate.update(sql, addRequest.getReceiverId(), addRequest.getSenderId());

        sql = "insert into friend_list(user_id,friend_id) values (?,?)";
        jdbcTemplate.update(sql, addRequest.getReceiverId(), addRequest.getSenderId());// 본인 리스트에 추가
        return jdbcTemplate.update(sql, addRequest.getSenderId(), addRequest.getReceiverId());// 상대 리스트에 추가
    }

    // 친구신청 차단
    public int blockUser(AddRequest addRequest) {
        String sql = "update add_request set req_status = 0 where receiver_id = ? and sender_id = ?";
        return jdbcTemplate.update(sql, addRequest.getReceiverId(), addRequest.getSenderId());
    }

    // 친구 신청 목록 개수
    public int listCount(String userId) {
        String sql = "select count(*) from add_request where receiver_id =? and req_status = 1";//차단 상태일때도 db에는 남아있기때문 조건을 하다 더 걸어둠
        int result = this.jdbcTemplate.queryForObject(sql, Integer.class, userId);
        return result;
    }
}
