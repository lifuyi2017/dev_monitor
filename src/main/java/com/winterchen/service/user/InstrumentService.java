package com.winterchen.service.user;

import com.winterchen.model.Network;

public interface InstrumentService {
    void updateById(Network network) throws Exception;

    void insertEntity(Network network) throws Exception;
}
