package thread.work4;

//Написать класс МФУ, на котором возможны одновременная печать и сканирование документов, при этом нельзя одновременно печатать
// два документа или сканировать (при печати в консоль выводится сообщения "отпечатано 1, 2, 3,… страницы", при сканировании
// тоже самое только "отсканировано…", вывод в консоль все также с периодом в 50 мс.)
public class MFU {
    private int scan;
    private int print;

    public synchronized void print() {
        System.out.println("отпечатано " + print + " страницы");
        print++;
    }

    public synchronized void scan() {
        System.out.println("отсканировано " + scan + " страницы");
        scan++;
    }
}
