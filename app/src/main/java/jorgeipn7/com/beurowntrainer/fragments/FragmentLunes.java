package jorgeipn7.com.beurowntrainer.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import jorgeipn7.com.beurowntrainer.R;

/**
 * A simple {@link Fragment} subclass.
 */

//fragment_editar_rutina_lunes

public class FragmentLunes extends Fragment {


    public FragmentLunes() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_editar_rutina_lunes, container, false);
    }

}
