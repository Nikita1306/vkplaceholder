package com.vk.vktest.controllers;

import com.vk.vktest.models.Album;
import com.vk.vktest.services.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("albums")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    @PostMapping(path = "save")
    public Album saveAlbum(@RequestBody Album album) {
        return albumService.saveAlbum(album);
    }

    @GetMapping(path = "{id}")
    public Album getAlbum (@PathVariable String id) {
        return albumService.getAlbum(id);
    }

    @PutMapping(path = "{id}")
    public Album updateAlbum(@PathVariable String id, @RequestBody Album album) {
        return albumService.updateAlbum(album, id);
    }
    @DeleteMapping(path = "{id}")
    public ResponseEntity patchAlbum (@PathVariable String id) {
        albumService.deleteAlbum(id);
        return new ResponseEntity<String>(HttpStatus.OK);
    }
}
