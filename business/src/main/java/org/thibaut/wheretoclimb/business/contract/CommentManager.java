package org.thibaut.wheretoclimb.business.contract;


import org.thibaut.wheretoclimb.model.entity.Comment;

public interface CommentManager {
	Comment createComment( Comment comment );

	Comment findCommentById( Integer id );

	void deleteComment( Integer commentId );
}
