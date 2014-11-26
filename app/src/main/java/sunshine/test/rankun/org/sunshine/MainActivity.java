package sunshine.test.rankun.org.sunshine;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
    private static String tag = "Sunshine_MainActivity";
    private TrueFalse[] mQuestionList = new TrueFalse[]{
            new TrueFalse(R.string.q1, true),
            new TrueFalse(R.string.q2, true),
            new TrueFalse(R.string.q3, false),
            new TrueFalse(R.string.q4, true),
            new TrueFalse(R.string.q5, true),
            new TrueFalse(R.string.q6, false),
            new TrueFalse(R.string.q7, false)
    };
    private int mCurrentIndex = 0;
    private MyToast myToast = new MyToast();
    private Button right_btn;
    private Button wrong_btn;
    private ImageButton prev_btn;
    private ImageButton next_btn;
    private TextView question_txt;
    private Button cheat_button;
    private boolean mIsCheater;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(tag, Thread.currentThread().getStackTrace()[3].getMethodName());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        if (null != savedInstanceState) {
            this.mCurrentIndex = savedInstanceState.getInt("mCurrentIndex", 0);
            mQuestionList[mCurrentIndex].setCheated(savedInstanceState.getBoolean("mIsCheater", false));
        }
    }

    @Override
    protected void onResume() { super.onResume(); Log.d(tag, Thread.currentThread().getStackTrace()[3].getMethodName()); }
    @Override
    protected void onStop() { super.onStop(); Log.d(tag, Thread.currentThread().getStackTrace()[3].getMethodName()); }
    @Override
    protected void onPause() { super.onPause(); Log.d(tag, Thread.currentThread().getStackTrace()[3].getMethodName()); }
    @Override
    protected void onDestroy() { super.onDestroy(); Log.d(tag, Thread.currentThread().getStackTrace()[3].getMethodName()); }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(tag, Thread.currentThread().getStackTrace()[3].getMethodName());
        super.onSaveInstanceState(outState);

        outState.putInt("mCurrentIndex", mCurrentIndex);
        outState.putBoolean("mIsCheater", mQuestionList[mCurrentIndex].isCheated());
    }

    @Override
    protected void onStart() {
        Log.d(tag, Thread.currentThread().getStackTrace()[3].getMethodName());

        super.onStart();
        right_btn = (Button) findViewById(R.id.right_rtn);
        wrong_btn = (Button) findViewById(R.id.wrong_rtn);
        prev_btn = (ImageButton) findViewById(R.id.prev_btn);
        next_btn = (ImageButton) findViewById(R.id.next_btn);
        question_txt = (TextView) findViewById(R.id.question_txt_view);
        cheat_button = (Button) findViewById(R.id.cheat_button);


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
        cheat_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CheatActivity.class);
                intent.putExtra(CheatActivity.EXTRA_ANSWER_IS_TRUE, mQuestionList[mCurrentIndex].isTrueQuestion());
                startActivityForResult(intent, 0);
            }
        });
        prev_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeQuestion(false);
            }
        });
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeQuestion(true);
            }
        });
        question_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeQuestion(true);
            }
        });
        loadQ(mCurrentIndex);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(tag, Thread.currentThread().getStackTrace()[3].getMethodName());
        if (data == null) {
            return;
        }

        if (0 == requestCode) {
            super.onActivityResult(requestCode, resultCode, data);
            boolean isAnswerCheated = data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOWN, false);
            mQuestionList[mCurrentIndex].setCheated(isAnswerCheated);
        }

    }

    /**
     * Change to next question.
     */
    private void changeQuestion(boolean loadNext) {
        mIsCheater = false;
        if (loadNext) {
            if (mCurrentIndex >= mQuestionList.length - 1) {
                mCurrentIndex = 0;
                loadQ(mCurrentIndex);
            } else {
                loadQ(++mCurrentIndex);
            }
        } else {
            if (mCurrentIndex == 0) {
                mCurrentIndex = mQuestionList.length - 1;
                loadQ(mCurrentIndex);
            } else {
                loadQ(--mCurrentIndex);
            }
        }
    }

    /**
     * Check if user selected the right answer
     * @param userPressedTrue user pressed true
     */
    private void checkAnswer(boolean userPressedTrue) {
        myToast.cancel();
        TrueFalse mCurrentQuestion = mQuestionList[mCurrentIndex];
        boolean answerIsTrue = mCurrentQuestion.isTrueQuestion();
        if (mCurrentQuestion.isCheated()) {
            if (answerIsTrue == userPressedTrue) {
                myToast.makeToast(R.string.judgement_toast);
            } else {
                myToast.makeToast(R.string.cheat_failed);
            }
        } else {
            if (answerIsTrue == userPressedTrue) {
                myToast.makeToast(R.string.right_text);
            } else {
                myToast.makeToast(R.string.wrong_text);
            }
        }
    }

    /**
     * Load the question with particular index
     * @param pos question index
     */
    private void loadQ(int pos) {
        if (pos < 0 || pos > mQuestionList.length) {
            return;
        }
        question_txt.setText(mQuestionList[pos].getQuestion());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(tag, Thread.currentThread().getStackTrace()[3].getMethodName());

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(tag, Thread.currentThread().getStackTrace()[3].getMethodName());

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
            Log.d(tag, Thread.currentThread().getStackTrace()[3].getMethodName());

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
