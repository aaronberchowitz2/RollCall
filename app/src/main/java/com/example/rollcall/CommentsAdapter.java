package com.example.rollcall;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsViewHolder>{

    private List<Comment> messages = new ArrayList<>();
    String id;
    boolean hasComments = false;

    public CommentsAdapter(String id) {
        this.id = id;
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference comm = db.collection("Images").document(id).collection("comments");
        if (comm != null) {
            hasComments = true;
            comm.get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                messages = new ArrayList<>();
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    try {
                                        Comment m = new Comment(
                                                SecurityHelper.Decrypt(document.get("userPhoto").toString()),
                                                SecurityHelper.Decrypt(document.get("userName").toString()),
                                                SecurityHelper.Decrypt(document.get("userID").toString()),
                                                SecurityHelper.Decrypt(document.get("message").toString())
                                        );
                                        messages.add(m);
                                    } catch (Exception e) {
                                    }
                                }
                                notifyDataSetChanged();
                            }
                        }
                    });
            comm.addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                    messages = new ArrayList<>();
                    for (DocumentSnapshot document : value.getDocuments())
                    {
                        try {
                            Comment m = new Comment(
                                    SecurityHelper.Decrypt(document.get("userPhoto").toString()),
                                    SecurityHelper.Decrypt(document.get("userName").toString()),
                                    SecurityHelper.Decrypt(document.get("userID").toString()),
                                    SecurityHelper.Decrypt(document.get("message").toString())
                            );
                            messages.add(m);
                        } catch (Exception e) {
                        }
                    }
                    notifyDataSetChanged();
                }
            });
        } else
        {
            hasComments = false;
            messages = new ArrayList<>();
        }

    }

    @NonNull
    @Override
    public CommentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comment, parent, false);
        return new CommentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentsViewHolder holder, int position) {
        Comment message = messages.get(position);
        holder.message.setText(message.message);
        holder.userName.setText(message.userName);
        Glide.with(holder.userImage.getContext()).load(message.userPhoto).into(holder.userImage);
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public void addComment(Comment m) {
        Map<String, Object> message = new HashMap<>();
        message.put("userPhoto", SecurityHelper.Encrypt(m.userPhoto));
        message.put("userName", SecurityHelper.Encrypt(m.userName));
        message.put("userID", SecurityHelper.Encrypt(m.userID));
        message.put("message",SecurityHelper.Encrypt(m.message));
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Images").document(id).collection("comments").document(String.valueOf(System.currentTimeMillis()))
                .set(message).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        if (!hasComments) {
                            hasComments = true;
                            CollectionReference comm = db.collection("Images").document(id).collection("comments");
                            comm.addSnapshotListener(new EventListener<QuerySnapshot>() {
                                @Override
                                public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                                    messages = new ArrayList<>();
                                    for (DocumentSnapshot document : value.getDocuments())
                                    {
                                        try {
                                            Comment m = new Comment(
                                                    SecurityHelper.Decrypt(document.get("userPhoto").toString()),
                                                    SecurityHelper.Decrypt(document.get("userName").toString()),
                                                    SecurityHelper.Decrypt(document.get("userID").toString()),
                                                    SecurityHelper.Decrypt(document.get("message").toString())
                                            );
                                            messages.add(m);
                                        } catch (Exception e) {
                                        }
                                    }
                                    notifyDataSetChanged();
                                }
                            });
                        }
                    }
                });
    }
}