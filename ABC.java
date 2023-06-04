package thread.work4;
// 1 уровень сложности: 1. Создать три потока, каждый из которых выводит определенную букву (A, B и C) 5 раз,
// порядок должен быть именно ABСABСABС. Используйте wait/notify/notifyAll.
public class ABC {
    private static final Object lock = new Object();
    private static volatile int count = 0;

    public static void main(String[] args) {
        Thread threadA = new Thread(new PrintLetter('A', 0));
        Thread threadB = new Thread(new PrintLetter('B', 1));
        Thread threadC = new Thread(new PrintLetter('C', 2));

        threadA.start();
        threadB.start();
        threadC.start();
    }

    private static class PrintLetter implements Runnable {
        private char letter;
        private int order;

        public PrintLetter(char letter, int order) {
            this.letter = letter;
            this.order = order;
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                synchronized (lock) {
                    while (count % 3 != order) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print(letter);
                    count++;
                    lock.notifyAll();
                }
            }
        }
    }
}