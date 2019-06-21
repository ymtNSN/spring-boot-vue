package com.ymt.manage.demo.nio.buffer;

import java.nio.ByteBuffer;

/**
 * @Description 缓冲区分片
 * @Author yangmingtian
 * @Date 2019/6/21
 */
public class BufferSlice {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);

        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte) i);
        }

        buffer.position(2);
        buffer.limit(5);
        ByteBuffer slice = buffer.slice();

        for (int i = 0; i < slice.capacity(); i++) {
            byte b = slice.get(i);
            b *= 10;
            slice.put(b);
        }

        buffer.position(0);
        buffer.limit(buffer.capacity());

        while (buffer.remaining() > 0) {
            System.out.println(buffer.get());
        }


    }

}
