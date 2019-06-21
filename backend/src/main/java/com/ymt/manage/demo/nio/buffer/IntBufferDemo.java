package com.ymt.manage.demo.nio.buffer;

import java.nio.IntBuffer;

/**
 * @Description TODO
 * @Author yangmingtian
 * @Date 2019/6/21
 */
public class IntBufferDemo {

    public static void main(String[] args) {
        IntBuffer buffer = IntBuffer.allocate(10);

        for (int i = 0; i < buffer.capacity(); i++) {
            int j = 2 * (i + 1);
            buffer.put(j);
        }

        buffer.flip();

        while (buffer.hasRemaining()) {
            System.out.print(buffer.get() + " ");
        }

    }
}
