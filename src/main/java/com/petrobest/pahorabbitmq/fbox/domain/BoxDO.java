package com.petrobest.pahorabbitmq.fbox.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class BoxDO {
    private long boxId; //主键
    private String boxDeviceId;//设备序列号
    private String boxName;//设备别名
    private String boxStat;//设备状态
    private String boxAddr;//设备地址
    private String boxGroup;//设备组
}
