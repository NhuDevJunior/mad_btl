package com.example.android.newsfeed.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.newsfeed.R;
import com.example.android.newsfeed.api.dto.comment.NewReplyRequest;
import com.example.android.newsfeed.databinding.CommentItemBinding;
import com.example.android.newsfeed.model.Comment;
import com.example.android.newsfeed.model.Reply;
import com.example.android.newsfeed.utils.Constants;
import com.example.android.newsfeed.viewmodels.CommentViewModel;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    private Context context;
    private List<Comment> comments;
    private CommentViewModel commentViewModel;
    private String token;

    public CommentAdapter(Context context, List<Comment> comments, CommentViewModel commentViewModel) {
        this.context = context;
        this.comments = comments;
        this.commentViewModel = commentViewModel;
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.share_preference_file_key), Context.MODE_PRIVATE);
        token = sharedPreferences.getString(Constants.SHARE_PREFERENCE_KEY_TOKEN, null);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item, parent, false);
        return new CommentAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.ViewHolder holder, int position) {
        final Comment currentComment = comments.get(position);
        holder.binding.commentUsername.setText(currentComment.getUsername());
        holder.binding.commentContent.setText(currentComment.getContent());

        // Show the replies only if the replies is not empty
        if (!currentComment.getReplies().isEmpty()) {
            holder.binding.commentReplies.setVisibility(View.VISIBLE);
        }

        // If the user has logged in
        if (token != null) {
            holder.binding.showReplyInputButton.setVisibility(View.VISIBLE);

            // Toggle the input reply on click only
            holder.binding.showReplyInputButton.setOnClickListener(view -> {
                if (holder.binding.commentReplyInputContainer.isShown()) {
                    holder.binding.commentReplyInputContainer.setVisibility(View.GONE);
                } else {
                    holder.binding.commentReplyInputContainer.setVisibility(View.VISIBLE);
                }
            });

            // Add new reply on submit
            holder.binding.commentReplySubmitButton.setOnClickListener(view -> {
                String replyContent = holder.binding.commentReplyInput.getText().toString();
                if (!replyContent.equals("")) {
                    commentViewModel.addNewReply(new NewReplyRequest(replyContent, currentComment.getId(), token));
                }
                holder.binding.commentReplyInput.setText("");
            });
        }

        // Add the reply to the view
        holder.binding.commentReplies.removeAllViews();
        for (Reply reply : currentComment.getReplies()) {
            TextView replyUsernameView = new TextView(context);
            replyUsernameView.setText(reply.getUsername());
            replyUsernameView.setTypeface(Typeface.DEFAULT_BOLD);

            TextView replyContentView = new TextView(context);
            replyContentView.setText(reply.getContent());

            LinearLayout replyView = new LinearLayout(context);
            replyView.setOrientation(LinearLayout.VERTICAL);
            replyView.addView(replyUsernameView);
            replyView.addView(replyContentView);

            holder.binding.commentReplies.addView(replyView);
        }
    }

    public void clearAll() {
        this.comments.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Comment> comments) {
        this.comments.clear();
        this.comments.addAll(comments);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CommentItemBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = CommentItemBinding.bind(itemView);
        }
    }

}
