/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appsdeveloperblog.api.books.service;


import java.util.List;

import com.appsdeveloperblog.api.books.data.AlbumEntity;

public interface AlbumsService {
    List<AlbumEntity> getAlbums(String userId);

	List<AlbumEntity> getAlbums();

	int calculateSum(int[] data);
}
