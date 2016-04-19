package si.karin.scoreapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

/**
 * Created by Karin on 18.4.2016.
 */
public class TennisPitchFragment extends PitchFragment implements MatchPitchCallback {

    /**
     * Returns a new instance of this fragment
     */
    public static TennisPitchFragment newInstance() {

        Bundle args = new Bundle();

        TennisPitchFragment fragment = new TennisPitchFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((MainActivity)getActivity()).setMatchPitchCallback(this);
        View rootView =  super.onCreateView(inflater, container, savedInstanceState);
        imgPitch.setImageResource(R.mipmap.tennis_pitch);
        tvHome.setText(sharedPrefs.getInt("Tennis_home", 0)+"");
        tvAway.setText(sharedPrefs.getInt("Tennis_away", 0)+"");

        return rootView;
    }

    @Override
    public void setResult(int home, int away) {
        Log.e("TENNIS", "pitch");
        this.home = home;
        this.away = away;
        ((TextView) rootView.findViewById(R.id.anim_home)).setText(home + "");
        ((TextView) rootView.findViewById(R.id.anim_away)).setText(away + "");
        Animation a = AnimationUtils.loadAnimation(getContext(), R.anim.from_left_to_center);
        a.setAnimationListener(showListener);
        scoreLayout.startAnimation(a);
    }

    @Override
    public void setCallback(MainActivity activity) {
        activity.setMatchPitchCallback(this);
    }
}
