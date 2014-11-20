package sunshine.test.rankun.org.sunshine;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
    private static String tag = "Sunshine_MainActivity";
    private TrueFalse[] questionList = new TrueFalse[]{
            new TrueFalse(R.string.q1, true),
            new TrueFalse(R.string.q2, true),
            new TrueFalse(R.string.q3, false),
            new TrueFalse(R.string.q4, true),
            new TrueFalse(R.string.q5, true),
            new TrueFalse(R.string.q6, false),
            new TrueFalse(R.string.q7, false)
    };
    private static int questionPos = 0;
    private MyToast myToast = new MyToast();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Button right_btn = (Button) findViewById(R.id.right_rtn);
        Button wrong_btn = (Button) findViewById(R.id.wrong_rtn);
        Button next_btn = (Button) findViewById(R.id.next_btn);

        right_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(tag, "True button clicked");
                checkAnswer(true);
            }
        });
        wrong_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(tag, "False button clicked");
                checkAnswer(false);
            }
        });
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (questionPos >= questionList.length - 1) {
                    questionPos = 0;
                    loadQ(questionPos);
                } else {
                    loadQ(++questionPos);
                }
            }
        });
        loadQ(0);
    }

    private void checkAnswer(boolean userPressedTrue) {
        myToast.cancel();
        if (questionList[questionPos].isTrueQuestion() == userPressedTrue) {
            myToast.makeToast(R.string.right_text);
        } else {
            myToast.makeToast(R.string.wrong_text);
        }
    }

    private void loadQ(int pos) {
        TextView question_txt = (TextView) findViewById(R.id.question_txt_view);
        question_txt.setText(questionList[pos].getQuestion());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

    }
    public class MyToast {
        private Toast toast;
        public void makeToast(int msg) {
            toast = Toast.makeText(
                    MainActivity.this, msg, Toast.LENGTH_SHORT);
            toast.show();
        }

        public void cancel() {
            if (null != toast) {
                toast.cancel();
            }
        }
    }

}
