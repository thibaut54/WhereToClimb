package org.thibaut.wheretoclimb.consumer.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.thibaut.wheretoclimb.model.entity.Atlas;
import org.thibaut.wheretoclimb.util.GenericBuilder;
import static junit.framework.TestCase.assertTrue;
import org.thibaut.wheretoclimb.consumer.DemoApplication;

import java.time.LocalDateTime;

//@RunWith( SpringJUnit4ClassRunner.class )
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
				                  .with( Atlas::setScale, "ScaleTest" )
				                  .with( Atlas::setCountry, "CountryTest" )
				                  .build();
		this.atlasRepository.save( atlasTest );

		System.out.println( atlasTest.toString() );
		System.out.println( atlasTest.getName().toString() );

		Page<Atlas> atlas = this.atlasRepository.searchAtlas( "a" , PageRequest.of( 0, 1 ));

		System.out.println( atlas.getNumberOfElements() );

		Assert.assertEquals( atlasTest, atlas.getContent().get( 0 ));
	}

}

