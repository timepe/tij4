package chapter13.string;

import java.util.Formatter;

public class Receipt {
    private double total = 0;
    private int itemTextWidth = 19;
    /*
     * System.out.printf()等价于
     * Formmater f = new Formatter(System.out);
     * f.format()
     */
    private Formatter f = new Formatter(System.out);

    public void printTitle() {
        String formatCtrl = "%-" + itemTextWidth + "s %5s %10s\n";
        f.format(formatCtrl, "Item", "Qry", "Price");
        f.format(formatCtrl, "----", "----", "----");
    }

    /*
    * 一般第一列选择左对齐， 最后一列选择右对齐
    *  中间的列需要看排版，一般右对齐
    * */
    public void print(String name, int qry, double price) {
        String formatCtrl = "%-" + itemTextWidth + "." + itemTextWidth + "s %5s %10.2f\n";
        f.format(formatCtrl, name, qry, price);
        total += price;
    }

    public void printTotal() {
        String formatCtrl1 = "%-" + itemTextWidth + "." + itemTextWidth + "s %5s %10.2f\n";
        String formatCtrl2 = "%-" + itemTextWidth + "." + itemTextWidth + "s %5s %10s\n";
        f.format(formatCtrl1, "Tax", "", total*0.06);
        f.format(formatCtrl2, "", "", "-----");
        f.format(formatCtrl1, "Total", "", total * 1.06);
    }

    public static void main(String[] args) {
        Receipt receipt = new Receipt();
        receipt.printTitle();
        receipt.print("Jack's Magic Beans", 4, 4.25);
        receipt.print("Princess peas", 1, 14.29);
        receipt.print("Three Bears Porridge", 1, 14.29);
        receipt.printTotal();
    }
}
