package com.javayuhm.fastlms.common.model;

import lombok.Data;

@Data
public class ResponseResult {
    ResponseResultHeader header;
    Object body;

    public ResponseResult(boolean result, String message) {
        header = new ResponseResultHeader(result, message);
    }
}
