package com.example.itexttesting04;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try{
            createPDF();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    private void createPDF()throws FileNotFoundException {
        String pdfPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        File file = new File(pdfPath,"MyInvoicePdf.pdf");
        OutputStream stream = new FileOutputStream(file);

        PdfWriter writer = new PdfWriter(file);
        PdfDocument pdfDocument = new PdfDocument(writer);
        Document document = new Document(pdfDocument);

        document.setMargins(0,0,0,0);
        Drawable drawable = getDrawable(R.drawable.tourist);
        Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
        ByteArrayOutputStream stream1 = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream1);
        byte[] bytes = stream1.toByteArray();

        ImageData imageData = ImageDataFactory.create(bytes);
        Image image = new Image(imageData);

        Drawable drawable1 = getDrawable(R.drawable.icon);
        Bitmap bitmap1 = ((BitmapDrawable)drawable1).getBitmap();
        ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
        bitmap1.compress(Bitmap.CompressFormat.PNG,100,stream2);
        byte[] bytes1 = stream2.toByteArray();

        ImageData imageData1 = ImageDataFactory.create(bytes1);
        Image image1 = new Image(imageData1);


        float colmnWidth[] = {112,112,112,112,112};
        Table table = new Table(colmnWidth);

        //Row 01
        table.addCell(new Cell(1,2).add(new Paragraph("Easy Pay Solution").setFontSize(20f).setBold().setFontColor(new DeviceRgb(66,133,125))).setBorder(Border.NO_BORDER));
        // table.addCell(new Cell().add(new Paragraph("")));
        table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));

        //Row 02
        table.addCell(new Cell().add(new Paragraph("123 yor Street\n"+"City,state,Country\n"+"Zip code\n").setBold()).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph("123-123-1234\n"+"youremail1@gmail.com\n"+"youremail2@gmail.com\n").setBold()).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));

        //Row 03
        table.addCell(new Cell().add(new Paragraph("\n")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));

        //Row 04
        Text text = new Text("BILLED TO\n");
        text.setBold();
        text.setFontColor(new DeviceRgb(18,192,33));
        Paragraph paragraph = new Paragraph();
        paragraph.add(text);
        paragraph.add("Client Name\n"+"Street address\n"+"City,state,Country\n"+"Zip code\n");
        table.addCell(new Cell().add(paragraph).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));

        //Row 05
        table.addCell(new Cell(2,1).add(new Paragraph("INVOICE").setBold().setFontColor(new DeviceRgb(18,192,33)).setFontSize(24f)).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));

        //Row 06
        //table.addCell(new Cell().add(new Paragraph("")));
        table.addCell(new Cell().add(new Paragraph("DESCRIPTION").setFontColor(ColorConstants.WHITE).setBackgroundColor(new DeviceRgb(66,133,244))));
        table.addCell(new Cell().add(new Paragraph("UNIT COST(INR)").setFontColor(ColorConstants.WHITE).setBackgroundColor(new DeviceRgb(66,133,244))));
        table.addCell(new Cell().add(new Paragraph("QTY").setFontColor(ColorConstants.WHITE).setBackgroundColor(new DeviceRgb(66,133,244))));
        table.addCell(new Cell().add(new Paragraph("AMOUNT(INR)").setFontColor(ColorConstants.WHITE).setBackgroundColor(new DeviceRgb(66,133,244))));

        //Row 07
        Text text1 = new Text("INVOICE NUMBER\n");
        text1.setBold().setFontColor(new DeviceRgb(18,192,33));
        Paragraph paragraph1 = new Paragraph();
        paragraph1.add(text1);
        paragraph1.add("0001\n");

        table.addCell(new Cell(2,1).add(paragraph1).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph("Item Name")).setBackgroundColor(new DeviceRgb(220,220,220)));
        table.addCell(new Cell().add(new Paragraph("100")).setBackgroundColor(new DeviceRgb(220,220,220)));
        table.addCell(new Cell().add(new Paragraph("1")).setBackgroundColor(new DeviceRgb(220,220,220)));
        table.addCell(new Cell().add(new Paragraph("100")).setBackgroundColor(new DeviceRgb(220,220,220)));

        //Row 08
        // table.addCell(new Cell().add(new Paragraph("")));
        table.addCell(new Cell().add(new Paragraph("Item Name1")).setBackgroundColor(new DeviceRgb(220,220,220)));
        table.addCell(new Cell().add(new Paragraph("100")).setBackgroundColor(new DeviceRgb(220,220,220)));
        table.addCell(new Cell().add(new Paragraph("1")).setBackgroundColor(new DeviceRgb(220,220,220)));
        table.addCell(new Cell().add(new Paragraph("100")).setBackgroundColor(new DeviceRgb(220,220,220)));

        //Row 09
        Text text2 = new Text("Date of issue\n");
        text2.setBold().setFontColor(new DeviceRgb(18,192,33));
        Paragraph paragraph2 = new Paragraph();
        paragraph2.add(text2);
        paragraph2.add("14-04-2023\n");
        table.addCell(new Cell(2,1).add(paragraph2));
        table.addCell(new Cell().add(new Paragraph("Item Name1")).setBackgroundColor(new DeviceRgb(220,220,220)));
        table.addCell(new Cell().add(new Paragraph("100")).setBackgroundColor(new DeviceRgb(220,220,220)));
        table.addCell(new Cell().add(new Paragraph("1")).setBackgroundColor(new DeviceRgb(220,220,220)));
        table.addCell(new Cell().add(new Paragraph("100")).setBackgroundColor(new DeviceRgb(220,220,220)));

        //Row 10
        //table.addCell(new Cell().add(new Paragraph("")));
        table.addCell(new Cell().add(new Paragraph("Item Name1")).setBackgroundColor(new DeviceRgb(220,220,220)));
        table.addCell(new Cell().add(new Paragraph("100")).setBackgroundColor(new DeviceRgb(220,220,220)));
        table.addCell(new Cell().add(new Paragraph("1")).setBackgroundColor(new DeviceRgb(220,220,220)));
        table.addCell(new Cell().add(new Paragraph("100")).setBackgroundColor(new DeviceRgb(220,220,220)));

        //Row 011
        table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));

        //Row 012
        table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph("Sub-Total:")).setBold().setFontColor(new DeviceRgb(18,192,33)));
        table.addCell(new Cell().add(new Paragraph("400")));

        //Row 013
        table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph("Discount:")).setBold().setFontColor(new DeviceRgb(18,192,33)));
        table.addCell(new Cell().add(new Paragraph("0")));

        //Row 014
        table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph("(TAX Rate)")).setBold().setFontColor(new DeviceRgb(18,192,33)));
        table.addCell(new Cell().add(new Paragraph("10%")));

        //Row 015
        table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph("TAX :")).setBold().setFontColor(new DeviceRgb(18,192,33)));
        table.addCell(new Cell().add(new Paragraph("40")));

        //Row 016
        table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(1,2).add(new Paragraph("=+=+=+=+=+=+=+=+=+=+=+=+=").setTextAlignment(TextAlignment.CENTER)).setBorder(Border.NO_BORDER));
        //table.addCell(new Cell().add(new Paragraph("")));

        //Row 017
        table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(1,2).add(new Paragraph("INVOICE TOTAL\n"+"440\n").setTextAlignment(TextAlignment.CENTER)).setBold().setFontColor(new DeviceRgb(18,192,33)));
        // table.addCell(new Cell().add(new Paragraph("")));

        //Row 018
        table.addCell(new Cell(1,2).add(new Paragraph("Term\n"+"E.g. Please pay invoice by 14-03-2023\n")).setBorder(Border.NO_BORDER));
        //table.addCell(new Cell().add(new Paragraph("")));
        table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));








        document.add(image.setFixedPosition(0,0));
        document.add(image1);

        document.add(table);
        document.add(new Paragraph("\n\n\n\n(Authorised Signatory)\n\n"));
        document.close();
        Toast.makeText(this, "pdf created", Toast.LENGTH_SHORT).show();
    }

}