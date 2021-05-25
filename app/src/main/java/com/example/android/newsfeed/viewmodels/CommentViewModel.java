package com.example.android.newsfeed.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.android.newsfeed.api.dto.comment.GetCommentsRequest;
import com.example.android.newsfeed.api.dto.comment.NewCommentRequest;
import com.example.android.newsfeed.api.dto.comment.NewReplyRequest;
import com.example.android.newsfeed.model.Comment;
import com.example.android.newsfeed.repositories.CommentRepository;

import java.util.List;

public class CommentViewModel extends ViewModel {

    private CommentRepository commentRepository;
    private LiveData<List<Comment>> comments;

    public CommentViewModel() {
        commentRepository = new CommentRepository();
        comments = commentRepository.getComments();
    }

    /**
     * Get all comments of the news from the API
     * @param getCommentsRequest
     */
    public void getNewsComment(GetCommentsRequest getCommentsRequest) {
        commentRepository.getNewsComments(getCommentsRequest);
    }

    /**
     * Add new comment to the news
     * @param newCommentRequest
     */
    public void addNewComment(NewCommentRequest newCommentRequest) {
        commentRepository.addNewComment(newCommentRequest);
    }

    /**
     * Add new reply to the comment
     * @param newReplyRequest
     */
    public void addNewReply(NewReplyRequest newReplyRequest) {
        commentRepository.addNewReply(newReplyRequest);
    }

    public LiveData<List<Comment>> getComments() {
        return comments;
    }
}
