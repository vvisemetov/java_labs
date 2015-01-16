package concurrency;

import java.util.concurrent.SynchronousQueue;
import java.io.UnsupportedEncodingException;

public class Main {
	public static void main(String[] args) throws InterruptedException,
			UnsupportedEncodingException {

		SynchronousQueue<String> SyncQueue = new SynchronousQueue<String>();

		String[] data = { "hellohellohi", "sd6dsdg4tgsd", "14 15 16 23" };
		String[] key = { "34534234242as34", "ada123dfsdf", "#%SDSW$Wfsdf" };
		int numberOfDataToCrypt;

		if (data.length == key.length) {
			numberOfDataToCrypt = data.length;
		} else {
			throw new IllegalArgumentException(
					"Mismatched number of keys and data");
		}

		for (int i = 0; i < numberOfDataToCrypt; i++) {
			InputStream InputStream = new InputStream(SyncQueue, data[i],
					key[i]);
			OutputStream OutputStream = new OutputStream(SyncQueue);

			try {
				Thread.sleep(700);
			} catch (InterruptedException ie) {
			}

			InputStream.stop();
			OutputStream.stop();
		}

	}
}