package com.appsdeveloperblog.photoapp.api.albums.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.appsdeveloperblog.photoapp.api.albums.data.AlbumEntity;

public class AlbumsServiceTest {

	@Test
	public void calculateSum_Test() {
//		fail("Not yet implemented");
		AlbumsServiceImpl albumsServiceImpl = new AlbumsServiceImpl();
		int actualResult = albumsServiceImpl.calculateSum(new int[] { 1, 2, 3, 4, 5 });
		int expectedResult = 15;
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void getAlbumsTest() {
		AlbumsService albumsServiceMock = mock(AlbumsService.class);
		
		List<AlbumEntity> returnValue = new ArrayList<>();
        
        AlbumEntity albumEntity = new AlbumEntity();
        albumEntity.setUserId("1");
        albumEntity.setAlbumId("album1Id");
        albumEntity.setDescription("album 1 description");
        albumEntity.setId(1L);
        albumEntity.setName("album 1 name");
        
        AlbumEntity albumEntity2 = new AlbumEntity();
        albumEntity2.setUserId("2");
        albumEntity2.setAlbumId("album2Id");
        albumEntity2.setDescription("album 2 description");
        albumEntity2.setId(2L);
        albumEntity2.setName("album 2 name");
        
        returnValue.add(albumEntity);
        returnValue.add(albumEntity2);

		when(albumsServiceMock.getAlbums()).thenReturn(returnValue);
		// Now we have set that when the getAlbums method from Albums service is called, it should return this array.
		// We can now set the expected array also, and compare the expected array with the returned array.
	}

}
