package com.demacs.moviemarket.persistence.dao;

import com.demacs.moviemarket.persistence.model.Download;

import java.util.List;

public interface DownloadDao {
    Download findById(int id);
    List<Download> findAll();
    void save(Download download);
    void update(Download download);
    void delete(Download download);
}