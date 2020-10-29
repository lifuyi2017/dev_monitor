package com.winterchen.service.user;

import com.github.pagehelper.PageInfo;
import com.winterchen.model.CollectionManager;
import com.winterchen.model.CollectionManagerHttp;
import com.winterchen.model.CollectionManagerRequest;

import java.util.List;

public interface CollectionService {
    List<CollectionManager> queryByEntity(CollectionManager collectionManager) throws Exception;

    void updateByCollectionId(CollectionManager collectionManager1) throws Exception;

    void insert(CollectionManager collectionManager) throws Exception;

    void deleteByElementId(String dev_element_id);

    void deleteById(String collection_id);

    void putToMqtt(CollectionManager collectionManager1, String flag) throws Exception;

    List<CollectionManager> getByChId(String collectQ);

    PageInfo<CollectionManagerHttp> queryByEntityBPage(CollectionManagerRequest collectionManagerRequest);

    List<CollectionManager> getByChIdAndId(CollectionManager col);
}
