import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
public class ResponseTest {
 
	public static void main(String[] args) {
 
		ServerSocket serverSocket = null; // サーバ用のソケット
		Socket socket = null; // ソケットをやり取りする為に使用する
		BufferedReader in = null;
		BufferedWriter out = null;
		while(true){
			try {
				serverSocket = new ServerSocket(10008); // 受信側はポート側のみ定義し、8080ポートを開放する。
				socket = serverSocket.accept(); // コネクションの要求が来るまで待機する。
 
				// Socketでのやり取りをテキストベースにする為に、BufferedWriterを使用します。受信する際に使用します。
				out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				// Socketでのやり取りをテキストベースにする為に、BufferedReaderを使用します。送信する際に使用します。
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String str; str = in.readLine(); // クライアント側からの送信を１行読み込む
				System.out.println("受信：" + str);
				String responce = "うけとったよー";
				out.write(responce); // 文字を書き込む時にはwriteを使用します。
				out.newLine(); // HTTPの際に必須になる？
				out.flush(); // 書き込んだ文字を送信先に送信する。
				System.out.println("返信：" + responce);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				try { // 終わった後の後始末。必ずクローズしましょう。
					if (serverSocket != null) {
						serverSocket.close();
					}
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
}
