package com.a608.modac.model.follow;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class FollowRequest {
    private Long fromSeq;
    private Long toSeq;

}
