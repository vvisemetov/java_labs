package concurrency;

import java.util.concurrent.SynchronousQueue;

public class OutputStream implements Runnable {

	private SynchronousQueue<String> syncQueue;
	private Thread OutPutStreamThread;

	public OutputStream(SynchronousQueue<String> syncQueue)
			throws InterruptedException {
		this.syncQueue = syncQueue;
		OutPutStreamThread = new Thread(this, "OutputStream Thread");
		OutPutStreamThread.start();
	}

	public void run() {

		Thread currentThread = Thread.currentThread();
		String threadName = Thread.currentThread().getName();
		long threadID = Thread.currentThread().getId();

		try {
			while (OutPutStreamThread == currentThread) {
				System.out.println(threadName + " with ID=" + threadID
						+ " is taking data: " + syncQueue.take() + "\n");
			}
		} catch (InterruptedException ie) {
		}
	}

	public void stop() {
		this.OutPutStreamThread = null;
	}
}