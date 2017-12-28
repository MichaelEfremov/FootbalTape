package android.mike.ru.footbaltape;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.IOException;

public class ContentActivity extends AppCompatActivity {

    private Content content;
    private TextView tvDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        tvDescription = (TextView) findViewById(R.id.tvDescription);

        Intent intent = getIntent();
        content = intent.getParcelableExtra("description");

        new Fetcher().execute();

    }

    public class Fetcher extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            tvDescription.setText(content.getDescription());

        }

        private void getDescription(String url) {
            String description = "";
            try {
                Document document = Jsoup.connect(url).get();
                Element element = document.selectFirst(".article_text.publication.blackcolor.mt_30.mb_15.js-mediator-article");
                description = element.text();
                content.setDescription(description);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected Void doInBackground(Void... voids) {
            getDescription(content.getUrl());
            return null;
        }
    }

}
