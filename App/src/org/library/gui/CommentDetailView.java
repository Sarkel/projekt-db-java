package org.library.gui;

import com.sun.istack.internal.NotNull;
import org.library.model.Comments;
import org.library.wrappers.CommentsWrapper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Created by Sebastian Kubalski on 18.02.2016.
 * @author  Sebastian Kubalski
 * @version 1.0
 */
public class CommentDetailView extends JFrame{

    private JPanel contentPane;
    private JButton backButton;
    private JLabel dataLabel;
    private JLabel dataValue;
    private JTextPane contentValue;

    /**
     * @param comment id of selected comment
     * @param book id of selected book
     */
    public CommentDetailView(@NotNull Integer comment, @NotNull Integer book){
        setContentPane(contentPane);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        try{
            this.comment = Comments.getCommentDetails(comment);
            setValues();
        } catch (SQLException e){
            System.out.println(e.getMessage());
            dispose();
        }
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CommentsView(book);
                dispose();
            }
        });
    }

    private CommentsWrapper.CommentDetail comment;

    private void setValues(){
        dataValue.setText(this.comment.data.toString());
        contentValue.setText(this.comment.content);
    }
}
