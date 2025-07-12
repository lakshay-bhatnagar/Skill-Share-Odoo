package com.example.skillswapodoo.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skillswapodoo.ProfileActivity;
import com.example.skillswapodoo.R;
import com.example.skillswapodoo.adapters.SkillSwapAdapter;
import com.example.skillswapodoo.databinding.FragmentHomeBinding;
import com.example.skillswapodoo.models.SkillSwapModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private ImageButton profileBtn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recyclerView = root.findViewById(R.id.recommendedSkillRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<SkillSwapModel> userList = new ArrayList<>();
        userList.add(new SkillSwapModel("John Doe", "Guitar", "French", 4.5f, R.drawable.baseline_person_24));
        userList.add(new SkillSwapModel("Sarah Lee", "Math", "Singing", 4.0f, R.drawable.baseline_person_24));
        userList.add(new SkillSwapModel("Amit Kumar", "Cooking", "English", 5.0f, R.drawable.baseline_person_24));

        SkillSwapAdapter adapter = new SkillSwapAdapter(getContext(), userList);
        recyclerView.setAdapter(adapter);
        profileBtn = binding.profileBtn;
        profileBtn.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), ProfileActivity.class);
            startActivity(intent);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}