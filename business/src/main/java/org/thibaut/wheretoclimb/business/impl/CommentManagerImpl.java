package org.thibaut.wheretoclimb.business.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.thibaut.wheretoclimb.business.contract.CommentManager;
import org.thibaut.wheretoclimb.model.entity.Atlas;
import org.thibaut.wheretoclimb.model.entity.Comment;

@Component
@Slf4j
public class CommentManagerImpl extends AbstractManager implements CommentManager {

	@Override
	public Comment createComment( Comment comment ) {
		getDaoFactory().getCommentRepository().save( comment);
		return comment;
	}
}
