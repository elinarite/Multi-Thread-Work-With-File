package thread.work4;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

// Написать совсем небольшой метод, в котором 3 потока построчно
// пишут данные в файл (штук по 10 записей, с периодом в 20 мс)
public class FileWriterExample {

    private static final Object lock = new Object();
    private static volatile int count = 0;

    public static void main(String[] args) {
        Thread thread1 = new Thread(new FileWrite(0));
        Thread thread2 = new Thread(new FileWrite(1));
        Thread thread3 = new Thread(new FileWrite(2));

        thread1.start();
        thread2.start();
        thread3.start();
    }

    private static class FileWrite implements Runnable {
        private int order;

        public FileWrite(int order) {
            this.order = order;
        }

        public void run() {
            for (int i = 0; i < 10; i++) {
                synchronized (lock) {
                    while (count % 3 != order) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    writing();
                    count++;
                    lock.notifyAll();
                }
            }
        }

        public  void writing() {
            String line = "line " + count + " " + Thread.currentThread().getName();
            String separator = File.separator;
            String outputFile = "src" + separator + "main" + separator + "java" + separator + "thread" + separator + "work4" + separator + "FileWriterExampleFile";
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile, true))) {

                bufferedWriter.write(line);
                Thread.sleep(100);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}