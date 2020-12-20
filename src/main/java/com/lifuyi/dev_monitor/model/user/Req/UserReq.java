package com.lifuyi.dev_monitor.model.user.Req;

import com.lifuyi.dev_monitor.model.user.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Api(value = "用户查询实体")
public class UserReq {

    @ApiModelProperty(value = "用户查询实体" ,position = 1)
    private User user;
    @ApiModelProperty(value = "页码" ,position = 2)
    private Integer pageNum;
    @ApiModelProperty(value = "每页数量" ,position = 3)
    private Integer pageSize;

    public UserReq(User user, Integer pageNum, Integer pageSize) {
        this.user = user;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public UserReq() {
    }
}
