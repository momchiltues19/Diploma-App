package mom.todorov.elsys.org.diplomaappv1;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class MenuActivity extends Activity {

    //image in the xml
    ImageView avatarImage;
    //text in the xml
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //taking user data and avatar data
        DBConnect db = new DBConnect(this);
        User myUser = db.getUser("gg@ff");

        //Avatar testAvatar = db.insertAvatar(new Avatar(Long.valueOf(1), "blablablablablalb"));
        Avatar myAvatar = db.getAvatar("gg@ff");

        //init of text
        text = findViewById(R.id.textView1);
        text.setText(myAvatar.imageLink);

        //init of avatar image
        avatarImage = findViewById(R.id.avatarImage);
        avatarImage.setImageResource(R.drawable.my_image);

        //get image from the internet
        new DownloadImageTask((ImageView) findViewById(R.id.avatarImage))
                .execute("https://www.google.co.uk/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png");

        //take image from the gallery
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 0);
    }

    //used for getting image from the internet
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

    //used for putting the image from the gallery to the phone
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Uri targetUri = data.getData();
            text.setText(targetUri.toString());
            Bitmap bitmap;
            try {
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                avatarImage.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
