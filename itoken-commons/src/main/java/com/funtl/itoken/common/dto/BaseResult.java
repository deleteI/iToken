package com.funtl.itoken.common.dto;

import lombok.Data;

import java.util.List;

@Data
public class BaseResult {
    private static final String RESULT_OK= "ok";
    private static final String RESULT_NOT_OK="not_ok";
    private static final String SUCCESS="操作成功";

    private String result;
    private Object data;
    private Cursor cursor;
    private  String succeess ;
    private List<Error>error;

    //根据得到的数据返回指定结果
    public static BaseResult ok() {
        return createResult(RESULT_OK, null, SUCCESS, null, null);
    }

    public static BaseResult ok(String success) {
        return createResult(RESULT_OK, null, success, null, null);
    }

    public static BaseResult ok(Object data) {
        return createResult(RESULT_OK, data, SUCCESS, null, null);
    }

    public static BaseResult ok(Object data, Cursor cursor) {
        return createResult(RESULT_OK, data, SUCCESS, cursor, null);
    }

    public static BaseResult notOk(List<BaseResult.Error> errors) {
        return createResult(RESULT_NOT_OK, null, "", null, errors);
    }


    /**
     * @param result
     * @param data
     * @param cursor
     * @param succeess
     * @param error
     * @return
     */
    private static BaseResult  createResult(String result,Object data,String succeess,Cursor cursor,List<Error>error){
        BaseResult baseResult = new BaseResult();
        baseResult.setCursor(cursor);
        baseResult.setData(data);
        baseResult.setError(error);
        baseResult.setSucceess(succeess);
        baseResult.setResult(result);
        return baseResult;
    }

    @Data
    private static class Cursor{
        private int total;
        private int offset;
        private int limit;
    }
    @Data
    private  static class Error{
        private String field;
        private String message;
    }

}
