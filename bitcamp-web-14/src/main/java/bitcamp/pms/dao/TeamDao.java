package bitcamp.pms.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bitcamp.pms.domain.Team;

public interface TeamDao {
    

    public int delete(String name);
    public List<Team> selectList();
    public int insert(Team team);
    public int update(Team team);
    public Team selectOne(String name);
    public Team selectOneWithMembers(String name);
    }
