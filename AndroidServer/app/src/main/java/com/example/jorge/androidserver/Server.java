package com.example.jorge.androidserver;

import android.util.Base64;

import java.io.IOException;
import java.util.Map;

import fi.iki.elonen.NanoHTTPD;

public class Server extends NanoHTTPD {

    public Server() throws IOException {
        super(8080);
        start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
        System.out.println("\nRunning! Point your browsers to http://localhost:8080/ \n");
    }

    @Override
    public Response serve(IHTTPSession session) {
        String msg = "<html><body>\n";
        Map<String, String> parms = session.getParms();

        MainActivity.mCamera.takePicture(null, null, MainActivity.mPicture);


        while(!MainActivity.done);



        byte[] image = MainActivity.image;


        String encodedImage = Base64.encodeToString(image, Base64.DEFAULT);

        msg+="<img src=\"data:image/jpg;base64,"+encodedImage+"\"/ style=\"width:100%; height:100%;\">";


        MainActivity.done=false;


        return newFixedLengthResponse(msg + "</body></html>\n");
    }

}