/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


/**
 *
 * @author Anh Nguyen
 */
public class Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
try{
            // Tao mot Server Socket voi cong 1021
        ServerSocket server = new ServerSocket(1021);
        // Server chap nhan du lieu tu cac client cung port la 1021
        Socket socket = server.accept();
        
        //Tạo luồng gửi trả kết quả Client
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        
        //Tạo luồng nhận dữ liệu trả từ Client tới
        DataInputStream in = new DataInputStream(socket.getInputStream());
        
        Scanner input = new Scanner(System.in);
        
        while(true) {
            //Đọc dữ liệu client gửi về
            String str = in.readUTF();
            /*Khi ta nhập vào q, hệ thống sẽ thoát. equalsIgnoreCase là hàm so
            sánh chuỗi kiểu String mà không quan tâm là chữ hoa hay chữ thường.
            Nếu ta nhập vào q hoặc Q thì equalsIgnoreCase trả về là True và thoát
            thoát khỏi vòng lặp While()
            */
            if (str.equalsIgnoreCase("q")) {
                break;
            } else {
                System.out.println("Client:" + str);
            }
            //Gửi dữ liệu cho Client
            System.out.print("\nI: ");
            out.writeUTF(input.nextLine()); //Thêm dữ liệu muốn gửi vào hàng chờ
            out.flush(); //Đẩy dữ liệu sang cho Client
        }
        //Đóng luồng 
        in.close();
        out.close();
        socket.close();
        server.close();
    }
    catch(IOException e){
    }
        System.err.println("Lỗi");
    }
    
}
