package com.wojbk.miaosha.exception;

import com.wojbk.miaosha.result.CodeMsg;

/**
 * 2019-5-15 21:45
 * @author qinyonghao
 */
public class GlobalException extends RuntimeException {
    private static final long serialVersionUID=1L;
    private CodeMsg cm;

    public GlobalException(CodeMsg cm){
        super(cm.toString());
        this.cm=cm;
    }

    public CodeMsg getCm() {
        return cm;
    }

}
