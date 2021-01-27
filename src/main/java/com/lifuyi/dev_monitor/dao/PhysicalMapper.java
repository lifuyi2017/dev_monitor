package com.lifuyi.dev_monitor.dao;

import com.lifuyi.dev_monitor.model.physical.Physical;
import com.lifuyi.dev_monitor.model.physical.PhysicalChannelBinding;
import com.lifuyi.dev_monitor.model.physical.resp.PhysicalResp;

import java.util.List;

public interface PhysicalMapper {
    Physical getByCode(String code);

    void addOrUpdatePhysical(Physical physical);

    List<PhysicalResp> getPageByEntity(Physical physical);

    void insertOrUpdatePhysicalChannelBinding(PhysicalChannelBinding physicalChannel);

    void setNetworkIdNullByNetworkId(String network_id);

    void removeBingdingCollectId(String id);

    List<String> getChannelTypeByPhysicalId(String id);

    void deletePhysicalBingdingByPhysicalId(String id);

    void deleteById(String id);
}
