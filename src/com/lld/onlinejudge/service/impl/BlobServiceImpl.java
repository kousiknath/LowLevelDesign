package com.lld.onlinejudge.service.impl;

import com.lld.onlinejudge.service.BlobService;

public class BlobServiceImpl implements BlobService {
    @Override
    public String saveData(String data) {
        // Save the code to say Amazon S3 or internal blob storage
        return "/some/random/path";
    }
}
