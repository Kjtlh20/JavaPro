package com.gmail.dev.surovtsev.yaroslav;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Command list:");
            System.out.println("/exit");
            System.out.println("/reg param_login param_password");
            System.out.println("/login param_login param_password");
            System.out.println("/usrs");
            System.out.println();
            String login = null;
            boolean isloggedIn = false;
            while (true) {
                System.out.println("Enter command: ");
                String command = scanner.nextLine();
                String[] comArr = command.split(" ");
                String commandName = comArr[0];
                if ("/exit".equals(commandName)) {
                    break;
                }

                if ("/usrs".equals(commandName)) {
                    URL url = new URL(Utils.getURL() + "/usrs");
                    HttpURLConnection http = (HttpURLConnection) url.openConnection();

                    InputStream is = http.getInputStream();
                    try {
                        byte[] buf = Utils.responseBodyToArray(is);
                        String strBuf = new String(buf, StandardCharsets.UTF_8);

                        Gson gson = new GsonBuilder().create();
                        JsonUsers list = gson.fromJson(strBuf, JsonUsers.class);
                        if (list != null) {
                            for (User user : list.getList()) {
                                System.out.println(user);
                            }
                        }
                    } finally {
                        is.close();
                    }
                    continue;
                }

                if ("/reg".equals(commandName) || "/login".equals(commandName)) {
                    if (comArr.length < 3) {
                        System.out.println("Wrong command or parameters");
                        continue;
                    }
                }
                if ("/login".equals(commandName)) {
                    login = comArr[1];
                }
                Command commandReg = new Command(comArr[0], new String[]{comArr[1], comArr[2]});
                int res = commandReg.send(Utils.getURL() + commandName);
                if (res != 200) { // 200 OK
                    System.out.println("HTTP error occured: " + res);
                    continue;
                }
                if ("/login".equals(commandName)) {
                    isloggedIn = true;
                }
                if (isloggedIn) {
                    break;
                }
            }

            Thread th = new Thread(new GetThread());
            th.setDaemon(true);
            th.start();

            System.out.println("Enter your message: ");
            while (true) {
                String text = scanner.nextLine();
                if (text.isEmpty()) break;

                Message m = new Message(login, text);
                int res = m.send(Utils.getURL() + "/add");

                if (res != 200) { // 200 OK
                    System.out.println("HTTP error occured: " + res);
                    return;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
