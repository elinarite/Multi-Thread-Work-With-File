package thread.work4;

public class MFUThread extends Thread {
    private MFU mfu;

    public MFUThread(MFU mfu) {
        this.mfu = mfu;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                mfu.print();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                mfu.scan();
            }
        }
    }
}
class Main{
    public static void main(String[] args) {
        MFU mfu = new MFU();
        MFUThread thread = new MFUThread(mfu);
        thread.start();

    }
}
