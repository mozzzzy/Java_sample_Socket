import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
public class SocketTest {
 
	public static void main(String[] args) {
		Socket socket = null;
		BufferedReader in = null;
		BufferedWriter out = null;
		try {
			socket = new Socket();
			//送信先サーバのIPアドレスとポート番号を定義
			socket.connect(new InetSocketAddress("localhost", 10008));
 
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
 
			String request = "test";
			out.write(request);
			out.newLine();
			out.flush();
			System.out.println("送信：" + request);
 
			String response = in.readLine();
			System.out.println("受信：" + response);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (socket != null) {
					socket.close();
				}
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
 
}
