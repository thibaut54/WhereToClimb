//package org.thibaut.wheretoclimb.consumer.repository;
//
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Transactional;
//import org.thibaut.wheretoclimb.consumer.DemoApplication;
//import org.thibaut.wheretoclimb.model.entity.Area;
//import org.thibaut.wheretoclimb.model.entity.Atlas;
//import org.thibaut.wheretoclimb.util.GenericBuilder;
//
//import java.time.LocalDateTime;
//
//import static junit.framework.TestCase.assertTrue;
//
//@RunWith( SpringRunner.class )
//@SpringBootTest(classes = DemoApplication.class)
//@Transactional
//public class AreaRepositoryTest {
//
//	@Autowired
//	private AreaRepository areaRepository;
//
//
//	@Test
//	public void shouldAlwaysBeInvoked() {
//		assertTrue("Should never fail and will ensure that the unit tests are possible to execute", true);
//	}
//
//	@Test
//	public void checkSearchAtlas(){
//		Area areaTest = GenericBuilder.of( Area::new )
//				                  .with( Area::setName, "AreaTest" )
//				                  .with( Area::setNearestCity, "City test." )
//				                  .with( Area::setAccess, "Access test." )
//				                  .with( Area::setApproachDuration, 15 )
//				                  .with( Area::setCreateDate, LocalDateTime.now() )
//				                  .build();
//		this.areaRepository.save( areaTest );
//
//		Page< Area > area = this.areaRepository.searchArea(
//				"AreaTest".toLowerCase(), PageRequest.of( 0, 1 ) );
//
//		Assert.assertEquals( areaTest, area.getContent().get( 0 ));
//	}
//}
