package bitcamp.pms.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bitcamp.pms.dao.ClassroomDao;
import bitcamp.pms.domain.Classroom;

@Service
public class ClassroomService {
    @Autowired 
    ClassroomDao classroomDao;

    public List<Classroom> list(int page, int size) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("startIndex", (page - 1) * size);
        params.put("pageSize", size);
        return classroomDao.selectList(params);
    }

    public void add(Classroom classroom) {
        classroomDao.insert(classroom);
    }

    public int update(Classroom classroom) {
        System.out.println(classroom.toString());
        
        return classroomDao.update(classroom);
    }

    public int delete(int no) {
        return classroomDao.delete(no);
    }

    public Classroom get(int no) {
        return classroomDao.selectOne(no);
    }
    
    public int getTotalPage(int size) {
        int count = classroomDao.countAll();
        int totalPage = count / size;
        if (count % size > 0)
            totalPage++;
        
        return totalPage;

    }

}
