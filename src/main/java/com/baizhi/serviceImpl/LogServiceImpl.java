package com.baizhi.serviceImpl;

import com.baizhi.dao.LogMapper;
import com.baizhi.entity.Log;
import com.baizhi.entity.VideoExample;
import com.baizhi.service.LogService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
@Service("logservice")
@Transactional
public class LogServiceImpl implements LogService {
    @Autowired
    private LogMapper logMapper;

    @Override
    @Transactional(propagation =  Propagation.SUPPORTS)
    public HashMap<String, Object> queryByPage(Integer page, Integer rows) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("page", page);
        VideoExample example = new VideoExample();
        int records = logMapper.selectCountByExample(example);
        map.put("records", records);
        Integer total = records / rows == 0 ? records / rows : records / rows + 1;
        map.put("total", total);
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        List<Log> log = logMapper.selectByRowBounds(new Log(), rowBounds);
        map.put("rows", log);
        return map;
    }

    @Override
    public int queryTotal() {
        Log log = new Log();
        return logMapper.selectCount(log);
    }
}
