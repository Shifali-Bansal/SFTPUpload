package com.newgen.app;

import com.jcraft.jsch.*;

public class SftpUploader {
    public static void main(String[] args) {
        String host = "your_sftp_host";
        int port = 22;
        String username = "your_username";
        String password = "your_password";
        String localFilePath = "path/to/local/file.txt";
        String remoteFilePath = "/path/on/sftp/server/file.txt";

        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(username, host, port);
            session.setPassword(password);

            // Avoid asking for key confirmation
            session.setConfig("StrictHostKeyChecking", "no");

            session.connect();

            ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();

            // Upload file
            channelSftp.put(localFilePath, remoteFilePath);

            // Disconnect
            channelSftp.disconnect();
            session.disconnect();

            System.out.println("File uploaded successfully to SFTP server.");
        } catch (JSchException | SftpException e) {
            e.printStackTrace();
        }
    }
}
