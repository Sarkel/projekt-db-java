package org.library.gui;

import com.sun.istack.internal.NotNull;
import org.library.model.Comments;
import org.library.wrappers.CommentsWrapper;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.stream.Collectors;

/**
 * Created by Sebastian Kubalski on 18.02.2016.
 * @author  Sebastian Kubalski
 * @version 1.0
 */
public class CommentsView extends JFrame{
    private JPanel contentPane;
    private JList commentsList;
    private JButton backButton;
    private JButton addCommentButton;

    /**
     * @param book id of selected book
     */
    public CommentsView(@NotNull Integer book){
        setContentPane(contentPane);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        try {
            this.comments = Comments.getBookComments(book);
            Vector<String> vec = this.comments.stream().map(comment ->
                    comment.nazwisko + " " + comment.imie).collect(Collectors.toCollection(Vector::new));
            commentsList.setListData(vec);
        } catch (SQLException e){
            System.out.println(e.getMessage());
            dispose();
        }
        commentsList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                new CommentDetailView(comments.get(e.getFirstIndex()).id, book);
                dispose();
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TotalBookView();
                dispose();
            }
        });
        addCommentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new NewCommentView(book);
                dispose();
            }
        });
    }

    private ArrayList<CommentsWrapper.Comment> comments;
}
