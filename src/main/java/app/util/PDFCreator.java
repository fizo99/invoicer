package main.java.app.util;

import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import main.java.app.models.Buyer;
import main.java.app.models.Car;
import main.java.app.models.Invoice;


import java.io.IOException;
import java.time.LocalDate;


public class PDFCreator {
    private final String fileName;
    private final Invoice invoice;

    public PDFCreator(String fileName, Invoice invoice){
        this.fileName = fileName;
        this.invoice = invoice;
    }

    public void save() {
        Document document = new Document("template.docx");

        //TODO
        //GET user data and replace in doc

        document.replace("#TYPE", invoice.getInvoiceType().toString(), false, true);
        document.replace("#NUMBER", invoice.getInvoiceNumber(), false, true);

        document.replace("#PLACE", invoice.getPlace(), false, true);
        document.replace("#DATE", invoice.getDate().toString(), false, true);

        Buyer buyer = invoice.getBuyer();
        document.replace("#buyerFullName", buyer.getFullName(), false, true);
        document.replace("#buyerZIPCode", buyer.getZIPCode(), false, true);
        document.replace("#buyerTown", buyer.getTown(), false, true);
        document.replace("#buyerStreet", buyer.getStreet(), false, true);
        document.replace("#buyerStreetNumber", buyer.getStreetNumber(), false, true);
        if(buyer.getNIP() != null){
            document.replace("#buyerNIP", "NIP " + buyer.getNIP(), false, true);
        }

        Car car = invoice.getCar();
        document.replace("#nettoPrice", car.getNettoPrice().toString(), false, true);
        document.replace("#carBrand", car.getBrand(), false, true);
        document.replace("#carModel", car.getModel(), false, true);
        document.replace("#carYear", car.getYear().toString(), false, true);
        document.replace("#carVIN", car.getVIN(), false, true);

        document.replace("#nettoPrice", car.getNettoPrice().toString(), false, true);
        document.replace("#vatPercent", new Float(car.getVatPercent() * 100.0f).toString(), false, true);
        document.replace("#vatAmount", car.getVatAmount().toString(), false, true);
        document.replace("#bruttoPrice", car.getBruttoPrice().toString(), false, true);

        document.replace("#paymentMethod", invoice.getPaymentMethod().toString(), false, true);
        document.replace("#additionalInformation", invoice.getAdditionalInformation(), false, true);

        document.replace("#numberToWords", numberToWords(car.getBruttoPrice().longValue()), false, true);

        document.saveToFile("output.docx", FileFormat.Docx_2013);

    }


    public static void main(String[] args){
        System.out.println(numberToWords(23133));

//        Car car = new Car.Builder()
//                .VIN("VIN")
//                .bruttoPrice(23133.0f)
//                .vatPercent(0.23f)
//                .description("opis")
//                .nettoPrice(2137.0f)
//                .vatAmount(2137.0f)
//                .build();

//        Buyer buyer = new Buyer();
//        buyer.setFullAddress("pelnyadres");
//        buyer.setFullName("pelnanazwa");
//        buyer.setNIP("nipkupcy");
//
//        Invoice invoice = new Invoice();
//        invoice.setDate(LocalDate.now());
//        invoice.setInvoiceNumber(2);
//        invoice.setInvoiceType(Invoice.Type.VAT);
//        invoice.setBuyer(buyer);
//        invoice.setCar(car);

        //PDFCreator creator = new PDFCreator("test.pdf",invoice);

        try{
            //creator.save();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }
    //Zamiana liczby na slowa z polska gramatyka
    //Krzysztof Kranc
    //www.algorytm.org
    public static String numberToWords(long liczba) {
        String[] jedności = { "", "jeden ", "dwa ", "trzy ", "cztery ",
                "pięć ", "sześć ", "siedem ", "osiem ", "dziewięć ", };

        String[] nastki = { "", "jedenaście ", "dwanaście ", "trzynaście ",
                "czternaście ", "piętnaście ", "szesnaście ", "siedemnaście ",
                "osiemnaście ", "dziewiętnaście ", };

        String[] dziesiątki = { "", "dziesięć ", "dwadzieścia ",
                "trzydzieści ", "czterdzieści ", "pięćdziesiąt ",
                "sześćdziesiąt ", "siedemdziesiąt ", "osiemdziesiąt ",
                "dziewięćdziesiąt ", };

        String[] setki = { "", "sto ", "dwieście ", "trzysta ", "czterysta ",
                "pięćset ", "sześćset ", "siedemset ", "osiemset ",
                "dziewięćset ", };

        String[][] grupy = { { "", "", "" },
                { "tysiąc ", "tysiące ", "tysięcy " },
                { "milion ", "miliony ", "milionów " },
                { "miliard ", "miliardy ", "miliardów " },
                { "bilion ", "biliony ", "bilionów " },
                { "biliard ", "biliardy ", "biliardów " },
                { "trylion ", "tryliony ", "trylionów " }, };

        long j = 0/* jedności */, n = 0/* nastki */, d = 0/* dziesiątki */, s = 0/* setki */, g = 0/* grupy */, k = 0/* końcówwki */;
        String słownie = "";
        String znak = "";



        if (liczba < 0) {
            znak = "minus ";
            liczba = -liczba;
        }
        if (liczba == 0) {
            znak = "zero";
        }

        while (liczba != 0) {
            s = liczba % 1000 / 100;
            d = liczba % 100 / 10;
            j = liczba % 10;

            if (d == 1 & j > 0)
            {
                n = j;
                d = 0;
                j = 0;
            } else {
                n = 0;
            }


            if (j == 1 & s + d + n == 0) {
                k = 0;

                if (s + d == 0 && g > 0)
                {
                    j = 0;
                    słownie = grupy[(int) g][(int) k] + słownie;
                }
            } else if (j == 2) {
                k = 1;
            } else if (j == 3) {
                k = 1;
            } else if (j == 4) {
                k = 1;
            } else {
                k = 2;
            }


            if (s+d+n+j > 0) {
                słownie = setki[(int) s] + dziesiątki[(int) d] + nastki[(int) n]
                        + jedności[(int) j] + grupy[(int) g][(int) k] + słownie;
            }

            liczba = liczba / 1000;
            g = g + 1;
        }

        słownie = znak + słownie;
        return słownie;

    }
}
