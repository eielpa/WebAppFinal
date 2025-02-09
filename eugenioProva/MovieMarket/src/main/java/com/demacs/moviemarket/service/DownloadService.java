package com.demacs.moviemarket.service;

import com.demacs.moviemarket.persistence.dao.DownloadDao;
import com.demacs.moviemarket.persistence.model.Download;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DownloadService {

    private final DownloadDao downloadDao;

    public DownloadService(DownloadDao downloadDao) {
        this.downloadDao = downloadDao;
    }

    public Download findById(int id) {
        return downloadDao.findById(id);
    }

    public List<Download> findAll() {
        return downloadDao.findAll();
    }

    public void save(Download download) {
        downloadDao.save(download);
    }

    public void update(Download download) {
        downloadDao.update(download);
    }

    public void delete(Download download) {
        downloadDao.delete(download);
    }
}