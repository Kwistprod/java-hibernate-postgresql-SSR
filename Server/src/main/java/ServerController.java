import Pages.Students;
import Pages.Task;
import Pages.Templates.IndexPage;
import Pages.Templates.StudentsPage;
import Pages.Templates.TaskPage;
import PagesStuff.Ipage;
import PagesStuff.PAGES;
import Solution.Result;
import Solution.Solution;
import StudentsStuff.Student;
import StudentsStuff.StudentsController;
import StudentsStuff.db.DBManager;
import freeMarker.FreeMarkerUtil;
import freemarker.template.Template;

import java.io.*;
import java.net.Socket;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class ServerController {
    private final Socket socket;

    public ServerController(Socket socket){
        this.socket = socket;
    }

    public void definePage(){
        try(
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedOutputStream dataOut = new BufferedOutputStream(socket.getOutputStream());
        ) {
            String input = in.readLine();
            StringTokenizer tokens = new StringTokenizer(input);
            String method = tokens.nextToken().toUpperCase();
            String request = URLDecoder.decode(tokens.nextToken(), StandardCharsets.UTF_8);
            System.out.println("Method: " + method);
            System.out.println("Request: " + request);
            StudentsController studentsController = new DBManager();
            Map<String, Object> map = new HashMap<>();
            if (method.equals("GET")) {
                if(!isHtml(request) && (request.endsWith(".js") || request.endsWith(".css") || request.endsWith(".ico"))){
                    sendHeaders(out, request);
                    byte[] arr = readFile(request.substring(1));
                    dataOut.write(arr);
                }
                else {
                    Ipage current;
                    if(!request.endsWith(".html")){
                        request = request.concat(".html");
                    }
                    sendHeaders(out, request);
                    if (PAGES.TASK.value().equals(request.substring(1))) {
                        current = new TaskPage();
                    } else if (PAGES.STUDENTS.value().equals(request.substring(1))) {
                        current = new StudentsPage(studentsController);
                    } else {
                        current = new IndexPage();
                    }
                    dataOut.write(Objects.requireNonNull(current).getHtmlSource().getBytes());
                }
                dataOut.flush();
            } else if(method.equals("POST")) {
                sendHeaders(out, request);
                String postData = readPostRequest(in);
                System.out.println(postData);
                if (request.endsWith("task")) {
                    Result res = new Solution(postData);
                    dataOut.write(res.getResult().getBytes());
                    dataOut.flush();
                } else {
                    Long id = studentsController.addStudent(parseStudent(postData));
                    System.out.println(id);
                    dataOut.write(id.toString().getBytes());
                    dataOut.flush();
                }
            } else if(method.equals("PUT")) {
                Long id = Long.parseLong(request.split("/")[3]);
                String postData = readPostRequest(in);
                System.out.println(postData);
                Student tmp = parseStudent(postData);
                studentsController.updateStudent(tmp, id);
            } else if(method.equals("DELETE")){
                Long id = Long.parseLong(request.split("/")[3]);
                studentsController.deleteStudent(id);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private byte[] readFile(String path){
        byte[] arr = null;
        System.out.println(path);
        try(InputStream inp = getClass().getResourceAsStream(path)){
            int length = inp.available();
            arr = new byte[length];
            inp.read(arr);
        }catch(Exception e){
            e.printStackTrace();
        }
        return arr;
    }

    private boolean isHtml(String req){
        return (req.endsWith(".html") || req.endsWith(".htm"));
    }

    private String readPostRequest(BufferedReader in)throws IOException{
        String line;
        int postlength = -1;
        while ((line = in.readLine()) != null && line.length() > 0) {
            System.out.println(line);
            if (line.contains("Content-Length:")) {
                postlength = Integer.parseInt(line.substring(16));
            }
        }
        String postData = null;
        if (postlength > 0) {
            char[] charArray = new char[postlength];
            in.read(charArray, 0, postlength);
            postData = new String(charArray);
        }
        return postData;
    }

    private Student parseStudent(String postStr){
        Map<String, String> map = Arrays.stream(postStr.split(","))
                .map((s) -> {
                    s = s.replaceAll("([{}\"])", "");
                    return s.split(":");
                })
                .collect(Collectors.toMap(s -> s[0], s ->s[1]));
        return new Student(map.get("FIO"), Integer.parseInt(map.get("age")));

    }

    private void sendHeaders(PrintWriter out, String req){
        out.println("HTTP/1.1 200 OK");
        out.println("Server: Java HTTP Server : 1.0");
        out.println("Date: " + new Date());
        out.println("Content-type: " + (isHtml(req) ? "text/html" : "text/plain") + "; charset=UTF-8");
        out.println();
        out.flush();
    }
}
