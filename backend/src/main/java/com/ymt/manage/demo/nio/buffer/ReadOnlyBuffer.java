package com.ymt.manage.demo.nio.buffer;

import java.nio.ByteBuffer;

/**
 * @Description 只读缓冲区
 * @Author yangmingtian
 * @Date 2019/6/21
 */
public class ReadOnlyBuffer {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);

        // 缓冲区中的数据0-9
        for (int i = 0; i < buffer.capacity(); ++i) {
            buffer.put((byte) i);
        }

        ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();

        // 改变原缓冲区的内容
        for (int i = 0; i < buffer.capacity(); ++i) {
            byte b = buffer.get(i);
            b *= 10;
            buffer.put(i, b);
        }

        readOnlyBuffer.position(0);
        readOnlyBuffer.limit(buffer.capacity());

        while (readOnlyBuffer.remaining() > 0) {
            System.out.println(readOnlyBuffer.get());
        }

        buffer.flip();
        while (buffer.remaining() > 0) {
            System.out.println(buffer.get());
        }
    }
}
