package com.petrobest.pahorabbitmq.fbox.service;

import com.petrobest.pahorabbitmq.fbox.domain.BoxDO;

import java.util.List;
import java.util.Map;

public interface BoxService {
    List<BoxDO> list(Map<String, Object> map);
}
