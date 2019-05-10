package mom.todorov.elsys.org.diplomaappv1;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        DBConnect db = new DBConnect(this);
        User myUser = db.getUser("gg@ff");
        //Avatar testAvatar = db.insertAvatar(new Avatar(Long.valueOf(1), "blablablablablalb"));
        Avatar myAvatar = db.getAvatar("gg@ff");
        TextView text = findViewById(R.id.textView1);
        text.setText(myAvatar.imageLink);
    }
}
