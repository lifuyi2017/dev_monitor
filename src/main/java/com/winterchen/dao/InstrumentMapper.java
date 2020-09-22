package com.winterchen.dao;

import com.winterchen.model.Network;

public interface InstrumentMapper {
    void updateById(Network network);

    void insert(Network network);
}
