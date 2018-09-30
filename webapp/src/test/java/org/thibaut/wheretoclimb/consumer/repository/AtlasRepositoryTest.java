package org.thibaut.wheretoclimb.consumer.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.thibaut.wheretoclimb.model.entity.Atlas;
import org.thibaut.wheretoclimb.util.GenericBuilder;
import org.thibaut.wheretoclimb.webapp.config.DBConfiguration;

import java.time.LocalDateTime;

@RunWith( SpringRunner.class )
@SpringBootTest(classes = DBConfiguration.class )

public class AtlasRepositoryTest {

	@Autowired
	private AtlasRepository atlasRepository;

	@Test
	public void checkSearchAtlas(){
		Atlas atlasTest = GenericBuilder.of( Atlas::new )
				.with( Atlas::setName, "AtlasTest" )
				.with( Atlas::setCreateDate, LocalDateTime.now() )
				.with( Atlas::setScale, "ScaleTest" )
				.with( Atlas::setCountry, "CountryTest" )
				.build();
		atlasRepository.save( atlasTest );

		Page<Atlas> atlas = atlasRepository.searchAtlas( "AtlasTest" , PageRequest.of( 0, 10 ));

		Assert.assertEquals( atlasTest, atlas.getContent().get( 0 ));
	}



}
