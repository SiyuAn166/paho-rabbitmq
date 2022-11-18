package com.petrobest.pahorabbitmq.fbox.service.impl;

import com.petrobest.pahorabbitmq.fbox.dao.BoxDao;
import com.petrobest.pahorabbitmq.fbox.domain.BoxDO;
import com.petrobest.pahorabbitmq.fbox.service.BoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BoxServiceImpl implements BoxService {
    @Autowired
    private BoxDao boxDao;

    @Override
    public List<BoxDO> list(Map<String, Object> map) {
        return boxDao.list(map);
    }
}
