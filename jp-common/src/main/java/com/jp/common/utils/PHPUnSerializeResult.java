package com.jp.common.utils;

class PHPUnSerializeResult {
    public Object value;
    public int hv;

    public PHPUnSerializeResult() {
    }

    public PHPUnSerializeResult(Object value, int hv) {
        this.value = value;
        this.hv = hv;
    }
}
