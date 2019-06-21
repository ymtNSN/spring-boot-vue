package com.ymt.manage.demo.nio.buffer;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Description TODO
 * @Author yangmingtian
 * @Date 2019/6/21
 */
public class BufferDemo {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("C:\\Users\\yangmingtian\\Desktop\\ymt.txt");
        FileChannel fc = fis.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(10);
        output("初始化",buffer);

        fc.read(buffer);
        output("调用read()",buffer);

        buffer.flip();
        output("调用flip()",buffer);

        while (buffer.hasRemaining()){
            byte b = buffer.get();
            System.out.println((char) b);
        }
        output("调用get()",buffer);

        buffer.clear();
        output("调用clear()",buffer);

        fis.close();
    }

    private static void output(String step, ByteBuffer buffer) {
        System.out.println(step+":");
        System.out.print("capacity: " +buffer.capacity()+",");
        System.out.print("position: "+buffer.position()+",");
        System.out.println("limit: "+buffer.position()+",");
        System.out.println();
    }
}
