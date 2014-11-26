package sunshine.test.rankun.org.sunshine;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by rankun203 on 11/25/14.
 */
public class CheatActivity extends Activity {
    private static String tag = "Sunshine_CheatActivity";
    public static final String EXTRA_ANSWER_IS_TRUE = "test.org.sunshine.answer_is_true";
    public static final String EXTRA_ANSWER_SHOWN = "test.org.sunshine.answer_shown";
    private TextView mAnswerTextView;
    private Button mShowAnswerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(tag, Thread.currentThread().getStackTrace()[3].getMethodName());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        final boolean isTrueQuestion = getIntent().getBooleanExtra(this.EXTRA_ANSWER_IS_TRUE, false);
        Log.d(tag, isTrueQuestion ? "isTrueQuestion" : "isFalseQuestion");

        mAnswerTextView = (TextView) findViewById(R.id.answerTextView);
        mShowAnswerButton = (Button) findViewById(R.id.showAnswerButton);

        setAnswerShownResult(false);

        mShowAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isTrueQuestion) {
                    mAnswerTextView.setText(R.string.right_text);
                } else {
                    mAnswerTextView.setText(R.string.wrong_text);
                }
                setAnswerShownResult(true);
            }
        });

    }

    private void setAnswerShownResult(boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(this.EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d(tag, Thread.currentThread().getStackTrace()[3].getMethodName());
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        Log.d(tag, Thread.currentThread().getStackTrace()[3].getMethodName());
        super.onPostCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        Log.d(tag, Thread.currentThread().getStackTrace()[3].getMethodName());
        super.onStart();
    }

    @Override
    protected void onRestart() {
        Log.d(tag, Thread.currentThread().getStackTrace()[3].getMethodName());
        super.onRestart();
    }

    @Override
    protected void onResume() {
        Log.d(tag, Thread.currentThread().getStackTrace()[3].getMethodName());
        super.onResume();
    }

    @Override
    protected void onPostResume() {
        Log.d(tag, Thread.currentThread().getStackTrace()[3].getMethodName());
        super.onPostResume();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.d(tag, Thread.currentThread().getStackTrace()[3].getMethodName());
        super.onNewIntent(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(tag, Thread.currentThread().getStackTrace()[3].getMethodName());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onPause() {
        Log.d(tag, Thread.currentThread().getStackTrace()[3].getMethodName());
        super.onPause();
    }

    @Override
    protected void onUserLeaveHint() {
        Log.d(tag, Thread.currentThread().getStackTrace()[3].getMethodName());
        super.onUserLeaveHint();
    }

    @Override
    public boolean onCreateThumbnail(Bitmap outBitmap, Canvas canvas) {
        Log.d(tag, Thread.currentThread().getStackTrace()[3].getMethodName());
        return super.onCreateThumbnail(outBitmap, canvas);
    }

    @Override
    public CharSequence onCreateDescription() {
        Log.d(tag, Thread.currentThread().getStackTrace()[3].getMethodName());
        return super.onCreateDescription();
    }

    @Override
    protected void onStop() {
        Log.d(tag, Thread.currentThread().getStackTrace()[3].getMethodName());
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(tag, Thread.currentThread().getStackTrace()[3].getMethodName());
        super.onDestroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Log.d(tag, Thread.currentThread().getStackTrace()[3].getMethodName());
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onLowMemory() {
        Log.d(tag, Thread.currentThread().getStackTrace()[3].getMethodName());
        super.onLowMemory();
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        Log.d(tag, Thread.currentThread().getStackTrace()[3].getMethodName());
        super.onAttachFragment(fragment);
    }

    @Override
    public View onCreatePanelView(int featureId) {
        Log.d(tag, Thread.currentThread().getStackTrace()[3].getMethodName());
        return super.onCreatePanelView(featureId);
    }

    @Override
    public boolean onCreatePanelMenu(int featureId, Menu menu) {
        Log.d(tag, Thread.currentThread().getStackTrace()[3].getMethodName());
        return super.onCreatePanelMenu(featureId, menu);
    }

    @Override
    public boolean onPreparePanel(int featureId, View view, Menu menu) {
        Log.d(tag, Thread.currentThread().getStackTrace()[3].getMethodName());
        return super.onPreparePanel(featureId, view, menu);
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        Log.d(tag, Thread.currentThread().getStackTrace()[3].getMethodName());
        return super.onMenuOpened(featureId, menu);
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        Log.d(tag, Thread.currentThread().getStackTrace()[3].getMethodName());
        return super.onMenuItemSelected(featureId, item);
    }

    @Override
    public void onPanelClosed(int featureId, Menu menu) {
        Log.d(tag, Thread.currentThread().getStackTrace()[3].getMethodName());
        super.onPanelClosed(featureId, menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(tag, Thread.currentThread().getStackTrace()[3].getMethodName());
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(tag, Thread.currentThread().getStackTrace()[3].getMethodName());
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        Log.d(tag, Thread.currentThread().getStackTrace()[3].getMethodName());
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        Log.d(tag, Thread.currentThread().getStackTrace()[3].getMethodName());
        super.onOptionsMenuClosed(menu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        Log.d(tag, Thread.currentThread().getStackTrace()[3].getMethodName());
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    protected void onApplyThemeResource(Resources.Theme theme, int resid, boolean first) {
        Log.d(tag, Thread.currentThread().getStackTrace()[3].getMethodName());
        super.onApplyThemeResource(theme, resid, first);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(tag, Thread.currentThread().getStackTrace()[3].getMethodName());
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onChildTitleChanged(Activity childActivity, CharSequence title) {
        Log.d(tag, Thread.currentThread().getStackTrace()[3].getMethodName());
        super.onChildTitleChanged(childActivity, title);
    }

    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        Log.d(tag, Thread.currentThread().getStackTrace()[3].getMethodName());
        super.onTitleChanged(title, color);
    }

    @Override
    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
        Log.d(tag, Thread.currentThread().getStackTrace()[3].getMethodName());
        return super.onWindowStartingActionMode(callback);
    }

    @Override
    public void onActionModeStarted(ActionMode mode) {
        Log.d(tag, Thread.currentThread().getStackTrace()[3].getMethodName());
        super.onActionModeStarted(mode);
    }

    @Override
    public void onActionModeFinished(ActionMode mode) {
        Log.d(tag, Thread.currentThread().getStackTrace()[3].getMethodName());
        super.onActionModeFinished(mode);
    }
}
