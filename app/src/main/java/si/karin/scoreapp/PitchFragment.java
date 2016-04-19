package si.karin.scoreapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PitchFragment extends Fragment {

    protected TextView tvAway;
    protected TextView tvHome;
    protected ImageView imgPitch;
    protected SharedPreferences sharedPrefs;
    protected View rootView;
    protected LinearLayout scoreLayout;
    protected int home = 0;
    protected int away = 0;
    protected MainActivity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main, container, false);

        scoreLayout = (LinearLayout) rootView.findViewById(R.id.score_layout);
        scoreLayout.setVisibility(View.GONE);

        sharedPrefs = getActivity().getPreferences(Context.MODE_PRIVATE);
        tvHome = (TextView) rootView.findViewById(R.id.tv_home);
        tvAway = (TextView) rootView.findViewById(R.id.tv_away);

        imgPitch = (ImageView) rootView.findViewById(R.id.img_pitch);
        return rootView;
    }


    protected Animation.AnimationListener showListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {
            scoreLayout.setVisibility(View.VISIBLE);
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            Animation a = AnimationUtils.loadAnimation(getContext(), R.anim.anim_rotate);
            a.setAnimationListener(continueListener);
            scoreLayout.startAnimation(a);
        }

        @Override
        public void onAnimationRepeat(Animation animation) { }
    };

    private Animation.AnimationListener continueListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            Animation a = AnimationUtils.loadAnimation(getContext(), R.anim.from_center_to_right);
            a.setAnimationListener(hideListener);
            tvHome.setText(home + "");
            tvAway.setText(away + "");
            scoreLayout.startAnimation(a);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };

    private Animation.AnimationListener hideListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            scoreLayout.setVisibility(View.GONE);

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };
}
