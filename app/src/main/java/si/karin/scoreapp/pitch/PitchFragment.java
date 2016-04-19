package si.karin.scoreapp.pitch;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import si.karin.scoreapp.R;

/**
 * Abstract basic fragment for pitch views
 */
public abstract class PitchFragment extends Fragment implements MatchPitchCallback {

    protected View rootView;
    protected TextView tvAway;
    protected TextView tvHome;
    protected ImageView imgPitch;
    protected LinearLayout scoreLayout;

    protected SharedPreferences sharedPrefs;

    protected int home = 0;
    protected int away = 0;
    protected String sp_home;
    protected String sp_away;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main, container, false);
        sharedPrefs = getActivity().getPreferences(Context.MODE_PRIVATE);

        tvHome = (TextView) rootView.findViewById(R.id.tv_home);
        tvAway = (TextView) rootView.findViewById(R.id.tv_away);

        scoreLayout = (LinearLayout) rootView.findViewById(R.id.score_layout);
        scoreLayout.setVisibility(View.GONE);

        imgPitch = (ImageView) rootView.findViewById(R.id.img_pitch);

        tvHome.setText(String.format("%s", sharedPrefs.getInt(sp_home, 0)));
        tvAway.setText(String.format("%s", sharedPrefs.getInt(sp_away, 0)));

        return rootView;
    }

    @Override
    public void setResult(int home, int away) {
        this.home = home;
        this.away = away;
        sharedPrefs.edit().putInt(sp_home, home).putInt(sp_away, away).apply();

        // bad way to set focus on fragment,
        // but in other case animation (and changing scores) sometimes doesn't work
        // and I couldn't find out better solution
        tvHome.setText(tvHome.getText().toString());

        ((TextView) rootView.findViewById(R.id.anim_home)).setText(String.format("%s", home));
        ((TextView) rootView.findViewById(R.id.anim_away)).setText(String.format("%s", away));

        // translate score layout from left side of the screen to the middle
        Animation a = AnimationUtils.loadAnimation(getContext(), R.anim.from_left_to_center);
        a.setAnimationListener(showScoresListener);
        scoreLayout.startAnimation(a);
    }

    /**
     * Animation listener for animation to set score layout visible on start
     * and to start new animation when this animation is ended
     */
    protected Animation.AnimationListener showScoresListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {
            scoreLayout.setVisibility(View.VISIBLE);
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            Animation a = AnimationUtils.loadAnimation(getContext(), R.anim.anim_rotate);
            a.setAnimationListener(rotateListener);
            scoreLayout.startAnimation(a);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {  }
    };

    /**
     * Animation listener for rotation animation
     */
    private Animation.AnimationListener rotateListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            // translate score layout from the middle of the screen to the right side
            Animation a = AnimationUtils.loadAnimation(getContext(), R.anim.from_center_to_right);
            a.setAnimationListener(hideScoresListener);

            // set/update score on main view
            tvHome.setText(String.format("%s", home));
            tvAway.setText(String.format("%s", away));
            scoreLayout.startAnimation(a);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };

    /**
     * Animation listener to hide score layout after ended animation
     */
    private Animation.AnimationListener hideScoresListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) { }

        @Override
        public void onAnimationEnd(Animation animation) {
            scoreLayout.setVisibility(View.GONE);
        }

        @Override
        public void onAnimationRepeat(Animation animation) { }
    };
}
