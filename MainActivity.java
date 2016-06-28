package com.ecemboluk.jsoupuygulama;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button baslik;
    Button aciklama;
    Button yazar;
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text=(TextView)findViewById(R.id.textView2);
        baslik=(Button)findViewById(R.id.baslik);
        baslik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  new Baslik().execute();
            }
        });
        aciklama=(Button)findViewById(R.id.aciklama);
        aciklama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  new Aciklama().execute();
            }
        });
        yazar=(Button) findViewById(R.id.yazar);
        yazar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 new Yazar().execute();
            }
        });
    }
    private class Baslik extends AsyncTask<Void,Void,Void>
    {
        String URL="http://ecemboluk.blogspot.com.tr";
        String baslik;
        ProgressDialog dialog;
        @Override
        protected  void onPreExecute()
        {
            super.onPreExecute();
            dialog=new ProgressDialog(MainActivity.this);
            dialog.setTitle("Jsoup Uygulama.");
            dialog.setMessage("Veri getiriliyor");
            dialog.setIndeterminate(false);
            dialog.show();
        }
        @Override
        protected Void doInBackground(Void... params) {
            try {
                Document doc= Jsoup.connect(URL).get();
                baslik=doc.title();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void avoid)
        {
            text.setText(baslik);
            dialog.dismiss();
        }
    }
    private class Aciklama extends AsyncTask<Void,Void,Void>
    {
        String URL="http://ecemboluk.blogspot.com.tr/2015/06/c-ve-net-ortam-nedir-ne-degildir.html";
        String aciklama;
        String veri;
        ProgressDialog dialog;
        @Override
        protected  void onPreExecute()
        {
            super.onPreExecute();
            dialog=new ProgressDialog(MainActivity.this);
            dialog.setTitle("Jsoup Uygulama.");
            dialog.setMessage("Veri getiriliyor");
            dialog.setIndeterminate(false);
            dialog.show();
        }
        @Override
        protected Void doInBackground(Void... params) {
            try {
                Document doc= Jsoup.connect(URL).get();
                Elements elements=doc.select("div[class=post-entry inside]");
                elements.select("p");
                elements.select("img").remove();
                veri=elements.html();//istenilen html taglarını çeker.
                aciklama=Jsoup.parse(veri).text();//html taglarını texte çevirir.
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void avoid)
        {
            text.setText(aciklama);
            dialog.dismiss();
        }
    }
    private class Yazar extends AsyncTask<Void,Void,Void>
    {
        String URL="http://ecemboluk.blogspot.com.tr/2015/06/c-ve-net-ortam-nedir-ne-degildir.html";
        String yazar;
        String veri;
        ProgressDialog dialog;
        @Override
        protected  void onPreExecute()
        {
            super.onPreExecute();
            dialog=new ProgressDialog(MainActivity.this);
            dialog.setTitle("Jsoup Uygulama.");
            dialog.setMessage("Veri getiriliyor");
            dialog.setIndeterminate(false);
            dialog.show();
        }
        @Override
        protected Void doInBackground(Void... params) {
            try {
                Document doc= Jsoup.connect(URL).get();
                Elements elements=doc.select("div[class=author-content]");
                elements.select("h5");
                veri=elements.html();//istenilen html taglarını çeker.
                yazar=Jsoup.parse(veri).text();//html taglarını texte çevirir.
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void avoid)
        {
            text.setText(yazar);
            dialog.dismiss();
        }
    }
    /*private class Resim extends AsyncTask<Void,Void,Void>
    {
        String URL="http://ecemboluk.blogspot.com.tr/2015/06/c-ve-net-ortam-nedir-ne-degildir.html";
        ProgressDialog dialog;
        Bitmap bitmap;
        @Override
        protected  void onPreExecute()
        {
            super.onPreExecute();
            dialog=new ProgressDialog(MainActivity.this);
            dialog.setTitle("Jsoup Uygulama.");
            dialog.setMessage("Veri getiriliyor");
            dialog.setIndeterminate(false);
            dialog.show();
        }
        @Override
        protected Void doInBackground(Void... params) {
            try {
                Document doc= Jsoup.connect(URL).get();
                Elements elements=doc.select("div[class=post-entry inside]");
                elements.select("img[src]");
                String imgSrc=elements.attr("src");
                InputStream input=new java.net.URL(imgSrc).openStream();
                bitmap= BitmapFactory.decodeStream(input);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void avoid)
        {
            ImageView img=(ImageView) findViewById(R.id.imageView);
            img.setImageBitmap(bitmap);
            dialog.dismiss();
        }
    }*/


}
