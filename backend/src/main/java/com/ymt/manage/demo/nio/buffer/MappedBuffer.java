package com.ymt.manage.demo.nio.buffer;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Description IO映射缓冲区
 * @Author yangmingtian
 * @Date 2019/6/21
 */
public class MappedBuffer {
    public static void main(String[] args) throws IOException {
        RandomAccessFile raf = new RandomAccessFile("C:\\Users\\yangmingtian\\Desktop\\ymt.txt", "rw");
        FileChannel fc = raf.getChannel();

        MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_WRITE, 0, 23);
        mbb.put(0, (byte) 97);
        mbb.put(22, (byte) 122);

        raf.close();
    }
}
