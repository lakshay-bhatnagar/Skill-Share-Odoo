package com.example.skillswapodoo.ui.dashboard;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skillswapodoo.R;
import com.example.skillswapodoo.adapters.SwapRequestAdapter;
import com.example.skillswapodoo.databinding.FragmentDashboardBinding;
import com.example.skillswapodoo.models.SwapRequestModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private List<SwapRequestModel> fullList;
    private SwapRequestAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recyclerView = root.findViewById(R.id.recycler_swap_requests);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // 1. Full swap list
        fullList = new ArrayList<>();
        fullList.add(new SwapRequestModel("Marc Demo", "JavaScript", "Photoshop", 3.9f, R.drawable.baseline_person_24, "Pending"));
        fullList.add(new SwapRequestModel("Mansi Singh", "Graphic Designing", "Java", 4.5f, R.drawable.baseline_person_24, "Accepted"));
        fullList.add(new SwapRequestModel("Lakshay Bhatnagar", "App Development", "Blender", 4.4f, R.drawable.baseline_person_24, "Pending"));
        fullList.add(new SwapRequestModel("Aditya Jha", "Web Development", "Python", 4.4f, R.drawable.baseline_person_24, "Pending"));
        fullList.add(new SwapRequestModel("Mundir", "Singing", "Excel", 3.4f, R.drawable.baseline_person_24, "Rejected"));

        adapter = new SwapRequestAdapter(getContext(), new ArrayList<>(fullList));
        recyclerView.setAdapter(adapter);

        // 2. Spinner setup
        Spinner spinner = root.findViewById(R.id.spinner_status_filter);
        String[] statuses = {"All", "Pending", "Accepted", "Rejected"};
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, statuses) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                ((TextView) view).setTextColor(getResources().getColor(android.R.color.black));
                return view;
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                ((TextView) view).setTextColor(getResources().getColor(android.R.color.black));
                return view;
            }
        };
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, View view, int position, long id) {
                filterList(spinner.getSelectedItem().toString(), binding.etSearch.getText().toString());
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {}
        });

        // 3. Search functionality
        EditText searchView = root.findViewById(R.id.et_search);
        searchView.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterList(spinner.getSelectedItem().toString(), s.toString());
            }
            @Override public void afterTextChanged(Editable s) {}
        });

        return root;
    }

    private void filterList(String statusFilter, String query) {
        List<SwapRequestModel> filtered = fullList.stream().filter(model -> {
            boolean matchesStatus = statusFilter.equals("All") || model.getStatus().equalsIgnoreCase(statusFilter);
            boolean matchesQuery = model.getName().toLowerCase().contains(query.toLowerCase())
                    || model.getSkillsOffered().toLowerCase().contains(query.toLowerCase())
                    || model.getSkillsWanted().toLowerCase().contains(query.toLowerCase());
            return matchesStatus && matchesQuery;
        }).collect(Collectors.toList());

        adapter.updateList(filtered);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
