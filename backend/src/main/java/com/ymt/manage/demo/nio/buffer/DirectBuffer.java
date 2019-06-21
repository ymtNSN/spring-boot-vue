package com.ymt.manage.demo.nio.buffer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Description 直接缓冲区
 * @Author yangmingtian
 * @Date 2019/6/21
 */
public class DirectBuffer {

    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("C:\\Users\\yangmingtian\\Desktop\\ymt.txt");
        FileChannel fcin = fis.getChannel();

        FileOutputStream fos = new FileOutputStream("C:\\Users\\yangmingtian\\Desktop\\ymtcopy.txt");
        FileChannel fcout = fos.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while (true) {
            buffer.clear();

            int r = fcin.read(buffer);
            if (r == -1) {
                break;
            }
            buffer.flip();

            fcout.write(buffer);
        }
    }
}
