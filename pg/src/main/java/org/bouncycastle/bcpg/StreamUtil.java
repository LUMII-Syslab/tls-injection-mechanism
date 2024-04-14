package org.bouncycastle.bcpg;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;

class StreamUtil
{
    private static final long MAX_MEMORY = Runtime.getRuntime().maxMemory();

    /**
     * Find out possible longest length, capped by available memory.
     *
     * @param in input stream of interest
     * @return length calculation or MAX_VALUE.
     */
    static int findLimit(InputStream in)
    {
        // TODO: this can obviously be improved.
        if (in instanceof ByteArrayInputStream)
        {
            return ((ByteArrayInputStream)in).available();
        }
        else if (in instanceof FileInputStream)
        {
            try
            {
                FileChannel channel = ((FileInputStream)in).getChannel();
                long size = (channel != null) ? channel.size() : Integer.MAX_VALUE;

                if (size < Integer.MAX_VALUE)
                {
                    return (int)size;
                }
            }
            catch (IOException e)
            {
                // ignore - they'll find out soon enough!
            }
        }

        if (MAX_MEMORY > Integer.MAX_VALUE)
        {
            return Integer.MAX_VALUE;
        }

        return (int)MAX_MEMORY;
    }

    static void writeNewPacketLength(OutputStream out, long bodyLen)
        throws IOException
    {
        if (bodyLen < 192)
        {
            out.write((byte)bodyLen);
        }
        else if (bodyLen <= 8383)
        {
            bodyLen -= 192;

            out.write((byte)(((bodyLen >> 8) & 0xff) + 192));
            out.write((byte)bodyLen);
        }
        else
        {
            out.write(0xff);
            writeBodyLen(out, bodyLen);
        }
    }

    static void writeBodyLen(OutputStream out, long bodyLen)
        throws IOException
    {
        out.write((byte)(bodyLen >> 24));
        out.write((byte)(bodyLen >> 16));
        out.write((byte)(bodyLen >> 8));
        out.write((byte)bodyLen);
    }

    static void writeKeyID(BCPGOutputStream pOut, long keyID)
        throws IOException
    {
        pOut.write((byte)(keyID >> 56));
        pOut.write((byte)(keyID >> 48));
        pOut.write((byte)(keyID >> 40));
        pOut.write((byte)(keyID >> 32));
        pOut.write((byte)(keyID >> 24));
        pOut.write((byte)(keyID >> 16));
        pOut.write((byte)(keyID >> 8));
        pOut.write((byte)(keyID));
    }

    static long readKeyID(BCPGInputStream in)
        throws IOException
    {
        long keyID = (long)in.read() << 56;
        keyID |= (long)in.read() << 48;
        keyID |= (long)in.read() << 40;
        keyID |= (long)in.read() << 32;
        keyID |= (long)in.read() << 24;
        keyID |= (long)in.read() << 16;
        keyID |= (long)in.read() << 8;
        return keyID | in.read();
    }

    static void writeTime(BCPGOutputStream pOut, long time)
        throws IOException
    {
        pOut.write((byte)(time >> 24));
        pOut.write((byte)(time >> 16));
        pOut.write((byte)(time >> 8));
        pOut.write((byte)time);
    }


}
