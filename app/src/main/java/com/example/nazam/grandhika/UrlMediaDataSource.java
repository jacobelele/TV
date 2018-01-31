package com.example.nazam.grandhika;

/**
 * Created by PSI_DEV_07 on 1/30/2018.
 */

import android.media.MediaDataSource;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.URL;
import java.net.HttpURLConnection;

@RequiresApi(api = Build.VERSION_CODES.M)
public class UrlMediaDataSource extends MediaDataSource {
    URL url;
    HttpURLConnection connection;
    BufferedInputStream stream;

    public UrlMediaDataSource(URL url) throws IOException {
        this.url = url;
        connection = (HttpURLConnection) url.openConnection();
        DatagramSocket socket = new DatagramSocket();
        InetAddress address = InetAddress.getByAddress("10.0.2.2".getBytes());

        // send request
        byte[] buf = new byte[256];

        DatagramPacket packet =
                new DatagramPacket(buf, buf.length, address, 1234);
        socket.send(packet);

//        sendState("connected");

        // get response
        packet = new DatagramPacket(buf, buf.length);


        socket.receive(packet);
        String line = new String(packet.getData(), 0, packet.getLength());
    }

    @Override
    public long getSize() {
        return 0;
    }

    @Override
    public int readAt(long position, byte[] buffer, int offset, int size) throws IOException {
        if (stream == null)
            stream = new BufferedInputStream(connection.getInputStream());
        return stream.read(buffer, offset, size);
    }

    @Override
    public void close() throws IOException {
        stream.close();
        stream = null;
        connection.disconnect();
        connection = null;
    }
}
