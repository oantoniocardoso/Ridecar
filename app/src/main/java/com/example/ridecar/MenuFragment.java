package com.example.ridecar;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Html;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MenuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MenuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MenuFragment newInstance(String param1, String param2) {
        MenuFragment fragment = new MenuFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        TextView textViewPerfil = (TextView) view.findViewById(R.id.textViewPerfil);
        SpannableString perfil = new SpannableString("Perfil");
        textViewPerfil.setText(perfil);
        textViewPerfil.setMovementMethod(LinkMovementMethod.getInstance());

        textViewPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PerfilActivity.class);
                startActivity(intent);
            }
        });

        TextView textViewRotasCaroneiro = (TextView) view.findViewById(R.id.textViewRotasCaroneiro);
        SpannableString rotasCaroneiro = new SpannableString("Rotas Caroneiro");
        textViewRotasCaroneiro.setText(rotasCaroneiro);
        textViewRotasCaroneiro.setMovementMethod(LinkMovementMethod.getInstance());

        textViewRotasCaroneiro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), RotasCaroneiroActivity.class);
                startActivity(intent);
            }
        });

        TextView textViewHabilitarMotorista = (TextView) view.findViewById(R.id.textViewHabilitarMotorista);
        SpannableString habilitarMotorista = new SpannableString("Habilitar Motorista");
        textViewHabilitarMotorista.setText(habilitarMotorista);
        textViewHabilitarMotorista.setMovementMethod(LinkMovementMethod.getInstance());

        textViewHabilitarMotorista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), HabilitarMotoristaActivity.class);
                startActivity(intent);
            }
        });

        TextView textViewVeiculo = (TextView) view.findViewById(R.id.textViewVeiculo);
        SpannableString veiculo = new SpannableString("Veículo");
        textViewVeiculo.setText(veiculo);
        textViewVeiculo.setMovementMethod(LinkMovementMethod.getInstance());

        textViewVeiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), VeiculoActivity.class);
                startActivity(intent);
            }
        });

        TextView textViewRotasMotorista = (TextView) view.findViewById(R.id.textViewRotasMotorista);
        SpannableString rotasMotorista = new SpannableString("Rotas Motorista");
        textViewRotasMotorista.setText(rotasMotorista);
        textViewRotasMotorista.setMovementMethod(LinkMovementMethod.getInstance());

        textViewRotasMotorista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), RotasMotoristaActivity.class);
                startActivity(intent);
            }
        });

        TextView textViewLogout = (TextView) view.findViewById(R.id.textViewLogout);
        SpannableString sair = new SpannableString("Sair");
        textViewLogout.setText(sair);
        textViewLogout.setMovementMethod(LinkMovementMethod.getInstance());

        textViewLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}