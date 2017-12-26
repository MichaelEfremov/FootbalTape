package android.mike.ru.footbaltape;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final String URL = "https://www.sport-express.ru/football/news/";
    private RecyclerView mRecyclerView;
    private ArrayList<Content> contents = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Fetcher().execute();
        mRecyclerView = (RecyclerView) findViewById(R.id.rvContent);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));


    }

    public class Fetcher extends AsyncTask<Void, ArrayList<Content>, ArrayList<Content>> {


        @Override
        protected void onProgressUpdate(ArrayList<Content>[] values) {
            super.onProgressUpdate(values);
            DataAdapter dataAdapter = new DataAdapter(MainActivity.this, contents);
            mRecyclerView.setAdapter(dataAdapter);
            dataAdapter.setViewListener(new ViewListener() {
                @Override
                public void onClick(int position) {
                    Intent intent = new Intent(MainActivity.this, ContentActivity.class);
                    intent.putExtra("description", contents.get(position).getDescription());
                    startActivity(intent);
                }
            });
        }

        @Override
        protected ArrayList<Content> doInBackground(Void... voids) {
            try {
                Document document = Jsoup.connect(URL).get();
                Elements elements = document.select(".fs_20.mb_10.block.black");
                for (Element element : elements) {
                    contents.add(new Content(element.text(), getDescription(element.absUrl("href")), element.absUrl("href")));
                    publishProgress(contents);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return contents;
        }

        private String getDescription(String url) {
            String description = "";
            try {
                Document document = Jsoup.connect(url).get();
                Element element = document.selectFirst(".article_text.publication.blackcolor.mt_30.mb_15.js-mediator-article");
                description = element.text();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return description;
        }
    }

}
