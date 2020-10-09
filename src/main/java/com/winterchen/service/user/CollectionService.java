package com.winterchen.service.user;

import com.winterchen.model.CollectionManager;

import java.util.List;

public interface CollectionService {
    List<CollectionManager> queryByEntity(CollectionManager collectionManager) throws Exception;

    void updateByCollectionId(CollectionManager collectionManager1) throws Exception;

    void insert(CollectionManager collectionManager) throws Exception;

    void deleteByElementId(String dev_element_id);

    void deleteById(String collection_id);
}
