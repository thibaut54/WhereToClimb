package org.thibaut.wheretoclimb.consumer.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;
import org.thibaut.wheretoclimb.model.entity.Atlas;
import org.thibaut.wheretoclimb.util.GenericBuilder;
import static junit.framework.TestCase.assertTrue;

import java.time.LocalDateTime;

//@RunWith( SpringRunner.class )
//@SpringBootTest
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
				.with( Atlas::setScale, "ScaleTest" )
				.with( Atlas::setCountry, "CountryTest" )
				.build();
		System.out.println( atlasTest.toString() );
		this.atlasRepository.save( atlasTest );

		Page<Atlas> atlas = atlasRepository.searchAtlas( "AtlasTest" , PageRequest.of( 0, 10 ));

		Assert.assertEquals( atlasTest, atlas.getContent().get( 0 ));
	}

}
