package com.starry.service;

import com.starry.entity.Saying;

public interface SayingService {
    Saying selectOneById(int id);
    void upadateLikesById(int SayingId, String likes);
}
