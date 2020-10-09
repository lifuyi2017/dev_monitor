package com.winterchen.model;

import java.io.Serializable;

public class CollectionManagerRequest  implements Serializable {
    private static final long serialVersionUID = 6167556927421596065L;

    private CollectionManager collectionManager;
    private Integer pageNum;
    private Integer pageSize;

    public CollectionManagerRequest() {
    }

    public CollectionManagerRequest(CollectionManager collectionManager, Integer pageNum, Integer pageSize) {
        this.collectionManager = collectionManager;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public CollectionManager getCollectionManager() {
        return collectionManager;
    }

    public void setCollectionManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
