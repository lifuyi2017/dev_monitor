package com.winterchen.dao;

import com.winterchen.model.CollectionManager;

import java.util.List;

public interface CollectionManagerMapper {
    void deleteByLogicId(String logic_id);

    void deleteByMeasureId(String measure_id);

    void deleteByChannelId(String channel_id);

    List<CollectionManager> queryByEntity(CollectionManager collectionManager);

    void updateByCollectionId(CollectionManager collectionManager1);

    void insert(CollectionManager collectionManager);
}
