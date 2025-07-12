package com.example.skillswapodoo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skillswapodoo.R;
import com.example.skillswapodoo.models.SwapRequestModel;

import java.util.ArrayList;
import java.util.List;

public class SwapRequestAdapter extends RecyclerView.Adapter<SwapRequestAdapter.ViewHolder> {

    private final Context context;
    private List<SwapRequestModel> swapList;

    public SwapRequestAdapter(Context context, List<SwapRequestModel> swapList) {
        this.context = context;
        this.swapList = new ArrayList<>(swapList); // ✅ Safe initialization
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.swap_request_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SwapRequestModel item = swapList.get(position);

        holder.name.setText(item.getName());
        holder.tvOffered.setText("Skill Offered → " + item.getSkillsOffered());
        holder.tvWanted.setText("Skill Wanted → " + item.getSkillsWanted());
        holder.tvRating.setText("Rating: " + item.getRating() + "/5");
        holder.tvStatus.setText(item.getStatus());
        holder.profileImage.setImageResource(item.getProfileImageResId());

        // Set color and button visibility based on status
        switch (item.getStatus()) {
            case "Pending":
                holder.tvStatus.setTextColor(context.getResources().getColor(android.R.color.holo_orange_light));
                holder.actionButtons.setVisibility(View.VISIBLE);
                break;
            case "Accepted":
                holder.tvStatus.setTextColor(context.getResources().getColor(android.R.color.holo_green_light));
                holder.actionButtons.setVisibility(View.GONE);
                break;
            case "Rejected":
                holder.tvStatus.setTextColor(context.getResources().getColor(android.R.color.holo_red_light));
                holder.actionButtons.setVisibility(View.GONE);
                break;
        }

        holder.btnAccept.setOnClickListener(v -> {
            item.setStatus("Accepted");
            notifyItemChanged(position);
            Toast.makeText(context, "Accepted " + item.getName(), Toast.LENGTH_SHORT).show();
        });

        holder.btnReject.setOnClickListener(v -> {
            item.setStatus("Rejected");
            notifyItemChanged(position);
            Toast.makeText(context, "Rejected " + item.getName(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return swapList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView profileImage;
        TextView name, tvOffered, tvWanted, tvRating, tvStatus;
        Button btnAccept, btnReject;
        LinearLayout actionButtons;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            profileImage = itemView.findViewById(R.id.profile_image);
            name = itemView.findViewById(R.id.name);
            tvOffered = itemView.findViewById(R.id.tv_offered);
            tvWanted = itemView.findViewById(R.id.tv_wanted);
            tvRating = itemView.findViewById(R.id.tv_rating);
            tvStatus = itemView.findViewById(R.id.tv_status);
            btnAccept = itemView.findViewById(R.id.btn_accept);
            btnReject = itemView.findViewById(R.id.btn_reject);
            actionButtons = itemView.findViewById(R.id.action_buttons);
        }
    }

    // ✅ Working update method
    public void updateList(List<SwapRequestModel> newList) {
        this.swapList.clear();
        this.swapList.addAll(newList);
        notifyDataSetChanged();
    }
}
