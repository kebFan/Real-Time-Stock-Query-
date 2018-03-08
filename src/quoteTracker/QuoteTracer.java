/*
 * A deskto application that shows the stock of popular campanies realtime
 *
 *  By Keb T. F.
 * */
package quoteTracker;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class QuoteTracer extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    // create text boxes
    JLabel labelOne = new JLabel();
    JLabel labelTwo = new JLabel();
    JLabel labelThree = new JLabel();
    JLabel labelFour = new JLabel();
    JLabel labelTopBar = new JLabel();
    // backgroud image
    //    ImageIcon background = new ImageIcon("/stockBackground.jpg");
    //    JLabel bMap = new JLabel(this.background);

    public QuoteTracer() {
        super("Stock Tracker - By Keb");

        this.setSize(1100, 1000);
        // this.setContentPane(this.bMap);
        this.labelTopBar.setOpaque(true);
        this.setLookAndFeel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GridLayout gridLayout = new GridLayout(0, 1);
        this.setLayout(gridLayout);

        this.setVisible(true);

        // set boarders
        //pane.setBorder(BorderFactory.createLineBorder(Color.black));

        this.labelFour
                .setBorder(BorderFactory.createLineBorder(Color.yellow, 15));
        this.labelThree
                .setBorder(BorderFactory.createLineBorder(Color.GREEN, 15));
        this.labelTwo.setBorder(BorderFactory.createLineBorder(Color.blue, 15));
        this.labelOne.setBorder(BorderFactory.createLineBorder(Color.red, 15));
        this.labelTopBar
                .setBorder(BorderFactory.createLineBorder(Color.black, 15));
        this.labelTopBar
                .setBorder(BorderFactory.createLineBorder(Color.black, 35));
        this.labelTopBar.setForeground(Color.white);
        this.labelTopBar.setBackground(Color.black);

        this.add(this.labelTopBar);
        this.add(this.labelOne);
        this.add(this.labelTwo);
        this.add(this.labelThree);
        this.add(this.labelFour);

        // set up top bar
        this.labelTopBar.setFont(new Font("Helvetica", Font.BOLD, 34));
        this.labelTopBar.setText("    TITLE" + "                            "
                + "PRICE" + "                            " + "CHANGE   ");
    }

    // needed for to customize our frame
    private void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(
                    "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception exc) {
            // do nothing
        }

    }

    /*
     * given the url 1) get the websites info in html format 2) take stock names
     * and their values
     */

    public void stockName() {

        List<String> title = new ArrayList();
        List<String> stockPrice = new ArrayList();
        List<String> stockChange = new ArrayList();

        try {
            // TODO code application logic here
            Document document = Jsoup
                    .connect("http://money.cnn.com/data/markets/").get();
            //System.out.println(document.toString());
            Elements spanElements = document.getElementsByTag("span");

            for (Element element : spanElements) {

                switch (element.className()) {
                    case "column stock-name":
                        title.add(element.text());
                        break;
                    case "column stock-price":
                        stockPrice.add(element.text());
                        break;
                    case "column stock-change":
                        stockChange.add(element.text());
                        break;

                }

            }

        } catch (IOException ex) {
            System.out.println(ex.toString());
        }

        Iterator<String> iTitle = title.iterator();
        while (iTitle.hasNext()) {
            System.out.println(iTitle.next());
        }

        // assign the label with stock name  then
        // update UI
        for (int x = 0; x < title.size(); x++) {

            if (title.get(x).equals("Apple")) {

                this.labelOne.setText("  " + title.get(x)
                        + "                            " + stockPrice.get(x)
                        + "                            " + stockChange.get(x));
                this.labelOne.setFont(new Font("Serif", Font.BOLD, 34));

            } else if (title.get(x).equals("General Electric")) {
                this.labelTwo.setText("  " + title.get(x) + "            "
                        + stockPrice.get(x) + "                            "
                        + stockChange.get(x));
                this.labelTwo.setFont(new Font("Serif", Font.BOLD, 34));
            } else if (title.get(x).equals("Google")) {
                this.labelThree.setText("  " + title.get(x)
                        + "                            " + stockPrice.get(x)
                        + "                            " + stockChange.get(x));
                this.labelThree.setFont(new Font("Serif", Font.BOLD, 34));
            } else if (title.get(x).equals("Microsoft")) {
                this.labelFour.setText("  " + title.get(x)
                        + "                        " + stockPrice.get(x)
                        + "                            " + stockChange.get(x));
                this.labelFour.setFont(new Font("Serif", Font.BOLD, 34));
            } else {
                // do nothing
            }
        }

    }

    public static void main(String[] args) {
        QuoteTracer stock = new QuoteTracer();

        // looks for stock names and their values then  format them to
        // be displayed clean
        stock.stockName();

    }
}

/*
 * Links of websites used to make this project
 *
 * https://stackoverflow.com/questions/4308554/simplest-way-to-read-json-from-a-
 * url-in-java
 *
 * https://stackoverflow.com/questions/9728854/jsoup-to-get-text-from-span-class
 */
