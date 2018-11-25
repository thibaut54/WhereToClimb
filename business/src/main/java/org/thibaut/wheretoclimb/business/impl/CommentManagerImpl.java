package org.thibaut.wheretoclimb.business.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.thibaut.wheretoclimb.business.contract.CommentManager;
import org.thibaut.wheretoclimb.model.entity.Atlas;
import org.thibaut.wheretoclimb.model.entity.BookingRequest;
import org.thibaut.wheretoclimb.model.entity.Comment;

import java.util.Optional;

@Component
@Slf4j
public class CommentManagerImpl extends AbstractManager implements CommentManager {


	@Override
	public Comment createComment( Comment comment ) {
		getDaoFactory().getCommentRepository().save( comment);
		return comment;
	}


	@Override
	public Comment findCommentById( Integer id ){

		if(id != null){
			Optional< Comment > comment=  getDaoFactory().getCommentRepository().findById( id );
			if(comment.isPresent()){
				return comment.get();
			}
		}
		return null;
	}


	@Override
	public void deleteComment( Integer commentId ){
		getDaoFactory().getCommentRepository().deleteById( commentId );
	}
}
