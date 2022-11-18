package com.petrobest.pahorabbitmq.fbox.dao;

import com.petrobest.pahorabbitmq.fbox.domain.BoxDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoxDao {

    List<BoxDO> list(Map<String, Object> map);
}
