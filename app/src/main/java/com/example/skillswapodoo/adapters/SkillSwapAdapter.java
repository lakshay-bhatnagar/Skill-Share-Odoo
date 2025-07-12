package com.example.skillswapodoo.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skillswapodoo.R;
import com.example.skillswapodoo.models.SkillSwapModel;

import java.util.List;

public class SkillSwapAdapter extends RecyclerView.Adapter<SkillSwapAdapter.ViewHolder> {


    private final Context context;
    private final List<SkillSwapModel> swapList;

    public SkillSwapAdapter(Context context, List<SkillSwapModel> swapList) {
        this.context = context;
        this.swapList = swapList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.skill_swap_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SkillSwapModel item = swapList.get(position);

        holder.name.setText(item.getProfileName());
        holder.skillsOffered.setText("Offers: " + item.getSkillsOffered());
        holder.skillsWanted.setText("Wants: " + item.getSkillsWanted());
        holder.ratingBar.setRating(item.getRating());
        holder.profileImage.setImageResource(item.getProfileImageResId());

        holder.requestBtn.setOnClickListener(v -> {
            View dialogView = LayoutInflater.from(context).inflate(R.layout.skill_request_dialog, null);
            Spinner spinnerOffered = dialogView.findViewById(R.id.spinner_offered);
            Spinner spinnerWanted = dialogView.findViewById(R.id.spinner_wanted);
            EditText etMessage = dialogView.findViewById(R.id.et_message);
            Button btnSend = dialogView.findViewById(R.id.btn_send_request);

            // Split skills by comma or space
            String[] offeredSkills = item.getSkillsOffered().split(",\\s*");
            String[] wantedSkills = item.getSkillsWanted().split(",\\s*");

            ArrayAdapter<String> offeredAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, offeredSkills);
            ArrayAdapter<String> wantedAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, wantedSkills);

            spinnerOffered.setAdapter(offeredAdapter);
            spinnerWanted.setAdapter(wantedAdapter);

            AlertDialog dialog = new AlertDialog.Builder(context)
                    .setView(dialogView)
                    .setCancelable(true)
                    .create();

            dialog.show();

            btnSend.setOnClickListener(view -> {
                String selectedOffered = spinnerOffered.getSelectedItem().toString();
                String selectedWanted = spinnerWanted.getSelectedItem().toString();
                String message = etMessage.getText().toString().trim();

                // Show confirmation toast
                Toast.makeText(context,
                        "Request sent to " + item.getProfileName() +
                                "\nOffered: " + selectedOffered +
                                "\nWanted: " + selectedWanted,
                        Toast.LENGTH_LONG).show();

                // Hide button and show "Requested"
                holder.requestBtn.setVisibility(View.GONE);
                holder.requestedText.setVisibility(View.VISIBLE);

                dialog.dismiss();
            });
        });
    }

    @Override
    public int getItemCount() {
        return swapList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView profileImage;
        TextView name, skillsOffered, skillsWanted, requestedText;
        RatingBar ratingBar;
        Button requestBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            profileImage = itemView.findViewById(R.id.profile_image);
            name = itemView.findViewById(R.id.tv_profile_name);
            requestedText = itemView.findViewById(R.id.tv_requested);
            skillsOffered = itemView.findViewById(R.id.tv_skills_offered);
            skillsWanted = itemView.findViewById(R.id.tv_skills_wanted);
            ratingBar = itemView.findViewById(R.id.rating_bar);
            requestBtn = itemView.findViewById(R.id.btn_request);
        }
    }
}

