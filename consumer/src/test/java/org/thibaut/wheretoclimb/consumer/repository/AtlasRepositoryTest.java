package org.thibaut.wheretoclimb.consumer.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.thibaut.wheretoclimb.consumer.DemoApplication;
import org.thibaut.wheretoclimb.model.entity.Atlas;
import org.thibaut.wheretoclimb.util.GenericBuilder;

import java.time.LocalDateTime;

import static junit.framework.TestCase.assertTrue;

@RunWith( SpringRunner.class )
@SpringBootTest(classes = DemoApplication.class)
@Transactional
public class AtlasRepositoryTest {


	@Autowired
	private AtlasRepository atlasRepository;


	@Test
	public void shouldAlwaysBeInvoked() {
		assertTrue("Should never fail and will ensure that the unit tests are possible to execute", true);
	}

	@Test
	public void checkSearchAtlas(){
		Atlas atlasTest = GenericBuilder.of( Atlas::new )
				                  .with( Atlas::setName, "AtlasTest" )
				                  .with( Atlas::setCreateDate, LocalDateTime.now() )
				                  .with( Atlas::setCountry, "CountryTest" )
				                  .build();
		this.atlasRepository.save( atlasTest );

		Page< Atlas > atlas = this.atlasRepository.searchAtlas(
				"AtlasTest".toLowerCase(), PageRequest.of( 0, 1 ) );

		Assert.assertEquals( atlasTest, atlas.getContent().get( 0 ));
	}

}

