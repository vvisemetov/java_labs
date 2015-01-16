package concurrency;

import java.util.concurrent.SynchronousQueue;
import java.io.UnsupportedEncodingException;

public class InputStream implements Runnable {

	private SynchronousQueue<String> syncQueue;
	private Thread InputStreamThread;
	private String datatoCrypt = "";
	private String keyForData = "";
	private String dataEncrypted = "";

	public InputStream(SynchronousQueue<String> syncQueue, String data,
			String keyForData) throws InterruptedException,
			UnsupportedEncodingException {

		this.syncQueue = syncQueue;
		this.datatoCrypt = data;
		this.keyForData = keyForData;
		this.dataEncrypted = encrypt();

		InputStreamThread = new Thread(this, "InputStream Thread");
		InputStreamThread.start();
	}

	private String encrypt() throws UnsupportedEncodingException {

		byte[] arr = this.datatoCrypt.getBytes();
		byte[] keyarr = this.keyForData.getBytes();
		byte[] result = new byte[arr.length];

		for (int i = 0; i < arr.length; i++) {
			result[i] = (byte) (arr[i] ^ keyarr[i % keyarr.length]);
		}

		return this.dataEncrypted = new String(result, "UTF-8");
	}

	public void run() {

		String threadName = Thread.currentThread().getName();
		long threadID = Thread.currentThread().getId();
		System.out.println(threadName + " with ID=" + threadID
				+ " is encrypting data");

		while (dataEncrypted == null) {
		}
		try {
			syncQueue.put(this.dataEncrypted);
			dataEncrypted = null;
		} catch (InterruptedException ie) {
		} catch (IllegalMonitorStateException ex) {
		}
	}

	public void stop() {
		this.InputStreamThread = null;
	}
}