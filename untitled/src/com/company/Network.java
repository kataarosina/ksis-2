package com.company;
import java.net.*;
import java.io.*;



public class Network {

    public String traceRoute(String host) {

        String route = "";
        try {
            InetAddress address = InetAddress.getByName(host);
            Process traceRt;
            traceRt = Runtime.getRuntime().exec("tracert " + address.getHostAddress());


            // read the output from the command
            route = convertStreamToString(traceRt.getInputStream());

            // read any errors from the attempted command
            String errors = convertStreamToString(traceRt.getErrorStream());
            if (errors != "") System.out.println(errors);
        } catch (IOException e) {
            System.out.println("error while performing trace route command");
        }

        return route;
    }
    private String convertStreamToString(InputStream stream) throws IOException {
        if (stream != null) {
            Writer writer = new StringWriter();

            char[] buffer = new char[1024];
            try {
                Reader reader = new BufferedReader(new InputStreamReader(stream,
                        "cp866"));
                int n;
                while ((n = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);
                }
            } finally {
                stream.close();
            }
            return writer.toString();
        }
        return "";
    }

}
