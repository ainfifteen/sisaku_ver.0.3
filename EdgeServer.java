package sisaku;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class EdgeServer {
	final static int ID = 3000;;//エッジサーバのID

	Udp udp;

	EdgeServer() throws IOException{
		udp = new Udp(ID);//UDPインスタンスにID付与
		udp.makeMulticastSocket();//ソケット生成
		udp.startListener();//受信
		//udp.sendMsgFromServer();//送信
	}

	void receiveData() throws IOException{//受信メソッド
		byte[] rcvData = udp.lisner.getData();//受信データ

		if(rcvData != null) {

			//System.out.println(rcvData);
			String str = new String(rcvData);//byte型から文字に変換
			System.out.println("(エッジサーバ受信データ) "+str);
			try {
				FileWriter fw = new FileWriter("/Users/TKLab/Desktop/data.txt",true);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(str);
				bw.newLine();//改行
				bw.flush();
				bw.close();
			}catch(IOException e) {
				System.out.println("エラー");
			}

			udp.lisner.resetData();//バッファの中のデータをリセット
		}

	}

	void  judgmentData(){

	}

}
