package com.ymt.manage.demo.nio.buffer;

import java.nio.ByteBuffer;

/**
 * @Description 手动分配缓冲区
 * @Author yangmingtian
 * @Date 2019/6/21
 */
public class BufferWrap {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);

        ByteBuffer wrap = ByteBuffer.wrap(new byte[10]);
    }

}
