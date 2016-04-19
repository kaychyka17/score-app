package si.karin.scoreapp.pitch;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import si.karin.scoreapp.MainActivity;
import si.karin.scoreapp.R;

/**
 * Fragment for tennis match pitch view
 * <p>
 * Created by Karin on 18.4.2016.
 */
public class TennisPitchFragment extends PitchFragment {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // set callback
        ((MainActivity) getActivity()).setTennisMatchPitchCallback(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        sp_home = "Tennis_home";
        sp_away = "Tennis_away";

        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        imgPitch.setImageResource(R.mipmap.tennis_pitch);

        return rootView;
    }
}
