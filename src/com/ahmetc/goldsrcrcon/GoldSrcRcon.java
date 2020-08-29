package com.ahmetc.goldsrcrcon;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GoldSrcRcon {

    public static final int CONNECTION_TIMEOUT = 2000;
    private String ip;
    private int port;
    private String password;
    private DatagramSocket clientSocket = new DatagramSocket();

    public GoldSrcRcon(String ip, int port, String password) throws Exception {
        this.ip = ip;
        this.port = port;
        this.password = password;
        this.clientSocket.setSoTimeout(CONNECTION_TIMEOUT);
        this.clientSocket.connect(InetAddress.getByName(ip), port);
    }

    public String sendConsoleCommand(String str) throws Exception {
        String sendCommand = sendCommand(str);
        return sendCommand.substring(5);
    }

    private String sendCommand(String str) throws IOException {
        String str2;
        byte[] bArr = new byte[1024];

        String str3 = "xxxxchallenge rcon";
        String sb2 = "";
        sb2 = str3 + ".";
        byte[] bytes = sb2.getBytes();
        bytes[0] = -1;
        bytes[1] = -1;
        bytes[2] = -1;
        bytes[3] = -1;
        bytes[bytes.length - 1] = 0;
        Matcher matcher = Pattern.compile(".*challenge rcon ([0-9]+).*", 32).matcher(new String(sendRawCommand(bytes)));
        if (matcher.matches()) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("xxxxrcon \"");
            sb3.append(matcher.group(1));
            sb3.append("\" ");
            sb3.append(this.password);
            sb3.append(" ");
            sb3.append(str);
            str2 = sb3.toString();
        } else {
            throw new IOException("unable to get challenge code for rcon");
        }

        String sb5 = str2 + ".";
        byte[] bytes2 = sb5.getBytes();
        bytes2[0] = -1;
        bytes2[1] = -1;
        bytes2[2] = -1;
        bytes2[3] = -1;
        bytes2[bytes2.length - 1] = 0;
        String str4 = new String(sendRawCommand(bytes2));
        return str4.substring(0, str4.indexOf("\u0000"));
    }

    private byte[] sendRawCommand(byte[] bArr) throws IOException {
        byte[] bArr2 = new byte[10000];
        DatagramPacket datagramPacket = new DatagramPacket(bArr, bArr.length);
        DatagramPacket datagramPacket2 = new DatagramPacket(bArr2, bArr2.length);
        this.clientSocket.send(datagramPacket);
        this.clientSocket.receive(datagramPacket2);
        return datagramPacket2.getData();
    }

    // Getters And Setters

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
