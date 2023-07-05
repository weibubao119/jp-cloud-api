package com.jp.common.utils;

public interface PHPSerializable {
    byte[] serialize();
    void unserialize(byte[] ss);
}
